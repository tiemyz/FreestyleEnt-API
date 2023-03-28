package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagarDespesas {
	
	private Long id;
	private BigDecimal valorDespesas;
	private LocalDate dataPagamento;
	private String detalhesDespesas;
	
	public PagarDespesas(Long id, BigDecimal valorDespesas, LocalDate dataPagamento, String detalhesDespesas) {
		
		this.id = id;
		this.valorDespesas = valorDespesas;
		this.dataPagamento = dataPagamento;
		this.detalhesDespesas = detalhesDespesas;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValorDespesas() {
		return valorDespesas;
	}
	public void setValorDespesas(BigDecimal valorDespesas) {
		this.valorDespesas = valorDespesas;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDetalhesDespesas() {
		return detalhesDespesas;
	}
	public void setDetalhesDespesas(String detalhesDespesas) {
		this.detalhesDespesas = detalhesDespesas;
	}
	
	@Override
	public String toString() {
		return "PagarDespesas [id=" + id + ", valorDespesas=" + valorDespesas + ", dataPagamento=" + dataPagamento
				+ ", detalhesDespesas=" + detalhesDespesas + "]";
	}
}