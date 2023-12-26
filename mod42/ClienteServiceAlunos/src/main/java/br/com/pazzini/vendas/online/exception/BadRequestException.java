package br.com.pazzini.vendas.online.exception;
/**
 * A classe BadRequestException representa uma exceção personalizada para situações de requisição inválida (HTTP 400 Bad Request).
 */
public class BadRequestException extends RuntimeException {

    /**
     * Número de série único para garantir a consistência durante a serialização.
     */
    private static final long serialVersionUID = -7339546357706827674L;

    /**
     * Construtor que aceita uma mensagem como parâmetro.
     * @param message Mensagem descritiva da exceção.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
