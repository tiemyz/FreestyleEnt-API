package br.com.fiap.FreestyleEnt.controllers;

//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.exception.RestNotFoundException;
import br.com.fiap.FreestyleEnt.models.DespesaInfraestrutura;
import br.com.fiap.FreestyleEnt.repository.DespesaInfraestruturaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/despesa_infraestrutura")
public class DespesaInfraestruturaController {

    Logger log = LoggerFactory.getLogger(DespesaInfraestruturaController.class);

    @Autowired
    DespesaInfraestruturaRepository repository;

    @GetMapping
    public List<DespesaInfraestrutura> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DespesaInfraestrutura> create(
            @RequestBody @Valid DespesaInfraestrutura despesaInfraestrutura,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaInfraestrutura);
        repository.save(despesaInfraestrutura);
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
        repository.delete(getDespesaInfraestrutura(id));
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
        repository.save(despesaInfraestrutura);
        return ResponseEntity.ok(despesaInfraestrutura);
    }

    private DespesaInfraestrutura getDespesaInfraestrutura(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}
