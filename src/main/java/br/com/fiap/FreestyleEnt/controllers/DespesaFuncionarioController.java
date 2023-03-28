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

import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;

@RestController
public class DespesaFuncionarioController {

    Logger log = LoggerFactory.getLogger(DespesaFuncionarioController.class);

    List<DespesaFuncionario> despesasFuncionario = new ArrayList<>();

    @GetMapping("/api/v1/despesa_funcionario")
    public List<DespesaFuncionario> index(){
        return despesasFuncionario;
    }

    @PostMapping("/api/v1/despesa_funcionario")
    public ResponseEntity<DespesaFuncionario> create(@RequestBody DespesaFuncionario despesaFuncionario){
        log.info("Cadastrando despesa....: " + despesaFuncionario);
        despesaFuncionario.setId(despesasFuncionario.size() + 1L);
        despesasFuncionario.add(despesaFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaFuncionario);
    }

    @GetMapping("/api/v1/despesa_funcionario/{id}")
    public ResponseEntity<DespesaFuncionario> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = despesasFuncionario.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("/api/v1/despesa_funcionario/{id}")
    public ResponseEntity<DespesaFuncionario> destroy(@PathVariable Long id){
        log.info("Apagando despesa: " + id);

        var despesaEncontrada = despesasFuncionario.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasFuncionario.remove(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/despesa_funcionario/{id}")
    public ResponseEntity<DespesaFuncionario> update(@PathVariable Long id, @RequestBody DespesaFuncionario despesaFuncionario){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = despesasFuncionario.stream().filter(d -> d.getId().equals(id)).findFirst();

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        despesasFuncionario.remove(despesaEncontrada.get());
        despesaFuncionario.setId(id);
        despesasFuncionario.add(despesaFuncionario);

        return ResponseEntity.ok(despesaFuncionario);
    }
}