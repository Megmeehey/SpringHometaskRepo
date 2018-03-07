package epam.spring.service;

import epam.spring.entity.User;
import epam.spring.repo.UserRepoI;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Setter
@Getter
public class UserService implements UserServiceI {
    private UserRepoI userRepo;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User save(@Nonnull User user) {
        return userRepo.save(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        userRepo.delete(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userRepo.findById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userRepo.findAll();
    }
}
