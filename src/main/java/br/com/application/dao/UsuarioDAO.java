package br.com.application.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.application.domain.Usuario;
import br.com.application.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	private Usuario usuario;
	private Session session;
	
	@SuppressWarnings("finally")
	public Usuario autenticar(String cpf, String senha) throws HibernateException, Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria consulta = session.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", senha));
			usuario = (Usuario) consulta.uniqueResult();
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			session.close();
			return usuario;
		}
	}
	
	@SuppressWarnings("finally")
	public Usuario getUsuario(String logon, String password) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = (Usuario) session.createQuery("from Usuario where logon = :logon and password = :password")
					.setParameter("password", password)
					.setParameter("logon", logon)
					.getSingleResult();
		} catch (Exception exception) {
			exception.printStackTrace();
		}finally {
			return usuario;
		}
	}
	
	@SuppressWarnings("finally")
	public Usuario getUsuario(String logon) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuario = (Usuario) session.createQuery("from Usuario where logon = :logon")
					.setParameter("logon", logon)
					.getSingleResult();
		} catch (Exception exception) {
			exception.printStackTrace();
		}finally {
			return usuario;
		}
	}
}
