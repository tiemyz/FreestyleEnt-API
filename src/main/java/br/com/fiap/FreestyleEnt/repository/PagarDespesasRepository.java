package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.PagarDespesas;

public interface PagarDespesasRepository extends JpaRepository<PagarDespesas, Long>{
    Page<PagarDespesas> findByIdContaining(String busca, Pageable pageable);
}
