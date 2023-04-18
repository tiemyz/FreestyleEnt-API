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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespesaFuncionario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
	private String nomeEquipe;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
	private String nomeLider;

	@NotNull @Min(value = 0, message = "Deve ser positivo")
	private BigDecimal valorFuncionario;

	@NotNull @PastOrPresent
	private LocalDate dataCadastroFuncionario;

	@NotNull @PastOrPresent
	private LocalDate diaServico;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
	private String detalhesFuncionario;

	@ManyToOne
	private ContaCadastro contaCadastro;
}