package epam.spring.repo;

import epam.spring.entity.AbstractEntity;

public interface AbstractRepoI<T extends AbstractEntity> {

    T save(T entity);

    void delete(T entity);

    void deleteById(Long entityId);

    T findById(Long entityId);
}
