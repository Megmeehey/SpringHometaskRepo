package epam.spring.repo;

import epam.spring.entity.Auditorium;

import java.util.List;

public interface AuditoriumRepoI extends AbstractRepoI<Auditorium> {
    List<Auditorium> findAll();
}
