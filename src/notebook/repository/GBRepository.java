package notebook.repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий, для выполнения CreateReadUpdateDelete (CRUD) операций
 * @param <E> тип модели данных
 * @param <I> тип ID модели данных E
 */
public interface GBRepository<E, I> {
    List<E> findAll();
    E create(E e);
    E findById(I id);
    Optional<E> update(I id, E e);
    boolean delete(I id);
}
