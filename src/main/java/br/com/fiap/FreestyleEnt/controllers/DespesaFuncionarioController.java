package br.com.fiap.FreestyleEnt.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.FreestyleEnt.exception.RestNotFoundException;
import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesa_func")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "despesa funcionário")
public class DespesaFuncionarioController {

    @Autowired
    DespesaFuncionarioRepository despesaFuncRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        var despesaFuncionario = (busca == null) ? 
            despesaFuncRepository.findAll(pageable): 
            despesaFuncRepository.findByNomeEquipeContaining(busca, pageable);

        return assembler.toModel(despesaFuncionario.map(DespesaFuncionario::toEntityModel));
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "a despesa foi cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "os dados enviados são inválidos")
    })
    public ResponseEntity<EntityModel<DespesaFuncionario>> create(
            @RequestBody @Valid DespesaFuncionario despesaFuncionario,
            BindingResult result) {
        log.info("Cadastrando despesa...." + despesaFuncionario);
        despesaFuncRepository.save(despesaFuncionario);
        despesaFuncionario.setConta(contaRepository.findById(despesaFuncionario.getConta().getId()).get());
        return ResponseEntity
            .created(despesaFuncionario.toEntityModel().getRequiredLink("self").toUri())
            .body(despesaFuncionario.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes da despesa",
        description = "Retornar os dados da despesa de acordo com o id informado no path"
    )
    public EntityModel<DespesaFuncionario> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return getDespesaFuncionario(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaFuncionario> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaFuncRepository.delete(getDespesaFuncionario(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<DespesaFuncionario>> update(
            @PathVariable Long id, 
            @RequestBody @Valid DespesaFuncionario despesaFuncionario) {
        log.info("Atualizando despesa...." + id);
        getDespesaFuncionario(id);
        despesaFuncionario.setId(id);
        despesaFuncRepository.save(despesaFuncionario);
        return ResponseEntity.ok(despesaFuncionario.toEntityModel());
    }

    private DespesaFuncionario getDespesaFuncionario(Long id) {
        return despesaFuncRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}