package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.PagarDespesas;

public interface PagarDespesasRepository extends JpaRepository<PagarDespesas, Long>{
    
}