/**
 * A classe ClienteServiceAlunosApplication é a classe principal que inicia a aplicação Spring Boot.
 */
package br.com.pazzini.vendas.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Classe principal que inicia a aplicação Spring Boot para o serviço de clientes.
 */
@SpringBootApplication
@RefreshScope
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ClienteServiceAlunosApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(ClienteServiceAlunosApplication.class, args);
    }

}
