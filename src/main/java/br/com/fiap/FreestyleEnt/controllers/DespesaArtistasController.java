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
import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesa_art")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "despesa artistas")
public class DespesaArtistasController {

    @Autowired
    DespesaArtistasRepository despesaArtRepository; 

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        var despesaArtistas = (busca == null) ? 
            despesaArtRepository.findAll(pageable): 
            despesaArtRepository.findByArtistaContaining(busca, pageable);

        return assembler.toModel(despesaArtistas.map(DespesaArtistas::toEntityModel)); 
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "a despesa foi cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "os dados enviados são inválidos")
    })
    public ResponseEntity<EntityModel<DespesaArtistas>> create(
            @RequestBody @Valid DespesaArtistas despesaArtistas,
            BindingResult result) {
        log.info("Cadastrando despesa...." + despesaArtistas);
        despesaArtRepository.save(despesaArtistas);
        despesaArtistas.setConta(contaRepository.findById(despesaArtistas.getConta().getId()).get());
        return ResponseEntity
            .created(despesaArtistas.toEntityModel().getRequiredLink("self").toUri())
            .body(despesaArtistas.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes da despesa",
        description = "Retornar os dados da despesa de acordo com o id informado no path"
    )
    public EntityModel<DespesaArtistas> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return getDespesaArtistas(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaArtistas> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaArtRepository.delete(getDespesaArtistas(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<DespesaArtistas>> update(
            @PathVariable Long id,
            @RequestBody @Valid DespesaArtistas despesaArtistas) {
        log.info("Atualizando despesa...." + id);
        getDespesaArtistas(id);
        despesaArtistas.setId(id);
        despesaArtRepository.save(despesaArtistas);
        return ResponseEntity.ok(despesaArtistas.toEntityModel());
    }

    private DespesaArtistas getDespesaArtistas(Long id) {
        return despesaArtRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}
