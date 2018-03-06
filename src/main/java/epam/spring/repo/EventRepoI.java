package epam.spring.repo;

import epam.spring.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepoI extends AbstractRepoI<Event> {
    Event findByName(String name);

    List<Event> getForDateRange(LocalDate from, LocalDate to);

    List<Event> getNextEvents(LocalDate to);
}
