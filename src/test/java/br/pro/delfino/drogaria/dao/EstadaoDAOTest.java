package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Ignore;
import org.junit.Test;

import br.com.application.dao.EstadoDAO;
import br.com.application.domain.Estado;

public class EstadaoDAOTest {
	@Test
	@Ignore
	public void salvar() throws HibernateException, Exception {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() throws HibernateException, Exception {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	public void buscar() throws HibernateException, Exception{
		Long codigo = 3L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
}
