/**
 * A classe ApiValidationError representa um suberro específico para erros de validação em respostas da API.
 */
package br.com.pazzini.vendas.online.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa um suberro específico para erros de validação em respostas da API.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

    /**
     * Nome do objeto que contém o erro de validação.
     */
    private String object;

    /**
     * Nome do campo que contém o erro de validação.
     */
    private String field;

    /**
     * Valor rejeitado que causou o erro de validação.
     */
    private Object rejectedValue;

    /**
     * Mensagem detalhada sobre o erro de validação.
     */
    private String message;

    /**
     * Construtor que aceita o nome do objeto e uma mensagem como parâmetros.
     */
    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
