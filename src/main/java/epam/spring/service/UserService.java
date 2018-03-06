package epam.spring.service;

import epam.spring.entity.User;
import epam.spring.repo.UserRepoI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class UserService implements UserServiceI {
    @Autowired
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
