package br.com.fiap.FreestyleEnt.config;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.FreestyleEnt.models.ContaCadastro;
import br.com.fiap.FreestyleEnt.repository.ContaCadastroRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaCadastroRepository contaCadastroRepository;

    @Override
    public void run(String... args) throws Exception {
        contaCadastroRepository.saveAll(List.of(
            new ContaCadastro(1L, "bradesco", new BigDecimal(100000))       
        ));
    } 
}
