package br.com.application.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.application.domain.ItemVenda;
import br.com.application.domain.Produto;
import br.com.application.domain.Venda;
import br.com.application.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	
	public void salvar(Venda venda, List<ItemVenda> itensVenda) throws HibernateException, Exception {
		sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			transacao = sessao.beginTransaction();
			sessao.save(venda);

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);

				sessao.save(itemVenda);

				Produto produto = itemVenda.getProduto();
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				if (quantidade >= 0) {
					produto.setQuantidade(new Short(quantidade + ""));
					sessao.update(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
			}

			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
