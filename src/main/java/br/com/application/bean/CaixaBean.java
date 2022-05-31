package br.com.application.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.application.dao.CaixaDAO;
import br.com.application.dao.FuncionarioDAO;
import br.com.application.domain.Caixa;
import br.com.application.domain.Funcionario;

@ManagedBean
@ViewScoped
public class CaixaBean {
	private Caixa caixa;

	private ScheduleModel caixas;
	private List<Funcionario> funcionarios;

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}

	public void novo(SelectEvent evento) throws HibernateException, Exception {
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
	}
	
	public void salvar() throws HibernateException, Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(caixa.getDataAbertura());
		calendar.add(Calendar.DATE, 1);
		caixa.setDataAbertura(calendar.getTime());
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}
}
