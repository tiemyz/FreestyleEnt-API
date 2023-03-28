package br.com.fiap.FreestyleEnt.controllers;

//import java.math.BigDecimal;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaInfraestrutura;

@RestController
public class DespesaInfraestruturaController {

    Logger log = LoggerFactory.getLogger(DespesaInfraestruturaController.class);

    List<DespesaInfraestrutura> despesasInfraestrutura = new ArrayList<>();

    @GetMapping("/api/v1/despesa_infraestrutura")
    public List<DespesaInfraestrutura> index(){
        return despesasInfraestrutura;
    }

    @PostMapping("/api/v1/despesa_infraestrutura")
    public ResponseEntity<DespesaInfraestrutura> create(@RequestBody DespesaInfraestrutura despesaInfraestrutura){
        log.info("Cadastrando despesa....: " + despesaInfraestrutura);
        despesaInfraestrutura.setId(despesasInfraestrutura.size() + 1L);
        despesasInfraestrutura.add(despesaInfraestrutura);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaInfraestrutura);
    }

    @GetMapping("/api/v1/despesa_infraestrutura/{id}")
    public ResponseEntity<DespesaInfraestrutura> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = despesasInfraestrutura.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("/api/v1/despesa_infraestrutura/{id}")
    public ResponseEntity<DespesaInfraestrutura> destroy(@PathVariable Long id){
        log.info("Apagando despesa....: " + id);

        var despesaEncontrada = despesasInfraestrutura.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasInfraestrutura.remove(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/despesa_infraestrutura/{id}")
    public ResponseEntity<DespesaInfraestrutura> update(@PathVariable Long id, @RequestBody DespesaInfraestrutura despesaInfraestrutura){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = despesasInfraestrutura.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasInfraestrutura.remove(despesaEncontrada.get());
        despesaInfraestrutura.setId(id);
        despesasInfraestrutura.add(despesaInfraestrutura);

        return ResponseEntity.ok(despesaInfraestrutura);
    }
}
