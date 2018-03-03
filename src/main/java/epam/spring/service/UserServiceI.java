package epam.spring.service;

import epam.spring.entity.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserServiceI extends DomainObjectServiceI<User> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable
    User getUserByEmail(@Nonnull String email);

}
