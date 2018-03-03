package epam.spring.repo;

import epam.spring.entity.AbstractEntity;

public interface AbstractRepo<T extends AbstractEntity> {

    void save(T entity);

    void delete(T entity);

    void deleteById(Long entityId);

    T findById(Long entityId);
}
