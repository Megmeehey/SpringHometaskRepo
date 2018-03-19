package epam.spring.service;

import epam.spring.entity.Auditorium;
import epam.spring.entity.Event;
import epam.spring.entity.Ticket;
import epam.spring.entity.User;
import epam.spring.exceptions.InconsistentDatabaseState;
import epam.spring.repo.TicketRepoI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService implements BookingServiceI {
    private TicketRepoI ticketRepo;
    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event,
                                  @Nonnull LocalDateTime dateTime,
                                  @Nullable User user,
                                  @Nonnull Set<Long> seats) {
        final Auditorium auditorium = event.getAuditoriums().get(dateTime);
        final Set<Long> bookedSeats = getPurchasedTicketsForEvent(event, dateTime).stream()
                                                                                  .map(Ticket::getSeat)
                                                                                  .collect(Collectors.toSet());
        if (seats.stream().anyMatch(bookedSeats::contains)) {
            throw new InconsistentDatabaseState("These seats you chose, are already booked");
        }

        final double basePrice = event.getBasePrice();
        final double ratingFactor = event.getRating().getFactor();
        final byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
        final Set<Long> vipSeats = auditorium.getVipSeats()
                                             .stream()
                                             .filter(seats::contains)
                                             .collect(Collectors.toSet());

        seats.removeAll(vipSeats);

        return (seats.size() + vipSeats.size() * 2) * discount * basePrice * ratingFactor;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        tickets.forEach(ticket -> {
            if (ticket.getUser() != null) {
                ticketRepo.save(ticket);
            } else {
                ticket.setUser(new User());
                ticketRepo.save(ticket);
            }
        });
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event,
                                                   @Nonnull LocalDateTime dateTime) {
        return ticketRepo.findAllForEventAndDate(event, dateTime);
    }
}
