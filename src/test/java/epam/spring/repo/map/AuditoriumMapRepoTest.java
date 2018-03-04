package epam.spring.repo.map;

import epam.spring.base.Status;
import epam.spring.entity.Auditorium;
import epam.spring.repo.AuditoriumRepoI;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AuditoriumMapRepoTest {

    private static final Status DEFAULT_STATUS = Status.Enabled;
    private static final long THIRD_AUDITORIUM_ID = 3L;
    private static final long THIRD_AUDITORIUM_NUMBER_OF_SEATS = 50L;
    private static final String THIRD_AUDITORIUM_NAME = "Third Auditorium";
    private static final int THIRD_AUDITORIUM_NUMBER_OF_VIP_SEATS = 10;
    private AuditoriumRepoI auditoriumRepo = new AuditoriumMapRepo();

    @Before
    public void setUp() {
        auditoriumRepo.save(buildAuditorium(1L, DEFAULT_STATUS, "First Auditorium",
                10L, Collections.emptySet()));
        auditoriumRepo.save(buildAuditorium(2L, DEFAULT_STATUS, "Second Auditorium",
                100L, Collections.emptySet()));
        auditoriumRepo.save(
                buildAuditorium(THIRD_AUDITORIUM_ID,
                        DEFAULT_STATUS,
                        THIRD_AUDITORIUM_NAME,
                        THIRD_AUDITORIUM_NUMBER_OF_SEATS,
                        new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L))));
    }

    private Auditorium buildAuditorium(long id, Status status, String name, long numberOfSeats, Set<Long> vipSeatsSet) {
        val auditorium = buildAuditorium(name, numberOfSeats, vipSeatsSet);
        auditorium.setId(id);
        auditorium.setStatus(status);
        return auditorium;
    }

    private Auditorium buildAuditorium(String name, long numberOfSeats, Set<Long> vipSeatsSet) {
        val auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setNumberOfSeats(numberOfSeats);
        auditorium.setVipSeats(vipSeatsSet);
        return auditorium;
    }

    @Test
    public void save() {
        assertNotNull(auditoriumRepo);
        val auditorium = buildAuditorium("Saved Auditorium", 42L,
                new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)));

        assertNotNull(auditorium);
        assertNull(auditorium.getId());
        assertNull(auditorium.getStatus());

        val auditoriumAfter = auditoriumRepo.save(auditorium);

        assertNotNull(auditoriumAfter.getId());
        assertNotNull(auditoriumAfter.getStatus());
        assertThat(auditoriumAfter.getId(), is(4L));
        assertThat(auditoriumAfter.getStatus(), is(Status.Enabled));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void delete() {
        assertNotNull(auditoriumRepo);

        val auditorium = auditoriumRepo.findById(3L);

        assertNotNull(auditorium);
        assertNotNull(auditorium.getId());
        assertNotNull(auditorium.getStatus());

        assertThat(auditorium.getId(), is(3L));
        assertThat(auditorium.getStatus(), is(Status.Enabled));

        auditoriumRepo.delete(auditorium);

        val auditoriumAfter = auditoriumRepo.findById(3L);

        assertNull(auditoriumAfter);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void deleteById() {
        assertNotNull(auditoriumRepo);

        val auditorium = auditoriumRepo.findById(2L);

        assertNotNull(auditorium);
        assertNotNull(auditorium.getId());
        assertNotNull(auditorium.getStatus());

        assertThat(auditorium.getId(), is(2L));
        assertThat(auditorium.getStatus(), is(Status.Enabled));

        auditoriumRepo.deleteById(auditorium.getId());

        val auditoriumAfter = auditoriumRepo.findById(2L);

        assertNull(auditoriumAfter);
    }

    @Test
    public void findById() {
        assertNotNull(auditoriumRepo);

        val auditorium = auditoriumRepo.findById(3L);

        assertNotNull(auditorium);
        assertNotNull(auditorium.getId());
        assertNotNull(auditorium.getStatus());
        assertNotNull(auditorium.getName());
        assertNotNull(auditorium.getVipSeats());

        assertThat(auditorium.getId(), is(THIRD_AUDITORIUM_ID));
        assertThat(auditorium.getStatus(), is(DEFAULT_STATUS));
        assertThat(auditorium.getNumberOfSeats(), is(THIRD_AUDITORIUM_NUMBER_OF_SEATS));
        assertThat(auditorium.getName(), is(THIRD_AUDITORIUM_NAME));
        assertThat(auditorium.getVipSeats(), hasSize(THIRD_AUDITORIUM_NUMBER_OF_VIP_SEATS));
    }

    @Test
    public void findAll() {
        assertNotNull(auditoriumRepo);

        val auditoriums = auditoriumRepo.findAll();

        assertNotNull(auditoriums);
        assertThat(auditoriums, hasSize(3));

        auditoriums.forEach(auditorium -> {
            assertNotNull(auditorium);
            assertNotNull(auditorium.getId());
            assertNotNull(auditorium.getStatus());
            assertNotNull(auditorium.getName());
        });
    }

    @After
    public void tearDown() {
        auditoriumRepo.findAll().forEach(
                auditorium -> auditoriumRepo.delete(auditorium)
        );
    }
}