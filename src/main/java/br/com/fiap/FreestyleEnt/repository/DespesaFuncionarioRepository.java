package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;


public interface DespesaFuncionarioRepository extends JpaRepository<DespesaFuncionario, Long> {
    Page<DespesaFuncionario> findByNomeEquipeContaining(String busca, Pageable pageable);
}
