/**
 * A classe EntityNotFoundException é uma exceção específica lançada quando uma entidade não pode ser encontrada com base nos parâmetros fornecidos.
 * Ela estende a classe RuntimeException, indicando que é uma exceção não verificada.
 */
package br.com.pazzini.vendas.online.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

/**
 * Exceção lançada quando uma entidade não pode ser encontrada com base nos parâmetros fornecidos.
 */
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5576150444545521612L;

    /**
     * Construtor que aceita a classe da entidade e um conjunto variável de parâmetros de busca.
     * @param clazz A classe da entidade que não foi encontrada.
     * @param searchParamsMap Os parâmetros de busca utilizados para tentar localizar a entidade.
     */
    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    /**
     * Método estático para gerar a mensagem de erro detalhada, incluindo a entidade e os parâmetros de busca.
     * @param entity A entidade que não foi encontrada.
     * @param searchParams Um mapa contendo os parâmetros de busca e seus valores.
     * @return A mensagem detalhada de erro.
     */
    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

    /**
     * Método estático para converter um conjunto variável de entradas em um mapa.
     * @param keyType O tipo da chave.
     * @param valueType O tipo do valor.
     * @param entries As entradas a serem convertidas em pares chave-valor.
     * @param <K> O tipo da chave.
     * @param <V> O tipo do valor.
     * @return Um mapa contendo pares chave-valor.
     */
    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
