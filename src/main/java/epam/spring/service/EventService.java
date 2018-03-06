package epam.spring.service;

import epam.spring.entity.Event;
import epam.spring.repo.EventRepoI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventService implements EventServiceI {
    @Autowired
    private EventRepoI eventRepo;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventRepo.findByName(name);
    }

    @Override
    public Event save(@Nonnull Event event) {
        return eventRepo.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventRepo.delete(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventRepo.findById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventRepo.findAll();
    }
}
