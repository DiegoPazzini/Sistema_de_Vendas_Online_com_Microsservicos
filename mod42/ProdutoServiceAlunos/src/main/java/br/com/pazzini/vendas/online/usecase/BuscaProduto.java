/**
 * A classe CadastroProduto é um componente de caso de uso responsável por gerenciar as operações de cadastro, atualização e remoção de produtos.
 * Ela segue as boas práticas de design orientado a objetos e é anotada com @Service, indicando que é um componente de serviço gerenciado pelo Spring.
 * A classe possui uma dependência injetada de IProdutoRepository, o que facilita a testabilidade e segue o princípio de inversão de controle.
 */
package br.com.pazzini.vendas.online.usecase;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pazzini.vendas.online.domain.Produto;
import br.com.pazzini.vendas.online.domain.Produto.Status;
import br.com.pazzini.vendas.online.exception.EntityNotFoundException;
import br.com.pazzini.vendas.online.repository.IProdutoRepository;

/**
 * CadastroProduto é um componente de caso de uso responsável por encapsular as operações de cadastro, atualização e remoção de produtos.
 * Esta classe segue boas práticas de design orientado a objetos e é anotada com @Service para indicar que é um componente de serviço gerenciado pelo Spring.
 * Ela possui uma dependência injetada de IProdutoRepository, o que facilita a testabilidade e a inversão de controle.
 */
@Service
public class CadastroProduto {
	
	// Repositório responsável pela persistência de produtos.
	private IProdutoRepository produtoRepository;
	
	/**
	 * Construtor de CadastroProduto que recebe uma instância de IProdutoRepository como dependência.
	 * A anotação @Autowired indica que a instância será injetada automaticamente pelo Spring.
	 */
	@Autowired
	public CadastroProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	/**
	 * Método responsável por cadastrar um novo produto. Recebe um objeto Produto validado e define seu status como ATIVO.
	 * A inserção é realizada através do método insert do repositório.
	 * Retorna o produto cadastrado.
	 */
	public Produto cadastrar(@Valid Produto produto) {
		produto.setStatus(Status.ATIVO);
		return this.produtoRepository.insert(produto);
	}

	/**
	 * Método responsável por atualizar um produto existente. Recebe um objeto Produto validado e o salva no repositório através do método save.
	 * Retorna o produto atualizado.
	 */
	public Produto atualizar(@Valid Produto produto) {
		return this.produtoRepository.save(produto);
	}

	/**
	 * Método responsável por remover um produto com base no ID. Busca o produto no repositório por ID e, se encontrado, define seu status como INATIVO.
	 * O produto é então salvo no repositório. Caso o produto não seja encontrado, uma exceção do tipo EntityNotFoundException é lançada.
	 * Este método mantém um histórico das operações, marcando o produto como INATIVO em vez de excluí-lo permanentemente.
	 */
	public void remover(String id) {
		Produto prod = produtoRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));
		prod.setStatus(Status.INATIVO);
		this.produtoRepository.save(prod);
		// Poderia ser utilizado o método deleteById para excluir permanentemente o produto por ID.
		// this.produtoRepository.deleteById(id);
	}
}
