package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;


public interface DespesaFuncionarioRepository extends JpaRepository<DespesaFuncionario, Long> {
    
}
