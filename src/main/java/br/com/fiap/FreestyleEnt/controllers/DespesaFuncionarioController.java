package br.com.fiap.FreestyleEnt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.exception.RestNotFoundException;
import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesa_funcionario")
@Slf4j
public class DespesaFuncionarioController {

    @Autowired
    DespesaFuncionarioRepository despesaFuncRepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<DespesaFuncionario> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        if (busca == null)
            return despesaFuncRepository.findAll(pageable);
        return despesaFuncRepository.findByIdContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<DespesaFuncionario> create(
            @RequestBody @Valid DespesaFuncionario despesaFuncionario,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaFuncionario);
        despesaFuncRepository.save(despesaFuncionario);
        despesaFuncionario.setConta(contaRepository.findById(despesaFuncionario.getConta().getId()).get());
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