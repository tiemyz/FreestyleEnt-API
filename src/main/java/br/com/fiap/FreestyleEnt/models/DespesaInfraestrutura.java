package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaInfraestrutura {
	
    private Long id;
    private String tipoEstrutura;
    private String empresa;
    private BigDecimal valorEstrutura;
    private LocalDate dataCadastroEstrutura;
    private String detalhesEstrutura;
    
	public DespesaInfraestrutura(Long id, String tipoEstrutura, String empresa, BigDecimal valorEstrutura,
		LocalDate dataCadastroEstrutura, String detalhesEstrutura) {
                
		this.id = id;
		this.tipoEstrutura = tipoEstrutura;
		this.empresa = empresa;
		this.valorEstrutura = valorEstrutura;
		this.dataCadastroEstrutura = dataCadastroEstrutura;
		this.detalhesEstrutura = detalhesEstrutura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoEstrutura() {
		return tipoEstrutura;
	}

	public void setTipoEstrutura(String tipoEstrutura) {
		this.tipoEstrutura = tipoEstrutura;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getValorEstrutura() {
		return valorEstrutura;
	}

	public void setValorEstrutura(BigDecimal valorEstrutura) {
		this.valorEstrutura = valorEstrutura;
	}

	public LocalDate getDataCadastroEstrutura() {
		return dataCadastroEstrutura;
	}

	public void setDataCadastroEstrutura(LocalDate dataCadastroEstrutura) {
		this.dataCadastroEstrutura = dataCadastroEstrutura;
	}

	public String getDetalhesEstrutura() {
		return detalhesEstrutura;
	}

	public void setDetalhesEstrutura(String detalhesEstrutura) {
		this.detalhesEstrutura = detalhesEstrutura;
	}

	@Override
	public String toString() {
		return "DespesaInfraestrutura [id=" + id + ", tipoEstrutura=" + tipoEstrutura + ", empresa=" + empresa
				+ ", valorEstrutura=" + valorEstrutura + ", dataCadastroEstrutura=" + dataCadastroEstrutura
				+ ", detalhesEstrutura=" + detalhesEstrutura + "]";
	}

}