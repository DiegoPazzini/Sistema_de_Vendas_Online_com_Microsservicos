/**
 * A classe MongoConfig é uma classe de configuração que define a configuração específica para integração do Spring Data MongoDB.
 * Está anotada com @Configuration, indicando que fornece configurações para o contexto da aplicação Spring.
 * A anotação @EnableMongoRepositories é utilizada para habilitar a integração com repositórios do MongoDB, e o atributo
 * basePackages define o pacote base onde o Spring irá procurar por interfaces de repositórios.
 */
package br.com.pazzini.vendas.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuração específica para integração do Spring Data MongoDB.
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.rpires.vendas.online.repository")
public class MongoConfig {

}
