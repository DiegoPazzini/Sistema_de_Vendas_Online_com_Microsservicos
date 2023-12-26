/**
 * A classe ProdutoResource é um controlador REST que lida com operações relacionadas a produtos na aplicação de vendas online.
 * Está anotada com @RestController, indicando que é um controlador Spring MVC que lida com requisições HTTP.
 * As operações disponíveis incluem buscar, cadastrar, atualizar e remover produtos.
 */
package br.com.pazzini.vendas.online.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pazzini.vendas.online.domain.Produto;
import br.com.pazzini.vendas.online.domain.Produto.Status;
import br.com.pazzini.vendas.online.usecase.BuscaProduto;
import br.com.pazzini.vendas.online.usecase.CadastroProduto;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controlador REST para lidar com operações relacionadas a produtos na aplicação de vendas online.
 */
@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

	private BuscaProduto buscaProduto;
	
	private CadastroProduto cadastroProduto;
	
	@Autowired
	public ProdutoResource(BuscaProduto buscaProduto,
			CadastroProduto cadastroProduto) {
		this.buscaProduto = buscaProduto;
		this.cadastroProduto = cadastroProduto;
	}
	
	/**
	 * Endpoint para buscar uma lista paginada de produtos.
	 * @param pageable Objeto Pageable para suportar paginação.
	 * @return Uma ResponseEntity contendo a lista paginada de produtos.
	 */
	@GetMapping
	@Operation(summary = "Busca uma lista paginada de produtos")
	public ResponseEntity<Page<Produto>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaProduto.buscar(pageable));
	}
	
	/**
	 * Endpoint para buscar uma lista paginada de produtos por status.
	 * @param pageable Objeto Pageable para suportar paginação.
	 * @param status Status do produto.
	 * @return Uma ResponseEntity contendo a lista paginada de produtos filtrada pelo status.
	 */
	@GetMapping(value = "/status/{status}")
	@Operation(summary = "Busca uma lista paginada de produtos por status")
	public ResponseEntity<Page<Produto>> buscarPorStatus(Pageable pageable, 
			@PathVariable(value = "status", required = true) Status status) {
		return ResponseEntity.ok(buscaProduto.buscar(pageable, status));
	}
	
	/**
	 * Endpoint para buscar um produto pelo código.
	 * @param codigo Código do produto.
	 * @return Uma ResponseEntity contendo o produto encontrado.
	 */
	@GetMapping(value = "/{codigo}")
	@Operation(summary = "Busca um produto pelo código")
	public ResponseEntity<Produto> buscarPorCodigo(String codigo) {
		return ResponseEntity.ok(buscaProduto.buscarPorCodigo(codigo));
	}
	
	/**
	 * Endpoint para cadastrar um novo produto.
	 * @param produto Objeto Produto a ser cadastrado.
	 * @return Uma ResponseEntity contendo o produto cadastrado.
	 */
	@PostMapping
	@Operation(summary = "Cadastrar um produto")
	public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(cadastroProduto.cadastrar(produto));
	}
	
	/**
	 * Endpoint para atualizar um produto existente.
	 * @param produto Objeto Produto a ser atualizado.
	 * @return Uma ResponseEntity contendo o produto atualizado.
	 */
	@PutMapping
	@Operation(summary = "Atualiza um produto")
	public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(cadastroProduto.atualizar(produto));
	}	
	
	/**
	 * Endpoint para remover um produto pelo seu identificador único.
	 * @param id Identificador único do produto a ser removido.
	 * @return Uma ResponseEntity indicando o sucesso da remoção.
	 */
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove um produto pelo seu identificador único")
	public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
		cadastroProduto.remover(id);
		return ResponseEntity.ok("Removido com sucesso");
	}
}
