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
import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/despesa_artistas")
public class DespesaArtistasController {

    Logger log = LoggerFactory.getLogger(DespesaArtistasController.class);

    @Autowired
    DespesaArtistasRepository repository; 

    @GetMapping
    public List<DespesaArtistas> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DespesaArtistas> create(
            @RequestBody @Valid DespesaArtistas despesaArtistas,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaArtistas);
        repository.save(despesaArtistas);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaArtistas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaArtistas> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getDespesaArtistas(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaArtistas> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        repository.delete(getDespesaArtistas(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaArtistas> update(
        @PathVariable Long id,
        @RequestBody @Valid DespesaArtistas despesaArtistas
    ){
        log.info("Atualizando despesa...." + id);
        getDespesaArtistas(id);
        despesaArtistas.setId(id);
        repository.save(despesaArtistas);
        return ResponseEntity.ok(despesaArtistas);
    }

    private DespesaArtistas getDespesaArtistas(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}
