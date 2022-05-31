package br.com.application.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.HibernateException;
import br.com.application.util.HibernateUtil;

public class HibernateContexto implements ServletContextListener {

	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			HibernateUtil.getSessionFactory().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		try {
			HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
