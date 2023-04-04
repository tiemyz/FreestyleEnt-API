package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DespesaFuncionario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeEquipe;
	private String nomeLider;
	private BigDecimal valorFuncionario;
	private LocalDate dataCadastroFuncionario;
	private LocalDate diaServico;
	private String detalhesFuncionario;

	protected DespesaFuncionario(){}
	
	public DespesaFuncionario(Long id, String nomeEquipe, String nomeLider, BigDecimal valorFuncionario,
		LocalDate dataCadastroFuncionario, LocalDate diaServico, String detalhesFuncionario) {

		this.id = id;
		this.nomeEquipe = nomeEquipe;
		this.nomeLider = nomeLider;
		this.valorFuncionario = valorFuncionario;
		this.dataCadastroFuncionario = dataCadastroFuncionario;
		this.diaServico = diaServico;
		this.detalhesFuncionario = detalhesFuncionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getNomeLider() {
		return nomeLider;
	}

	public void setNomeLider(String nomeLider) {
		this.nomeLider = nomeLider;
	}

	public BigDecimal getValorFuncionario() {
		return valorFuncionario;
	}

	public void setValorFuncionario(BigDecimal valorFuncionario) {
		this.valorFuncionario = valorFuncionario;
	}

	public LocalDate getDataCadastroFuncionario() {
		return dataCadastroFuncionario;
	}

	public void setDataCadastroFuncionario(LocalDate dataCadastroFuncionario) {
		this.dataCadastroFuncionario = dataCadastroFuncionario;
	}

	public LocalDate getDiaServico() {
		return diaServico;
	}

	public void setDiaServico(LocalDate diaServico) {
		this.diaServico = diaServico;
	}

	public String getDetalhesFuncionario() {
		return detalhesFuncionario;
	}

	public void setDetalhesFuncionario(String detalhesFuncionario) {
		this.detalhesFuncionario = detalhesFuncionario;
	}

	@Override
	public String toString() {
		return "DespesaFuncionario [id=" + id + ", nomeEquipe=" + nomeEquipe + ", nomeLider=" + nomeLider
				+ ", valorFuncionario=" + valorFuncionario + ", dataCadastroFuncionario=" + dataCadastroFuncionario
				+ ", diaServico=" + diaServico + ", detalhesFuncionario=" + detalhesFuncionario + "]";
	}
	
}



	
