/**
 * A classe ValidatorConfig é responsável por configurar o mecanismo de validação utilizando o Bean Validation.
 */
package br.com.pazzini.vendas.onlineconfig;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuração do mecanismo de validação utilizando o Bean Validation.
 */
@Configuration
public class ValidatorConfig {
	
//	@Autowired
//    ResourceBundleMessageSource messageSource;

    /**
     * Configuração do Bean Validation.
     * @return Uma instância configurada de Validator.
     */
	@Bean
    public Validator validatorFactory () {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource);
	    return bean;
    }
}
