package epam.spring.service;

import epam.spring.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable User getUserByEmail(@Nonnull String email);

}
