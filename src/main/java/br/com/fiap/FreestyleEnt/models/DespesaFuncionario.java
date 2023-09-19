package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.FreestyleEnt.controllers.ContaController;
import br.com.fiap.FreestyleEnt.controllers.DespesaFuncionarioController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespesaFuncionario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
	private String nomeEquipe;

	@NotBlank @Size(min = 5, max = 50, message = "Nome completo, sem abreviações")
	private String nomeLider;

	@NotNull @Min(value = 0, message = "Deve ser positivo")
	private BigDecimal valorFunc;

	@NotNull @PastOrPresent
	private LocalDate dataCadastroFunc;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
	private String detalhesFunc;

	@ManyToOne
	private Conta conta;

	public EntityModel<DespesaFuncionario> toEntityModel() {
        return EntityModel.of(
            this, 
            linkTo(methodOn(DespesaFuncionarioController.class).show(id)).withSelfRel(),
            linkTo(methodOn(DespesaFuncionarioController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(DespesaFuncionarioController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ContaController.class).show(this.getConta().getId())).withRel("conta")
        );
    }
}