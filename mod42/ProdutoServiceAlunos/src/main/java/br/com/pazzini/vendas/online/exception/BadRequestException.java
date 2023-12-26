/**
 * A classe BadRequestException é uma exceção específica utilizada para representar erros relacionados a requisições malformadas ou inválidas.
 * Ela estende a classe RuntimeException, o que significa que é uma exceção não verificada e, portanto, não requer cláusulas throws ou tratamento obrigatório.
 */
package br.com.pazzini.vendas.online.exception;

/**
 * Exceção lançada em caso de requisição malformada ou inválida.
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -7339546357706827674L;

    /**
     * Construtor que aceita uma mensagem personalizada para a exceção.
     * @param message A mensagem que descreve a natureza do erro.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
