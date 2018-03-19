package epam.spring.service;

import epam.spring.entity.Auditorium;
import epam.spring.repo.AuditoriumRepoI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuditoriumService implements AuditoriumServiceI {
    private AuditoriumRepoI auditoriumRepo;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriumRepo.findAll());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumRepo.findByName(name);
    }
}
