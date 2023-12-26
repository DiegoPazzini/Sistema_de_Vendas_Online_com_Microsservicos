/**
 * ClienteResourceTest é uma classe de teste JUnit para a classe ClienteResource, que é parte do componente de recursos
 * da aplicação de vendas online. Utiliza o framework Mockito para simular o comportamento de componentes dependentes
 * (BuscaCliente e CadastroCliente).
 */
package br.com.pazzini.vendas.online;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockBean;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import br.com.pazzini.vendas.online.domain.Cliente;
import br.com.pazzini.vendas.online.reources.ClienteResource;
import br.com.pazzini.vendas.online.usecase.BuscaCliente;
import br.com.pazzini.vendas.online.usecase.CadastroCliente;

/**
 * Testa o comportamento da classe ClienteResource, que é responsável por lidar com operações relacionadas aos clientes
 * na aplicação de vendas online.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteResourceTest {
	
	@InjectMocks
	private ClienteResource clienteResource;
	
	@MockBean
	private BuscaCliente buscaCliente;
	
	@MockBean
	private CadastroCliente cadastroCliente;
	
	@BeforeEach
    public void setup() {
		// Inicializa os mocks antes de cada teste
		MockitoAnnotations.openMocks(this);
	}
	
	/**
	 * Testa o método buscarPorId() da classe ClienteResource.
	 */
	@Test
	public void buscarPorId() {
		// Cria um cliente de exemplo
		Cliente cliente1 = Cliente.builder().id("1").nome("Diego 1").build();
		 
		// Configura o comportamento esperado para o mock BuscaCliente
        when(buscaCliente.buscarPorId("1")).thenReturn(cliente1);
 
        // Chama o método a ser testado
        ResponseEntity<Cliente> result = clienteResource.buscarPorId("1");
 
        // Verifica se o resultado é consistente com o comportamento esperado
        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente1);
        assertThat(clienteResult.getId()).isEqualTo(cliente1.getId());
        assertThat(clienteResult.getNome()).isEqualTo(cliente1.getNome());
	}

}
