package br.com.fiap.FreestyleEnt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import br.com.fiap.FreestyleEnt.models.DespesaInfraestrutura;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaInfraestruturaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesa_infra")
@Slf4j
public class DespesaInfraestruturaController {

    @Autowired
    DespesaInfraestruturaRepository despesaInfraRepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<DespesaInfraestrutura> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        if (busca == null)
            return despesaInfraRepository.findAll(pageable);
        return despesaInfraRepository.findByIdContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<DespesaInfraestrutura> create(
            @RequestBody @Valid DespesaInfraestrutura despesaInfraestrutura,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaInfraestrutura);
        despesaInfraRepository.save(despesaInfraestrutura);
        despesaInfraestrutura.setConta(contaRepository.findById(despesaInfraestrutura.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaInfraestrutura);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaInfraestrutura> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getDespesaInfraestrutura(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaInfraestrutura> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaInfraRepository.delete(getDespesaInfraestrutura(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaInfraestrutura> update(
        @PathVariable Long id, 
        @RequestBody @Valid DespesaInfraestrutura despesaInfraestrutura
    ){
        log.info("Atualizando despesa...." + id);
        getDespesaInfraestrutura(id);
        despesaInfraestrutura.setId(id);
        despesaInfraRepository.save(despesaInfraestrutura);
        return ResponseEntity.ok(despesaInfraestrutura);
    }

    private DespesaInfraestrutura getDespesaInfraestrutura(Long id) {
        return despesaInfraRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}
