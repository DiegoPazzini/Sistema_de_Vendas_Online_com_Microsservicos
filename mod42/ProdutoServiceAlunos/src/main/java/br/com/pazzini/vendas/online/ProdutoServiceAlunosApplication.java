/**
 * A classe `ProdutoServiceAlunosApplication` é a classe principal que inicia a aplicação Spring Boot para o serviço de produtos.
 * Está anotada com `@SpringBootApplication` para indicar que é uma aplicação Spring Boot.
 * `@RefreshScope` é usado para habilitar o suporte à atualização dinâmica de propriedades da aplicação.
 * `@EnableAutoConfiguration` é configurado para excluir a configuração automática do DataSource, indicando que não será necessário um DataSource nesta aplicação.
 */
package br.com.pazzini.vendas.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProdutoServiceAlunosApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProdutoServiceAlunosApplication.class, args);
    }

}

