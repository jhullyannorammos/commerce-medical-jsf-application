package br.com.application.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemVenda extends GenericDomain {
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal precoParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Medicacao produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda venda;

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Medicacao getProduto() {
		return produto;
	}

	public void setProduto(Medicacao produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
