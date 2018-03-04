package epam.spring.repo.map;

import epam.spring.entity.Auditorium;
import epam.spring.repo.AuditoriumRepoI;
import epam.spring.util.ValidationUtils;

public class AuditoriumMapRepo extends AbstractMapRepo<Auditorium> implements AuditoriumRepoI {
    private void isValid(Auditorium entity) {
        if (!(ValidationUtils.isValid(entity)
                && ValidationUtils.isValid(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getName())
                && ValidationUtils.isValid(entity.getNumberOfSeats()))) {
            throw new IllegalArgumentException("Auditorium " + entity + " is not a valid auditorium");
        }
    }
}
