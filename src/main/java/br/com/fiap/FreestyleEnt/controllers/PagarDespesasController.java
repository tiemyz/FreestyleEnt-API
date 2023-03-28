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

import br.com.fiap.FreestyleEnt.models.PagarDespesas;

@RestController
public class PagarDespesasController {

    Logger log = LoggerFactory.getLogger(PagarDespesasController.class);

    List<PagarDespesas> despesasPagar = new ArrayList<>();

    @GetMapping("/api/v1/pagar_despesas")
    public List<PagarDespesas> index(){
        return despesasPagar;
    }

    @PostMapping("/api/v1/pagar_despesas")
    public ResponseEntity<PagarDespesas> create(@RequestBody PagarDespesas despesaPagar){
        log.info("Cadastrando despesa....: " + despesaPagar);
        despesaPagar.setId(despesasPagar.size() + 1L);
        despesasPagar.add(despesaPagar);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaPagar);
    }

    @GetMapping("/api/v1/pagar_despesas/{id}")
    public ResponseEntity<PagarDespesas> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = despesasPagar.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("/api/v1/pagar_despesas/{id}")
    public ResponseEntity<PagarDespesas> destroy(@PathVariable Long id){
        log.info("Apagando despesa....: " + id);

        var despesaEncontrada = despesasPagar.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasPagar.remove(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/pagar_despesas/{id}")
    public ResponseEntity<PagarDespesas> update(@PathVariable Long id, @RequestBody PagarDespesas despesaPagar){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = despesasPagar.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasPagar.remove(despesaEncontrada.get());
        despesaPagar.setId(id);
        despesasPagar.add(despesaPagar);

        return ResponseEntity.ok(despesaPagar);
    }
}