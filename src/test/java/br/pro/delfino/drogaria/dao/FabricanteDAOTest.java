package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Ignore;
import org.junit.Test;

import br.com.application.dao.FabricanteDAO;
import br.com.application.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() throws HibernateException, Exception {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Aché");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}

	@Test
	@Ignore
	public void listar() throws HibernateException, Exception {
		FabricanteDAO FabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = FabricanteDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Fabricante fabricante : resultado) {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void buscar() throws HibernateException, Exception{
		Long codigo = 3L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void merge() throws HibernateException, Exception {
		//Fabricante fabricante = new Fabricante();
		//fabricante.setDescricao("Fabricante A");
		//FabricanteDAO fabricanteDAO = new FabricanteDAO();
		//fabricanteDAO.merge(fabricante);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(5L);
		fabricante.setDescricao("Fabricante B");
		fabricanteDAO.merge(fabricante);
	}
}
