package epam.spring.repo.map;

import epam.spring.entity.Auditorium;
import epam.spring.repo.AuditoriumRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

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
}

