package epam.spring.repo;

import epam.spring.entity.User;

public interface UserRepoI extends AbstractRepoI<User> {
    User findByEmail(String email);
}
