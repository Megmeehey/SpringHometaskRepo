package epam.spring.repo.map;

import epam.spring.repo.AuditoriumRepoI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuditoriumMapRepoTest {

    AuditoriumRepoI auditoriumRepo = new AuditoriumMapRepo();

    @Before
    public void setUp() {
        auditoriumRepo.save();
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findById() {
    }
}