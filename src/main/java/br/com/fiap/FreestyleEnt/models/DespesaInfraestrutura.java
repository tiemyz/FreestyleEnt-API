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
public class DespesaInfraestrutura {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank @Size(min = 5, max = 200, message = "Deve ser uma descrição significativa")
    private String tipoEstrutura;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
    private String empresa;

	@NotNull @Min(value = 0, message = "Deve ser positivo")
    private BigDecimal valorEstrutura;

	@NotNull @PastOrPresent
    private LocalDate dataCadastroEstrutura;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
    private String detalhesEstrutura;

	protected DespesaInfraestrutura(){}
    
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