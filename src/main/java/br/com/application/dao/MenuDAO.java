package br.com.application.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Test;


import br.com.application.domain.Menu;

public class MenuDAO {
	@Test
	public void listar() throws HibernateException, Exception {
		MenuDAO menuDAO = new MenuDAO();

		List<Menu> lista = null; // = menuDAO.listar();

		for (Menu menu : lista) {
			if (menu.getCaminho() == null) {
				System.out.println(menu.getRotulo() + " - " + menu.getCaminho());
				for (Menu item : menu.getMenus()){
					System.out.println("\t" + item.getRotulo() + " - " + item.getCaminho());
				}
			}
		}
	}
}
