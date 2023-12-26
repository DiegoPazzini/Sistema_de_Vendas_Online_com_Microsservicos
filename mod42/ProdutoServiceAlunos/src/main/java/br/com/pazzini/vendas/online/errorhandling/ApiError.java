/**
 * A classe ApiError é uma representação de erro padronizada para respostas de API.
 * Ela contém informações como status HTTP, timestamp, mensagem, mensagem de depuração e suberros relacionados a validações.
 * É utilizada para encapsular erros e fornecer respostas consistentes nas APIs.
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
 * Representa uma resposta de erro padronizada para APIs.
 */
@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    
    // Anotação para formatar o timestamp na resposta JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    // Construtor privado para inicializar o timestamp
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    // Construtor para status sem mensagem
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    // Construtor para status e exceção
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    // Construtor para status, mensagem e exceção
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    // Método privado para adicionar suberro à lista
    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    // Métodos privados para adicionar erros de validação
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    // Método público para adicionar lista de erros de validação de campos
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    // Método público para adicionar lista de erros de validação globais
    public void addValidationErrors(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Método utilitário para adicionar erro de ConstraintViolation, geralmente quando a validação @Validated falha.
     *
     * @param cv o ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                "",
                cv.getInvalidValue(),
                cv.getMessage());
    }

    // Método público para adicionar lista de erros de validação de constraints
    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}
