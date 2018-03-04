package epam.spring.repo.map;

import epam.spring.entity.Ticket;
import epam.spring.repo.TicketRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

public class TicketMapRepo extends AbstractMapRepo<Ticket> implements TicketRepoI {
    private void isValid(@NonNull Ticket entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isValidLong(entity.getSeat())) {
            throw new IllegalArgumentException("Event " + entity + " is not a valid event");
        }
    }
}
