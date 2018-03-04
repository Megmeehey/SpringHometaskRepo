package epam.spring.repo.map;

import epam.spring.entity.AbstractEntity;
import epam.spring.repo.AbstractRepoI;
import epam.spring.util.ValidationUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractMapRepo<T extends AbstractEntity> implements AbstractRepoI<T> {
    private AtomicLong id;
    private ConcurrentHashMap<Long, T> source;

    AbstractMapRepo() {
        id = new AtomicLong(1L);
        source = new ConcurrentHashMap<>();
    }

    @Override
    public void save(T entity) {
        isValid(entity);
        source.put(id.getAndAdd(1L), entity);
    }

    @Override
    public void delete(T entity) {
        isValid(entity);
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long entityId) {
        if (source.remove(entityId) == null) {
            throw new IllegalArgumentException("No entity by id = " + entityId);
        }
    }

    @Override
    public T findById(Long entityId) {
        if (!ValidationUtils.isValid(entityId)) {
            throw new IllegalArgumentException("Illegal id = " + entityId);
        }
        return source.get(entityId);
    }

    private void isValid(T entity) {}
}
