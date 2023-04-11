package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class PagarDespesas {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull @Min(value = 0, message = "Deve ser positivo")
	private BigDecimal valorDespesas;

	@NotNull @PastOrPresent
	private LocalDate dataPagamento;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
	private String detalhesDespesas;

	protected PagarDespesas(){}
	
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