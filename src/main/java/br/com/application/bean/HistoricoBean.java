package br.com.application.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.application.dao.HistoricoDAO;
import br.com.application.dao.MedicacaoDAO;
import br.com.application.domain.Historico;
import br.com.application.domain.Medicacao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	private Medicacao produto;
	private Boolean exibePainelDados;
	
	private Historico historico;

	
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Medicacao();
		exibePainelDados = false;
	}

	/*
	public void buscar() throws Exception {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());

			if (resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				exibePainelDados = true;
				produto = resultado;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);
			
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);
			
			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			erro.printStackTrace();
		}
	}
	
	*/
	
	public Medicacao getProduto() {
		return produto;
	}

	public void setProduto(Medicacao produto) {
		this.produto = produto;
	}

	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}
	
	public Historico getHistorico() {
		return historico;
	}
}
