/**
 * A classe Produto representa a entidade de produto na aplicação de vendas online.
 * Está anotada com @Document do Spring Data MongoDB para indicar que é uma entidade persistente.
 * Possui campos como código, nome, descrição, valor e status, com validações usando as anotações do Java Bean Validation.
 */
package br.com.pazzini.vendas.online.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um produto na aplicação de vendas online.
 */
@Document(collection = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
	
	/**
	 * Enumeração para representar o status de um produto, podendo ser ATIVO ou INATIVO.
	 */
	public enum Status {
		ATIVO, INATIVO;
	}
	
	@Id
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String descricao;
	
	private BigDecimal valor;
	
	private Status status;

}

