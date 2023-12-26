/**
 * A interface `IProdutoRepository` é uma extensão da interface `MongoRepository` fornecida pelo Spring Data MongoDB.
 * Ela define operações de acesso a dados relacionadas à entidade `Produto`.
 */
package br.com.pazzini.vendas.online.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pazzini.vendas.online.domain.Produto;
import br.com.pazzini.vendas.online.domain.Produto.Status;

/**
 * Repositório para a entidade Produto, proporcionando operações de acesso a dados.
 */
@Repository
public interface IProdutoRepository extends MongoRepository<Produto, String>{

    /**
     * Busca um produto pelo código.
     *
     * @param codigo Código do produto a ser buscado.
     * @return Um Optional contendo o produto encontrado, ou vazio se não encontrado.
     */
    Optional<Produto> findByCodigo(String codigo);
    
    /**
     * Busca todos os produtos com um determinado status, retornando uma página de resultados.
     *
     * @param pageable Configurações de paginação.
     * @param status Status dos produtos a serem buscados.
     * @return Uma página de produtos com o status especificado.
     */
    Page<Produto> findAllByStatus(Pageable pageable, Status status);
}
