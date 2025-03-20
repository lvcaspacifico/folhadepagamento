package com.lvcaspacifico.folhadepagamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.lvcaspacifico.folhadepagamento.model.Empregado;
import com.lvcaspacifico.folhadepagamento.repository.EmpregadoRepository;

//@Configuration
public class CarregarDados {


    private static final Logger log = LoggerFactory.getLogger(CarregarDados.class);

    @Bean
    CommandLineRunner iniciarBancoDeDados(EmpregadoRepository empregadoRepository){

        return args -> {
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Jonathan Joestar", "Médico")));
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Joseph Joestar", "Explorador")));   
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Jotaro Kujo", "Biólogo")));
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Josuke Higashikata", "Atleta")));
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Giorno Giovana", "Empresário")));
            // log.info("Pré-carregar: " + empregadoRepository.save(new Empregado("Joline Kujo", "Exploradora")));
        };
    }
}
