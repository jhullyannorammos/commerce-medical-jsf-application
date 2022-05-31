package br.com.application.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
private static SessionFactory factory = getSession();
	
    public HibernateUtil() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() throws Exception {
        if(factory == null){
            Configuration configuration = new Configuration().configure();
            factory = configuration.buildSessionFactory();
        }
        return factory;
    }
	
	private static SessionFactory getSession() {
		return factory;
	}
	
	public static Connection getConnection() throws HibernateException, Exception {
		Session session = getSessionFactory().openSession();
		Connection connection = session.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException {
				return conn;
			}
		});
		return connection;
	}




	
}
