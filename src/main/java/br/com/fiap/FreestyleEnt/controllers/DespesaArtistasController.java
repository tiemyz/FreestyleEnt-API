package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaArtistas;

@RestController
public class DespesaArtistasController {

    @GetMapping("/api/v1/despesaArtistas")
    public DespesaArtistas index(){
        return new DespesaArtistas(1l, new BigDecimal(100), LocalDate.now(), "banda");
    } 
}
