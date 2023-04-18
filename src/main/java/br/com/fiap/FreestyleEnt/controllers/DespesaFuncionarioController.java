package br.com.fiap.FreestyleEnt.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.ContaCadastroRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/despesa_funcionario")
public class DespesaFuncionarioController {

    Logger log = LoggerFactory.getLogger(DespesaFuncionarioController.class);

    @Autowired
    DespesaFuncionarioRepository despesaFuncRepository;

    @Autowired
    ContaCadastroRepository contaRepository;

    @GetMapping
    public List<DespesaFuncionario> index(){
        return despesaFuncRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<DespesaFuncionario> create(
            @RequestBody @Valid DespesaFuncionario despesaFuncionario,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaFuncionario);
        despesaFuncRepository.save(despesaFuncionario);
        despesaFuncionario.setContaCadastro(contaRepository.findById(despesaFuncionario.getContaCadastro().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaFuncionario);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaFuncionario> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getDespesaFuncionario(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaFuncionario> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaFuncRepository.delete(getDespesaFuncionario(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaFuncionario> update(
        @PathVariable Long id, 
        @RequestBody @Valid DespesaFuncionario despesaFuncionario
    ){
        log.info("Atualizando despesa...." + id);
        getDespesaFuncionario(id);
        despesaFuncionario.setId(id);
        despesaFuncRepository.save(despesaFuncionario);
        return ResponseEntity.ok(despesaFuncionario);
    }

    private DespesaFuncionario getDespesaFuncionario(Long id) {
        return despesaFuncRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}