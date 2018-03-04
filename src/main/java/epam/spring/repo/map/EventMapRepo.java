package epam.spring.repo.map;

import epam.spring.entity.Event;
import epam.spring.repo.EventRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

public class EventMapRepo extends AbstractMapRepo<Event> implements EventRepoI {
    private void isValid(@NonNull Event entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getName())
                && ValidationUtils.isValidPrice(entity.getBasePrice())) {
            throw new IllegalArgumentException("Event " + entity + " is not a valid event");
        }
    }
}
