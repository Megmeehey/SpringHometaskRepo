package epam.spring.repo.map;

import epam.spring.entity.User;
import epam.spring.exceptions.InconsistentDatabaseState;
import epam.spring.repo.UserRepoI;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapRepo extends AbstractMapRepo<User> implements UserRepoI {
    private void isValid(@NonNull User entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isNotEmpty(entity.getEmail())
                && ValidationUtils.isNotEmpty(entity.getFirstName())
                && ValidationUtils.isNotEmpty(entity.getLastName())) {
            throw new IllegalArgumentException("User " + entity + " is not a valid User");
        }
    }

    @Override
    public User findByEmail(String email) {
        final List<User> users = source.values()
                                            .stream()
                                            .filter(user -> user.getEmail()
                                                                .equals(email))
                                            .collect(Collectors.toList());

        if (users.size() > 1)
            throw new InconsistentDatabaseState("More than one user by that email " + email);

        if (users.isEmpty())
            return null;

        return users.get(0);
    }
}
