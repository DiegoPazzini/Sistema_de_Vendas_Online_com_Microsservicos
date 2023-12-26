/**
 * A classe CadastroCliente representa a lógica de negócios para operações relacionadas à entidade Cliente.
 */
package br.com.pazzini.vendas.online.usecase;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pazzini.vendas.online.domain.Cliente;
import br.com.pazzini.vendas.online.repository.IClienteRepository;

/**
 * Serviço que encapsula a lógica de negócios para operações relacionadas à entidade Cliente.
 */
@Service
public class CadastroCliente {
    
    private IClienteRepository clienteRepository;
    
    /**
     * Construtor que injeta a dependência do repositório IClienteRepository.
     *
     * @param clienteRepository Repositório para operações de acesso a dados relacionadas à entidade Cliente.
     */
    @Autowired
    public CadastroCliente(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    /**
     * Cadastra um novo cliente no sistema.
     *
     * @param cliente Cliente a ser cadastrado, validado com as anotações de bean validation.
     * @return O cliente cadastrado.
     */
    public Cliente cadastrar(@Valid Cliente cliente) {
        return this.clienteRepository.insert(cliente);
    }

    /**
     * Atualiza as informações de um cliente existente.
     *
     * @param cliente Cliente a ser atualizado, validado com as anotações de bean validation.
     * @return O cliente atualizado.
     */
    public Cliente atualizar(@Valid Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    /**
     * Remove um cliente do sistema com base no seu identificador único.
     *
     * @param id Identificador único do cliente a ser removido.
     */
    public void remover(String id) {
        this.clienteRepository.deleteById(id);
    }
}
