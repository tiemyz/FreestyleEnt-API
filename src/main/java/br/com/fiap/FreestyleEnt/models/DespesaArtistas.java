package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespesaArtistas {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	@NotNull @Min(value = 0, message = "Deve ser positivo")
    private BigDecimal valorArt; 

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
    private String artista;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
    private String gravadora;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
    private String detalhesArt;

	@NotNull @PastOrPresent
    private LocalDate dataCadastroArt; 

	@ManyToOne
	private Conta conta;
}