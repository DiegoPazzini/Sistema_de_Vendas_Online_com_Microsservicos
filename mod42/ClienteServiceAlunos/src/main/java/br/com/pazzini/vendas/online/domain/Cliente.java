/**
 * A classe Cliente representa a entidade Cliente no domínio da aplicação.
 */
package br.com.pazzini.vendas.online.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um cliente da aplicação.
 */
@Document(collection = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Cliente", description = "Cliente") 
public class Cliente {
    
    /**
     * Identificador único do cliente.
     */
    @Id
    @Schema(description = "Identificador único") 
    private String id;
    
    /**
     * Nome do cliente.
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Nome", minLength = 1, maxLength = 50, nullable = false) 
    private String nome;
    
    /**
     * CPF do cliente, deve ser único.
     */
    @NotNull
    @Indexed(unique = true, background = true)
    @Schema(description = "CPF", nullable = false) 
    private Long cpf;
    
    /**
     * Número de telefone do cliente.
     */
    @NotNull
    @Schema(description = "Telefone", nullable = false) 
    private Long tel;
    
    /**
     * Email do cliente, deve seguir o padrão de endereço de email.
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Indexed(unique = true, background = true)
    @Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
    @Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
    private String email;
    
    /**
     * Endereço do cliente.
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Endereço", minLength = 1, maxLength = 50, nullable = false)
    private String end;
    
    /**
     * Número residencial do cliente.
     */
    @NotNull
    @Schema(description = "Numero residencial", nullable = false) 
    private Integer numero;
    
    /**
     * Cidade do cliente.
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Cidade", minLength = 1, maxLength = 50, nullable = false)
    private String cidade;
    
    /**
     * Estado do cliente.
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Schema(description = "Estado", minLength = 1, maxLength = 50, nullable = false)
    private String estado;
}
