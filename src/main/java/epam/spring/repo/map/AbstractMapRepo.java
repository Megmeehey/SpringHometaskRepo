package epam.spring.repo.map;

import epam.spring.base.Status;
import epam.spring.entity.AbstractEntity;
import epam.spring.repo.AbstractRepoI;
import epam.spring.util.ValidationUtils;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractMapRepo<T extends AbstractEntity> implements AbstractRepoI<T> {
    private static final Status DEFAULT_STATUS = Status.Enabled;
    private final AtomicLong id;
    final ConcurrentHashMap<Long, T> source;

    AbstractMapRepo() {
        id = new AtomicLong(1L);
        source = new ConcurrentHashMap<>();
    }

    @Override
    public T save(T entity) {
        isValid(entity);
        val generatedId = id.getAndAdd(1L);
        entity.setId(generatedId);
        entity.setStatus(DEFAULT_STATUS);
        source.put(generatedId, entity);
        return entity;
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
        if (!ValidationUtils.isValidLong(entityId)) {
            throw new IllegalArgumentException("Illegal id = " + entityId);
        }
        return source.get(entityId);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(source.values());
    }

    private void isValid(T entity) {}
}
