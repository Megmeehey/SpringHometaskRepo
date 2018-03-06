package epam.spring.service;

import epam.spring.entity.Event;
import epam.spring.repo.EventRepoI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventService implements EventServiceI {
    @Autowired
    private EventRepoI eventRepo;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventRepo.findByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return new HashSet<>(eventRepo.getForDateRange(from, to));
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDate to) {
        return new HashSet<>(eventRepo.getNextEvents(to));
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
