package br.com.fiap.FreestyleEnt.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.FreestyleEnt.models.Conta;
import br.com.fiap.FreestyleEnt.models.DespesaArtistas;
import br.com.fiap.FreestyleEnt.models.DespesaFuncionario;
import br.com.fiap.FreestyleEnt.repository.ContaRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaArtistasRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaFuncionarioRepository;
import br.com.fiap.FreestyleEnt.repository.DespesaInfraestruturaRepository;
import br.com.fiap.FreestyleEnt.repository.PagarDespesasRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaArtistasRepository despesaArtRepository;

    @Autowired
    DespesaFuncionarioRepository despesaFuncRepository;

    @Autowired
    DespesaInfraestruturaRepository despesaInfraRepository;

    @Autowired
    PagarDespesasRepository despesaPagarRepository;

    @Override
    public void run(String... args) throws Exception {

        Conta c1 = new Conta(1L, "bradesco", new BigDecimal(102350));     
        Conta c2 = new Conta(2L, "itau", new BigDecimal(100));  
        contaRepository.saveAll(List.of(c1, c2));

        despesaArtRepository.saveAll(List.of(
            DespesaArtistas.builder().valor(new BigDecimal(174123)).artista("New Order").gravadora("Factory Records").dataCadastro(LocalDate.now()).detalhes("new wave").conta(c1).build(),

            DespesaArtistas.builder().valor(new BigDecimal(184133)).artista("ENHYPEN").gravadora("Belift Entertainment").dataCadastro(LocalDate.now()).detalhes(" ").conta(c1).build()
        ));

        despesaFuncRepository.saveAll(List.of(
            DespesaFuncionario.builder().valorFuncionario(new BigDecimal(523)).nomeEquipe("Limpeza").nomeLider("Antônio Park").dataCadastroFuncionario(LocalDate.now()).detalhesFuncionario("produtos de limpeza").conta(c2).build(),

            DespesaFuncionario.builder().valorFuncionario(new BigDecimal(782)).nomeEquipe("Atendimento").nomeLider("Karina Souza").dataCadastroFuncionario(LocalDate.now()).detalhesFuncionario("computadores").conta(c2).build()
        ));
    } 
}
