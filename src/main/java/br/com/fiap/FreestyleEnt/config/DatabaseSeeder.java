package br.com.fiap.FreestyleEnt.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.FreestyleEnt.models.Conta;
import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaInfraestruturaRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaArtistasRepository despesaArtRepository;

    @Autowired
    DespesaFuncionarioRepository despesaFuncRepository;

    @Autowired
    DespesaInfraestruturaRepository despesaInfraRepository;

    @Override
    public void run(String... args) throws Exception {

        Conta c1 = new Conta(1L, "itau", new BigDecimal(100), "money");
        Conta c2 = new Conta(2L, "bradesco", new BigDecimal(5), "money");
        Conta c3 = new Conta(3L, "carteira", new BigDecimal(1), "coin");
        contaRepository.saveAll(List.of(c1, c2, c3));

        despesaArtRepository.saveAll(List.of(
            DespesaArtistas.builder().valorArt(new BigDecimal(174123)).artista("New Order").gravadora("Factory Records").dataCadastroArt(LocalDate.now()).detalhesArt("new wave").conta(c1).build(),

            DespesaArtistas.builder().valorArt(new BigDecimal(184133)).artista("ENHYPEN").gravadora("Belift Entertainment").dataCadastroArt(LocalDate.now()).detalhesArt("não especificaram os detalhes").conta(c1).build()
        ));

        despesaFuncRepository.saveAll(List.of(
            DespesaFuncionario.builder().valorFunc(new BigDecimal(523)).nomeEquipe("Limpeza").nomeLider("Antônio Park").dataCadastroFunc(LocalDate.now()).detalhesFunc("produtos de limpeza").conta(c2).build(),
            DespesaFuncionario.builder().valorFunc(new BigDecimal(782)).nomeEquipe("Atendimento").nomeLider("Karina Souza").dataCadastroFunc(LocalDate.now()).detalhesFunc("computadores").conta(c2).build()
        ));
        
    } 
}
