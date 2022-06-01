package br.com.application.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.application.bean.tools.ReportBean;
import br.com.application.dao.CidadeDAO;
import br.com.application.dao.EstadoDAO;
import br.com.application.domain.Cidade;
import br.com.application.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean extends ReportBean {
	
	private Estado estadoSelecionado;
	private List<Cidade> cidades;
	private List<Estado> estados;
	private Cidade cidade;
	
	@PostConstruct
	public void initialization() throws Exception {
		listar();
	}
	
	public void listar() throws Exception {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}

	public void novo() throws Exception {
		try {
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidade");
			erro.printStackTrace();
		}finally {
			Messages.addGlobalInfo("Cidade salva com sucesso");
		}
	}

	public void excluir(ActionEvent evento) throws Exception {
		cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
			erro.printStackTrace();
		}finally {
			Messages.addGlobalInfo("Cidade removida com sucesso");
		}
	}
	
	public void editar(ActionEvent evento) throws Exception{
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}finally {
			Messages.addGlobalInfo("Cidade atualizado com sucesso");
		}
	}
	
	public void cidadesBy(ActionEvent evento) throws Exception {
		estadoSelecionado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			cidades = estadoDAO.listarCidadesBy(estadoSelecionado.getCodigo());
			
			HashMap paramRel = new HashMap();
			
			ReportGeneratorToPDF("Cidades do estado " + estadoSelecionado.getNome() , paramRel, cidades);
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			
		}
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
