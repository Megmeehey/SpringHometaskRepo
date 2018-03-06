package epam.spring.repo.map;

import epam.spring.entity.User;
import epam.spring.repo.UserRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

public class UserMapRepo extends AbstractMapRepo<User> implements UserRepoI {
    private void isValid(@NonNull User entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getEmail())
                && ValidationUtils.isNotEmpty(entity.getFirstName())
                && ValidationUtils.isNotEmpty(entity.getLastName())) {
            throw new IllegalArgumentException("User " + entity + " is not a valid User");
        }
    }
}
