package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaInfraestrutura;

@RestController
public class DespesaInfraestruturaController {

    @GetMapping("/api/v1/despesa_infraestrutura")
    public DespesaInfraestrutura index(){
        return new DespesaInfraestrutura(1l, null, null, new BigDecimal(100), LocalDate.now(), "palco primavera");
    } 
}
