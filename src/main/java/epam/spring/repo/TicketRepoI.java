package epam.spring.repo;

import epam.spring.entity.Event;
import epam.spring.entity.Ticket;

import java.time.LocalDateTime;
import java.util.Set;

public interface TicketRepoI extends AbstractRepoI<Ticket> {
    Set<Ticket> findAllForEventAndDate(Event event, LocalDateTime dateTime);
}
