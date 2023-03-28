package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.PagarDespesas;

@RestController
public class PagarDespesasController {

    @GetMapping("/api/v1/pagar_despesas")
    public PagarDespesas index(){
        return new PagarDespesas(1l, new BigDecimal(100), LocalDate.now(), "banda");
    } 
}