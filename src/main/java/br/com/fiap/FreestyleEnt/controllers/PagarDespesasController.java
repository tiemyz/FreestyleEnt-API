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
import br.com.fiap.FreestyleEnt.models.PagarDespesas;
import br.com.fiap.FreestyleEnt.repository.PagarDespesasRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagar_despesas")
public class PagarDespesasController {

    Logger log = LoggerFactory.getLogger(PagarDespesasController.class);

    @Autowired
    PagarDespesasRepository repository;

    @GetMapping
    public List<PagarDespesas> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<PagarDespesas> create(
            @RequestBody @Valid PagarDespesas despesaPagar,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaPagar);
        repository.save(despesaPagar);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaPagar);
    }

    @GetMapping("{id}")
    public ResponseEntity<PagarDespesas> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getPagarDespesas(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PagarDespesas> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        repository.delete(getPagarDespesas(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PagarDespesas> update(
        @PathVariable Long id, 
        @RequestBody @Valid PagarDespesas despesaPagar
    ){
        log.info("Atualizando despesa...." + id);
        getPagarDespesas(id);
        despesaPagar.setId(id);
        repository.save(despesaPagar);
        return ResponseEntity.ok(despesaPagar);
    }

    private PagarDespesas getPagarDespesas(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}