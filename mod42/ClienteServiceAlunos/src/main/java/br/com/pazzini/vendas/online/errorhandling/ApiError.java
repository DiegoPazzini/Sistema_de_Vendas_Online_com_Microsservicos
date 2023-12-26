/**
 * A classe ApiError representa uma estrutura padronizada para mensagens de erro em respostas da API.
 */
package br.com.pazzini.vendas.online.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa uma estrutura padronizada para mensagens de erro em respostas da API.
 */
@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    /**
     * Construtor padrão para inicializar o timestamp com o momento atual.
     */
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Construtor que aceita um status HTTP como parâmetro.
     */
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Construtor que aceita um status HTTP e uma exceção como parâmetros.
     */
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Erro inesperado";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Construtor que aceita um status HTTP, uma mensagem personalizada e uma exceção como parâmetros.
     */
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Método privado para adicionar um suberro à lista de suberros.
     */
    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    /**
     * Método privado para adicionar um erro de validação à lista de suberros.
     */
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    /**
     * Método privado para adicionar um erro de validação à lista de suberros.
     */
    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    /**
     * Método privado para adicionar um erro de validação de campo à lista de suberros.
     */
    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    /**
     * Método público para adicionar erros de validação de campo à lista de suberros.
     */
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /**
     * Método privado para adicionar um erro de validação de objeto à lista de suberros.
     */
    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    /**
     * Método público para adicionar erros de validação de objeto à lista de suberros.
     */
    public void addValidationErrors(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Método privado para adicionar um erro de validação de ConstraintViolation à lista de suberros.
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                "",
                cv.getInvalidValue(),
                cv.getMessage());
    }

    /**
     * Método público para adicionar erros de validação de ConstraintViolation à lista de suberros.
     */
    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}
