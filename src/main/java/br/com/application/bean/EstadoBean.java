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
import br.com.application.dao.EstadoDAO;
import br.com.application.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean extends ReportBean {
	
	private EstadoDAO estadoDAO;
	private List<Estado> estados;
	private Estado estado;
	
	@PostConstruct
	public void initialization() throws Exception {
		listar();
	}

	public void listar() throws Exception {
		try {
			estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() throws Exception {
		try {
			estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			estado = new Estado();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}finally {
			Messages.addGlobalInfo("Estado salvo com sucesso");
		}
	}

	public void excluir(ActionEvent evento) throws Exception {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			estados = estadoDAO.listar();
			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) throws Exception {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
		    estadoDAO = new EstadoDAO();
		    estadoDAO.merge(estado);
		    estados = estadoDAO.listar();
		    Messages.addGlobalInfo("Estado atualizado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}
	
	public void report(ActionEvent evento) throws Exception {
		//estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		try {
			estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			
			HashMap paramRel = new HashMap();
			
			ReportGeneratorToPDF("Estados", paramRel, estados);
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			
		}
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

}
