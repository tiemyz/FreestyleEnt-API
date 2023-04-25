package br.com.fiap.FreestyleEnt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.DespesaInfraestrutura;

public interface DespesaInfraestruturaRepository extends JpaRepository<DespesaInfraestrutura, Long>{
    Page<DespesaInfraestrutura> findByIdContaining(String busca, Pageable pageable);
}
