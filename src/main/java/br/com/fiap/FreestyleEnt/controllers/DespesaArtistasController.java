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

import br.com.fiap.FreestyleEnt.models.DespesaArtistas;

@RestController
public class DespesaArtistasController {

    Logger log = LoggerFactory.getLogger(DespesaArtistasController.class);

    List<DespesaArtistas> despesasArtistas = new ArrayList<>();

    @GetMapping("/api/v1/despesa_artistas")
    public List<DespesaArtistas> index(){
        return despesasArtistas;
    }

    @PostMapping("/api/v1/despesa_artistas")
    public ResponseEntity<DespesaArtistas> create(@RequestBody DespesaArtistas despesaArtistas){
        log.info("Cadastrando despesa....: " + despesaArtistas);
        despesaArtistas.setId(despesasArtistas.size() + 1L);
        despesasArtistas.add(despesaArtistas);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaArtistas);
    }

    @GetMapping("/api/v1/despesa_artistas/{id}")
    public ResponseEntity<DespesaArtistas> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = despesasArtistas.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("/api/v1/despesa_artistas/{id}")
    public ResponseEntity<DespesaArtistas> destroy(@PathVariable Long id){
        log.info("Apagando despesa: " + id);

        var despesaEncontrada = despesasArtistas.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasArtistas.remove(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/despesa_artistas/{id}")
    public ResponseEntity<DespesaArtistas> update(@PathVariable Long id, @RequestBody DespesaArtistas despesaArtistas){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = despesasArtistas.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasArtistas.remove(despesaEncontrada.get());
        despesaArtistas.setId(id);
        despesasArtistas.add(despesaArtistas);

        return ResponseEntity.ok(despesaArtistas);
    }
}
