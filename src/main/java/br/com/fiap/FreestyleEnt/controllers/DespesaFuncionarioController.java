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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;

@RestController
@RequestMapping("/api/v1/despesa_funcionario")
public class DespesaFuncionarioController {

    Logger log = LoggerFactory.getLogger(DespesaFuncionarioController.class);

    @Autowired
    DespesaFuncionarioRepository repository;


    @GetMapping
    public List<DespesaFuncionario> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DespesaFuncionario> create(@RequestBody DespesaFuncionario despesaFuncionario){
        log.info("Cadastrando despesa....: " + despesaFuncionario);
        repository.save(despesaFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaFuncionario);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaFuncionario> show(@PathVariable Long id){
        log.info("Buscando despesa....: " + id);
        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaFuncionario> destroy(@PathVariable Long id){
        log.info("Apagando despesa: " + id);

        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        repository.delete(despesaEncontrada.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaFuncionario> update(@PathVariable Long id, @RequestBody DespesaFuncionario despesaFuncionario){
        log.info("Atualizando despesa....: " + id);

        var despesaEncontrada = repository.findById(id);

        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //BeanUtils.copyProperties(despesaFuncionario, despesaAtualizada, "id");

        despesaFuncionario.setId(id);
        repository.save(despesaFuncionario);
        return ResponseEntity.ok(despesaFuncionario);
    }
}