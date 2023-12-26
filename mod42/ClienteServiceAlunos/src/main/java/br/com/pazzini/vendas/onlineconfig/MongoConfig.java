/**
 * A classe MongoConfig representa uma configuração específica para o MongoDB na aplicação.
 */
package br.com.pazzini.vendas.onlineconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuração para integração do MongoDB com a aplicação.
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.pazzini.vendas.online.repository")
public class MongoConfig {

}
