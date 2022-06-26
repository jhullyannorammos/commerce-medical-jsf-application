package br.com.application.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.application.dao.FabricanteDAO;
import br.com.application.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	
	private FabricanteDAO fabricanteDao;
	private List<Fabricante> fabricantes;
	private Fabricante fabricante;

	@PostConstruct
	public void listar() throws Exception {
		
		fabricanteDao = new FabricanteDAO();
		try {
			fabricantes = fabricanteDao.listar();
 		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void salvar() throws Exception {
		fabricanteDao = new FabricanteDAO();
		try {
			fabricanteDao.merge(fabricante);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o fabricante");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Fabricante salvo com sucesso");
			listar();
		}
	}

	public void excluir(ActionEvent evento) throws Exception {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			fabricanteDao.excluir(fabricante);
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o fabricante");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Fabricante removido com sucesso");
			listar();
		}
	}

	public void editar(ActionEvent evento) throws Exception {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			fabricanteDao.merge(fabricante);
		} catch(RuntimeException exception) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o fabricante");
			exception.printStackTrace();
		} finally {
			Messages.addGlobalInfo("Fabricante editado com sucesso");
			listar();
		}
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}
