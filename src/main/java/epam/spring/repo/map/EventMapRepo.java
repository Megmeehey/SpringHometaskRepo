package epam.spring.repo.map;

import epam.spring.entity.Event;
import epam.spring.exceptions.InconsistentDatabaseState;
import epam.spring.repo.EventRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class EventMapRepo extends AbstractMapRepo<Event> implements EventRepoI {
    private void isValid(@NonNull Event entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getName())
                && ValidationUtils.isValidPrice(entity.getBasePrice())) {
            throw new IllegalArgumentException("Event " + entity + " is not a valid Event");
        }
    }

    @Override
    public Event findByName(String name) {
        final List<Event> events = source.values()
                                         .stream()
                                         .filter(event -> event.getName()
                                                               .equals(name))
                                         .collect(Collectors.toList());

        if (events.size() > 1)
            throw new InconsistentDatabaseState("More than one event by that name " + name);

        if (events.isEmpty())
            return null;

        return events.get(0);
    }
}
