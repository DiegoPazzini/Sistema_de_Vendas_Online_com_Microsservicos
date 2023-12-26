/**
 * A classe ValidatorConfig é uma classe de configuração que define a configuração específica para validação no Spring.
 * Está anotada com @Configuration, indicando que fornece configurações para o contexto da aplicação Spring.
 * O método validatorFactory() é anotado com @Bean e retorna um LocalValidatorFactoryBean, que é responsável por fornecer
 * a implementação do Validador Local para a aplicação.
 * O Validador Local é configurado para utilizar mensagens de validação do arquivo ResourceBundleMessageSource, que é
 * comumente utilizado para internacionalização de mensagens.
 */
package br.com.pazzini.vendas.online.config;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuração específica para validação no Spring.
 */
@Configuration
public class ValidatorConfig {
	
//	@Autowired
//    ResourceBundleMessageSource messageSource;

	/**
	 * Método para configurar o Validador Local para a aplicação.
	 * @return Uma instância de LocalValidatorFactoryBean configurada.
	 */
	@Bean
    public Validator validatorFactory () {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource);
	    return bean;
    }
}
