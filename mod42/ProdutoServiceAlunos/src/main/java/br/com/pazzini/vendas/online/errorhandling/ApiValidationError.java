/**
 * A classe ApiValidationError representa um suberro específico utilizado em respostas de erro da API, especialmente para erros de validação.
 * Esta classe herda de ApiSubError e fornece informações detalhadas sobre validações que falharam, incluindo o objeto, campo, valor rejeitado e a mensagem de erro.
 */
package br.com.pazzini.vendas.online.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa um suberro específico utilizado em respostas de erro da API para erros de validação.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    /**
     * Construtor utilizado para criar uma instância de ApiValidationError com informações mínimas.
     * @param object  O nome do objeto associado ao erro.
     * @param message A mensagem de erro associada ao objeto.
     */
    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
