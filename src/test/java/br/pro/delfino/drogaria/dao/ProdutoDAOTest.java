package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.junit.Ignore;
import org.junit.Test;

import br.com.application.dao.FabricanteDAO;
import br.com.application.dao.ProdutoDAO;
import br.com.application.domain.Fabricante;
import br.com.application.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() throws NumberFormatException, HibernateException, Exception{
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(new Long("3"));
		
		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 Comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto salvo com sucesso");
	}
}
