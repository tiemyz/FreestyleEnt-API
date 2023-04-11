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
public class DespesaArtistas {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	@NotNull @Min(value = 0, message = "Deve ser positivo")
    private BigDecimal valor; 

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
    private String artista;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
    private String gravadora;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
    private String detalhes;

	@NotNull @PastOrPresent
    private LocalDate dataCadastro; 

	@NotNull @PastOrPresent
    private LocalDate dataApresentacao;

	protected DespesaArtistas(){}

	public DespesaArtistas(Long id, BigDecimal valor, String artista, String gravadora, String detalhes, LocalDate dataCadastro, LocalDate dataApresentacao) {
		
		this.id = id;
		this.valor = valor;
		this.artista = artista;
		this.gravadora = gravadora;
		this.detalhes = detalhes;
		this.dataCadastro = dataCadastro;
		this.dataApresentacao = dataApresentacao;
	}

	public DespesaArtistas(long l, BigDecimal bigDecimal, LocalDate now, String string) {
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getGravadora() {
		return gravadora;
	}
	public void setGravadora(String gravadora) {
		this.gravadora = gravadora;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public LocalDate getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(LocalDate dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	
	@Override
	public String toString() {
		return "DespesaArtistas [id=" + id + ", valor=" + valor + ", artista=" + artista + ", gravadora=" + gravadora
				+ ", detalhes=" + detalhes + ", dataCadastro=" + dataCadastro + ", dataApresentacao=" + dataApresentacao
				+ "]";
	} 

}


