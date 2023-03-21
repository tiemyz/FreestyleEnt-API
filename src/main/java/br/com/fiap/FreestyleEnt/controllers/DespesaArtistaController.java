package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaArtista;

@RestController
public class DespesaArtistaController {

    @GetMapping("/api/v1/despesaArtista")
    public DespesaArtista index(){
        return new DespesaArtista(1l, new BigDecimal(100), LocalDate.now(), "banda");
    } 
}
