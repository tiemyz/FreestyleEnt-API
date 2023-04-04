package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;

@RestController
@RequestMapping("/api/v1/despesa_artistas")
public class DespesaArtistasController {

    Logger log = LoggerFactory.getLogger(DespesaArtistasController.class);

    @Autowired

    DespesaArtistasRepository repository; //!!!!!!!!!

    @GetMapping
    public List<DespesaArtistas> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DespesaArtistas> create(@RequestBody DespesaArtistas despesaArtistas){
        log.info("Cadastrando despesa....: " + despesaArtistas);
        repository.save(despesaArtistas);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaArtistas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaArtistas> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaArtistas> destroy(@PathVariable Long id){
        log.info("Apagando despesa: " + id);

        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        repository.delete(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaArtistas> update(@PathVariable Long id, @RequestBody DespesaArtistas despesaArtistas){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        //BeanUtils.copyProperties(despesaArtistas, despesaAtualizada, "id");

        despesaArtistas.setId(id);
        repository.save(despesaArtistas);
        return ResponseEntity.ok(despesaArtistas);
    }
}
