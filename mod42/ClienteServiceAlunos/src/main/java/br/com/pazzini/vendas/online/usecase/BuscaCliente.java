/**
 * A interface IClienteRepository é responsável por definir operações de acesso a dados para a entidade Cliente no contexto do MongoDB.
 */
package br.com.pazzini.vendas.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pazzini.vendas.online.domain.Cliente;

/**
 * Repositório que estende MongoRepository para operações relacionadas à entidade Cliente no MongoDB.
 */
@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> {

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf Número do CPF do cliente.
     * @return Um Optional contendo o cliente correspondente, se existir.
     */
    Optional<Cliente> findByCpf(Long cpf);
}
