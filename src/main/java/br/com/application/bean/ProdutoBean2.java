package br.com.application.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.application.dao.FabricanteDAO;
import br.com.application.dao.ProdutoDAO;
import br.com.application.domain.Fabricante;
import br.com.application.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2  implements Serializable {
	
	private Produto produto;
	private Long codigoProduto;
	
	private List<Fabricante> fabricantes;
	private List<Produto> produtos;
	
	private FabricanteDAO fabricanteDAO;
	private ProdutoDAO produtoDAO;
	
	@PostConstruct
	public void iniciar(){
		fabricanteDAO = new FabricanteDAO();
		produtoDAO = new ProdutoDAO();
	}
	
	public void listar() throws Exception {
		try {
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	public void carregarEdicao() throws Exception{
		try {
			produto = produtoDAO.buscar(codigoProduto);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados para edição");
			erro.printStackTrace();
		} finally {
			fabricantes = fabricanteDAO.listar("descricao");
		}
	}
	
	public void exibePainelDados() {
		
	}
	
    public void buscar() {
		
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Long getCodigoProduto() {
		return codigoProduto;
	}
	
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
