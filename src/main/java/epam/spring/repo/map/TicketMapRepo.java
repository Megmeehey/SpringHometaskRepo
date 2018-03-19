package epam.spring.repo.map;

import epam.spring.entity.Event;
import epam.spring.entity.Ticket;
import epam.spring.repo.TicketRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TicketMapRepo extends AbstractMapRepo<Ticket> implements TicketRepoI {
    private ConcurrentHashMap<Long, Ticket> bookedTickets;

    private void isValid(@NonNull Ticket entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isValidLong(entity.getSeat())) {
            throw new IllegalArgumentException("Ticket " + entity + " is not a valid Ticket");
        }
    }

    public void bookTicket(Ticket ticket) {
        // TODO: 07.03.18 ADD CHECKS
        bookedTickets.put(ticket.getId(), ticket);
    }

    public void unbookTicket(Ticket ticket) {
        // TODO: 07.03.18 ADD CHECKS
        bookedTickets.remove(ticket.getId());
    }

    @Override
    public Set<Ticket> findAllForEventAndDate(Event event, LocalDateTime dateTime) {
        return source.values()
                     .stream()
                     .filter(ticket -> ticket.getDateTime().equals(dateTime) && ticket.getEvent().equals(event))
                     .collect(Collectors.toSet());
    }
}
