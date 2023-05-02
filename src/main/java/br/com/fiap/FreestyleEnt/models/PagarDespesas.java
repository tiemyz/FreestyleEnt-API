package br.com.fiap.FreestyleEnt.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.FreestyleEnt.controllers.ContaController;
import br.com.fiap.FreestyleEnt.controllers.PagarDespesasController;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagarDespesas {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull @Min(value = 0, message = "Deve ser positivo")
	private BigDecimal valorDesp;

	@NotNull @PastOrPresent
	private LocalDate dataPagamento;

	@NotBlank @Size(min = 5, max = 300, message = "Deve ser uma descrição significativa")
	private String detalhesDesp;

	@ManyToOne
	private Conta conta;

	public EntityModel<PagarDespesas> toEntityModel() {
        return EntityModel.of(
            this, 
            linkTo(methodOn(PagarDespesasController.class).show(id)).withSelfRel(),
            linkTo(methodOn(PagarDespesasController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(PagarDespesasController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ContaController.class).show(this.getConta().getId())).withRel("conta")
        );
    }
}