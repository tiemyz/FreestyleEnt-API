package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.ContaCadastro;

public interface ContaCadastroRepository extends JpaRepository<ContaCadastro, Long> {
    
}
