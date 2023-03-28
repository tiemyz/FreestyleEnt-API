package br.com.fiap.FreestyleEnt.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;

@RestController
public class DespesaFuncionarioController {

    @GetMapping("/api/v1/despesa_funcionario")
    public DespesaFuncionario index(){
        return new DespesaFuncionario(1l, null, null, new BigDecimal(100), LocalDate.now(), null, "banda");
    } 
}