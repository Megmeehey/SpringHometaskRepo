package epam.spring.repo.map;

import epam.spring.entity.Auditorium;
import epam.spring.exceptions.InconsistentDatabaseState;
import epam.spring.repo.AuditoriumRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuditoriumMapRepo extends AbstractMapRepo<Auditorium> implements AuditoriumRepoI {
    private void isValid(@NonNull Auditorium entity) {
                if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getName())
                && ValidationUtils.isValidLong(entity.getNumberOfSeats())) {
            throw new IllegalArgumentException("Auditorium " + entity + " is not a valid Auditorium");
        }
    }

    @Override
    public List<Auditorium> findAll() {
        return new ArrayList<>(source.values());
    }

    @Override
    public Auditorium findByName(String name) {
        final List<Auditorium> auditoriums = source.values()
                                                   .stream()
                                                   .filter(auditorium -> auditorium.getName()
                                                                                   .equals(name))
                                                   .collect(Collectors.toList());


        if (auditoriums.size() > 1)
            throw new InconsistentDatabaseState("More than one auditorium by that name " + name);

        if (auditoriums.isEmpty())
            return null;

        return auditoriums.get(0);
    }
}

