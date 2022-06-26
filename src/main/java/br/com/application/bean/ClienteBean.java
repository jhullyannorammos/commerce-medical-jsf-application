package br.com.application.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.application.bean.tools.ReportBean;
import br.com.application.dao.ClienteDAO;
import br.com.application.dao.PessoaDAO;
import br.com.application.domain.Cliente;
import br.com.application.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean extends ReportBean {
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Pessoa> pessoas;
	private ClienteDAO clienteDAO;

	@PostConstruct
	public void listar() throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		try {
			clientes = clienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			
			clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("dataCadastro");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Cliente salvo com sucesso");
			clientes = clienteDAO.listar("dataCadastro");
			cliente = new Cliente();
		}
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
