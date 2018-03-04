package epam.spring.repo.map;

import epam.spring.entity.Event;
import epam.spring.repo.EventRepoI;
import epam.spring.util.ValidationUtils;

public class EventMapRepo extends AbstractMapRepo<Event> implements EventRepoI {
    private void isValid(Event entity) {
        if (!(ValidationUtils.isValid(entity)
                && ValidationUtils.isValid(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getName())
                && ValidationUtils.isValid(entity.getBasePrice()))) {
            throw new IllegalArgumentException("Auditorium " + entity + " is not a valid auditorium");
        }
    }
}
