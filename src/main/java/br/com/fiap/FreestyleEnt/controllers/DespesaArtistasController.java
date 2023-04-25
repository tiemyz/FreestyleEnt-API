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
import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesa_artistas")
@Slf4j
public class DespesaArtistasController {

    @Autowired
    DespesaArtistasRepository despesaArtRepository; 

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<DespesaArtistas> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        if (busca == null)
            return despesaArtRepository.findAll(pageable);
        return despesaArtRepository.findByIdContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<DespesaArtistas> create(
            @RequestBody @Valid DespesaArtistas despesaArtistas,
            BindingResult result
        ){
        log.info("Cadastrando despesa...." + despesaArtistas);
        despesaArtRepository.save(despesaArtistas);
        despesaArtistas.setConta(contaRepository.findById(despesaArtistas.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesaArtistas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DespesaArtistas> show(@PathVariable Long id){
        log.info("Buscando despesa...." + id);
        return ResponseEntity.ok(getDespesaArtistas(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DespesaArtistas> destroy(@PathVariable Long id){
        log.info("Apagando despesa...." + id);
        despesaArtRepository.delete(getDespesaArtistas(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DespesaArtistas> update(
        @PathVariable Long id,
        @RequestBody @Valid DespesaArtistas despesaArtistas
    ){
        log.info("Atualizando despesa...." + id);
        getDespesaArtistas(id);
        despesaArtistas.setId(id);
        despesaArtRepository.save(despesaArtistas);
        return ResponseEntity.ok(despesaArtistas);
    }

    private DespesaArtistas getDespesaArtistas(Long id) {
        return despesaArtRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Despesa não encontrada."));
    }
}
