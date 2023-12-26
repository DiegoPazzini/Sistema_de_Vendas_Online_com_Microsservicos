/**
 * A classe OpenAPIConfig é responsável pela configuração personalizada do Swagger/OpenAPI.
 */
package br.com.pazzini.vendas.onlineconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuração personalizada para o Swagger/OpenAPI.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Configuração do OpenAPI.
     * @param appVersion A versão da aplicação obtida a partir do arquivo de propriedades.
     * @return Uma instância de OpenAPI configurada.
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Serviço de clientes")
                        .version(appVersion)
                        .description("Serviço para gerenciamento de clientes")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Diego Pazzini").email("diego@pazzini.com")));
    }
}
