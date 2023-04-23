package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
