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
@RequestMapping("/api/v1/conta_cadastro")
@Slf4j
public class ContaController {
    
    @Autowired
    ContaRepository repository;
    
    @GetMapping
    public List<Conta> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Conta> create(
            @RequestBody @Valid Conta contaCadastro,
            BindingResult result        
        ){
        log.info("Cadastrando conta...." + contaCadastro);
        repository.save(contaCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCadastro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("Buscando conta...." + id);
        return ResponseEntity.ok(getContaCadastro(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id){
        log.info("Apagando conta...." + id);
        repository.delete(getContaCadastro(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> update(
        @PathVariable Long id,
        @RequestBody @Valid Conta contaCadastro
    ){
        log.info("Atualizando conta...." + id);
        getContaCadastro(id);
        contaCadastro.setId(id);
        repository.save(contaCadastro);
        return ResponseEntity.ok(contaCadastro);
    }

    private Conta getContaCadastro(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Conta não encontrada."));
    }
}
