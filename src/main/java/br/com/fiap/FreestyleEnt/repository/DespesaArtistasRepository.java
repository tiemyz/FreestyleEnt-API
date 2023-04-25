package br.com.fiap.FreestyleEnt.repository;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.FreestyleEnt.models.DespesaArtistas;

public interface DespesaArtistasRepository extends JpaRepository<DespesaArtistas, Long> {

    Page<DespesaArtistas> findByIdContaining(String busca, Pageable pageable);
}
