package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.application.dao.CaixaDAO;
import br.com.application.domain.Caixa;

public class CaixaDAOTest {
	@Test
	@Ignore
	public void salvar() throws HibernateException, Exception {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("14/12/2015"));
		caixa.setValorAbertura(new BigDecimal("100.00"));
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
	}

	@Test
	@Ignore
	public void buscar() throws HibernateException, Exception {
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2015"));
		System.out.println(caixa);
		Assert.assertNull(caixa);
	}
}
