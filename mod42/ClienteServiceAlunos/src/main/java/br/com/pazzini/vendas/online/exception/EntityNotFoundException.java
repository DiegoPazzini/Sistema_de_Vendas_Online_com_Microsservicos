/**
 * A classe EntityNotFoundException representa uma exceção personalizada para situações em que uma entidade não é encontrada.
 */
package br.com.pazzini.vendas.online.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

/**
 * Exceção personalizada para representar a não localização de uma entidade.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Número de série único para garantir a consistência durante a serialização.
     */
    private static final long serialVersionUID = -5576150444545521612L;

    /**
     * Construtor que aceita a classe da entidade e os parâmetros de pesquisa como parâmetros.
     */
    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    /**
     * Método privado para gerar uma mensagem de exceção com base na entidade e nos parâmetros de pesquisa.
     */
    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " não foi encontrada para os parâmetros " +
                searchParams;
    }

    /**
     * Método privado para converter um array de objetos em um mapa.
     */
    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Entradas inválidas");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
