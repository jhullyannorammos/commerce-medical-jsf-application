package br.com.application.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.application.dao.CidadeDAO;
import br.com.application.dao.EstadoDAO;
import br.com.application.dao.PessoaDAO;
import br.com.application.domain.Cidade;
import br.com.application.domain.Estado;
import br.com.application.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	
	private CidadeDAO cidadeDao;
	private EstadoDAO estadoDao;
	private PessoaDAO pessoaDAO;
	private Pessoa pessoa;
	private Estado estado;
	
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;

	@PostConstruct
	public void listar() throws Exception {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			estadoDao = new EstadoDAO();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		} 
	}

	public void editar(ActionEvent evento) throws Exception {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		try{
			estado = pessoa.getCidade().getEstado();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Pessoa atualizada com sucesso");
		}
	}

	public void salvar() throws Exception {
		try {
			pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Pessoa salva com sucesso");
			
			pessoa = new Pessoa();
			estado = new Estado();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
		}
	}

	public void excluir(ActionEvent evento) {

	}

	public void popular() throws Exception {
		try {
	        cidades = cidadeDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
