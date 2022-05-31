package br.com.application.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.application.domain.Funcionario;
import br.com.application.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() throws HibernateException, Exception {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Funcionario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
}
