package epam.spring.repo;

import epam.spring.entity.Event;

public interface EventRepoI extends AbstractRepoI<Event> {
    Event findByName(String name);
}
