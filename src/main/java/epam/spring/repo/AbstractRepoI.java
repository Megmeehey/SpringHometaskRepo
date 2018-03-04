package epam.spring.repo;

import epam.spring.entity.AbstractEntity;

import java.util.List;

public interface AbstractRepoI<T extends AbstractEntity> {

    T save(T entity);

    void delete(T entity);

    void deleteById(Long entityId);

    T findById(Long entityId);

    List<T> findAll();
}
