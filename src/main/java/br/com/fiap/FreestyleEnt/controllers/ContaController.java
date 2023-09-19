package br.com.fiap.FreestyleEnt.controllers;

import java.util.List;
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
import br.com.fiap.FreestyleEnt.models.Conta;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/conta")
@Slf4j
public class ContaController {
    
    @Autowired
    ContaRepository repository; 

    @GetMapping
    public List<Conta> index(){
        log.info("buscando todas as contas");
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Conta> create(
            @RequestBody @Valid Conta conta, 
            BindingResult result
        ){
        log.info("cadastrando conta: " + conta);
        repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("buscando conta: " + id);
        return ResponseEntity.ok(getConta(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id){
        log.info("apagando conta: " + id);
        repository.delete(getConta(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> update(
        @PathVariable Long id, 
        @RequestBody @Valid Conta conta
    ){
        log.info("atualizando conta: " + id);
        getConta(id);
        conta.setId(id);
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }

    private Conta getConta(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("conta não encontrada"));
    }
}
