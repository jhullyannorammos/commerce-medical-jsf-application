package br.com.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Historico extends GenericDomain {
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	@Column(nullable = false, length = 500)
	private String observacoes;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Medicacao produto;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Medicacao getProduto() {
		return produto;
	}

	public void setProduto(Medicacao produto) {
		this.produto = produto;
	}
}
