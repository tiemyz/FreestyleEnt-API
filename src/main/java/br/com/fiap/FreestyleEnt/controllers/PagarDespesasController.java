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
import br.com.fiap.FreestyleEnt.models.PagarDespesas;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.PagarDespesasRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/pagar_despesas")
@Slf4j
public class PagarDespesasController {

    @Autowired
    PagarDespesasRepository despesaPagarRepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<PagarDespesas> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        if (busca == null)
            return despesaPagarRepository.findAll(pageable);
        return despesaPagarRepository.findByIdContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<PagarDespesas> create(
            @RequestBody @Valid PagarDespesas despesaPagar,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaPagar);
        despesaPagarRepository.save(despesaPagar);
        despesaPagar.setConta(contaRepository.findById(despesaPagar.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaPagar);
    }

    @GetMapping("{id}")
    public ResponseEntity<PagarDespesas> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getPagarDespesas(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PagarDespesas> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaPagarRepository.delete(getPagarDespesas(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PagarDespesas> update(
        @PathVariable Long id, 
        @RequestBody @Valid PagarDespesas despesaPagar
    ){
        log.info("Atualizando despesa...." + id);
        getPagarDespesas(id);
        despesaPagar.setId(id);
        despesaPagarRepository.save(despesaPagar);
        return ResponseEntity.ok(despesaPagar);
    }

    private PagarDespesas getPagarDespesas(Long id) {
        return despesaPagarRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}