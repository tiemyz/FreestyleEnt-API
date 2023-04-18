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
import br.com.fiap.FreestyleEnt.models.ContaCadastro;
import br.com.fiap.FreestyleEnt.repository.ContaCadastroRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/conta_cadastro")
@Slf4j
public class ContaCadastroController {
    
    @Autowired
    ContaCadastroRepository repository;

    @GetMapping
    public List<ContaCadastro> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<ContaCadastro> create(
            @RequestBody @Valid ContaCadastro contaCadastro,
            BindingResult result        
        ){
        log.info("Cadastrando conta...." + contaCadastro);
        repository.save(contaCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCadastro);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContaCadastro> show(@PathVariable Long id){
        log.info("Buscando conta...." + id);
        return ResponseEntity.ok(getContaCadastro(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ContaCadastro> destroy(@PathVariable Long id){
        log.info("Apagando conta...." + id);
        repository.delete(getContaCadastro(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaCadastro> update(
        @PathVariable Long id,
        @RequestBody @Valid ContaCadastro contaCadastro
    ){
        log.info("Atualizando conta...." + id);
        getContaCadastro(id);
        contaCadastro.setId(id);
        repository.save(contaCadastro);
        return ResponseEntity.ok(contaCadastro);
    }

    private ContaCadastro getContaCadastro(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Conta não encontrada."));
    }
}
