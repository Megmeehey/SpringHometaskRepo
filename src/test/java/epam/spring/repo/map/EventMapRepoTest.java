package epam.spring.repo.map;

import epam.spring.UnitTest;
import epam.spring.base.EventRating;
import epam.spring.base.Status;
import epam.spring.entity.Auditorium;
import epam.spring.entity.Event;
import epam.spring.repo.EventRepoI;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


@Category(UnitTest.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class EventMapRepoTest {
    private static final Status DEFAULT_STATUS = Status.Enabled;
    private static final long THIRD_EVENT_ID = 3L;
    private static final String THIRD_EVENT_NAME = "Third event";
    private static final EventRating THIRD_EVENT_RATING = EventRating.LOW;
    private static final double THIRD_EVENT_PRICE = 10.0;
    private EventRepoI eventRepo = new EventMapRepo();

    @Before
    public void setUp() {
        eventRepo.save(buildEvent(1L, DEFAULT_STATUS, "First event", 100.0,
                EventRating.HIGH, Collections.emptyNavigableMap(), Collections.emptyNavigableSet()));

        eventRepo.save(buildEvent(2L, DEFAULT_STATUS, "Second event", 50.0,
                EventRating.MID, Collections.emptyNavigableMap(), Collections.emptyNavigableSet()));

        final TreeMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(LocalDateTime.now(), new Auditorium());

        final TreeSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(LocalDateTime.now());

        eventRepo.save(buildEvent(3L, DEFAULT_STATUS, "Third event", 10.0,
                EventRating.LOW, auditoriums, airDates));
    }

    private Event buildEvent(final long id,
                             final Status status,
                             final String name,
                             final double price,
                             final EventRating rating,
                             final NavigableMap<LocalDateTime, Auditorium> auditoriums,
                             final NavigableSet<LocalDateTime> airDates) {
        val event = buildEvent(name, price, rating, auditoriums, airDates);
        event.setId(id);
        event.setStatus(status);
        return event;
    }

    private Event buildEvent(final String name,
                             final double price,
                             final EventRating rating,
                             final NavigableMap<LocalDateTime, Auditorium> auditoriums,
                             final NavigableSet<LocalDateTime> airDates) {
        val event = new Event();
        event.setName(name);
        event.setBasePrice(price);
        event.setRating(rating);
        event.setAirDates(airDates);
        event.setAuditoriums(auditoriums);
        return event;
    }

    @Test
    public void save() {
        assertNotNull(eventRepo);

        final TreeMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(LocalDateTime.now().plusDays(1L), new Auditorium());

        final TreeSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(LocalDateTime.now().plusDays(1L));

        val event = buildEvent("Saved event", 120.0,
                EventRating.HIGH, auditoriums, airDates);

        assertNotNull(event);
        assertNull(event.getId());
        assertNull(event.getStatus());

        val eventAfter = eventRepo.save(event);

        assertNotNull(eventAfter.getId());
        assertNotNull(eventAfter.getStatus());
        assertThat(eventAfter.getId(), is(4L));
        assertThat(eventAfter.getStatus(), is(Status.Enabled));
        assertThat(eventAfter.getAuditoriums(), is(auditoriums));
        assertThat(eventAfter.getAirDates(), is(airDates));
        assertThat(eventAfter.getName(), is("Saved event"));
        assertThat(eventAfter.getBasePrice(), is(120.0));
        assertThat(eventAfter.getRating(), is(EventRating.HIGH));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void delete() {
        assertNotNull(eventRepo);

        val event = eventRepo.findById(3L);

        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getStatus());

        assertThat(event.getId(), is(3L));
        assertThat(event.getStatus(), is(Status.Enabled));

        eventRepo.delete(event);

        val eventAfter = eventRepo.findById(3L);

        assertNull(eventAfter);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void deleteById() {
        assertNotNull(eventRepo);

        val event = eventRepo.findById(2L);

        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getStatus());

        assertThat(event.getId(), is(2L));
        assertThat(event.getStatus(), is(Status.Enabled));

        eventRepo.deleteById(event.getId());

        val eventAfter = eventRepo.findById(2L);

        assertNull(eventAfter);
    }

    @Test
    public void findById() {
        assertNotNull(eventRepo);

        val event = eventRepo.findById(3L);

        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getStatus());
        assertNotNull(event.getName());
        assertNotNull(event.getAirDates());
        assertNotNull(event.getAuditoriums());
        assertNotNull(event.getRating());

        assertThat(event.getId(), is(THIRD_EVENT_ID));
        assertThat(event.getStatus(), is(DEFAULT_STATUS));
        assertThat(event.getName(), is(THIRD_EVENT_NAME));
        assertThat(event.getRating(), is(THIRD_EVENT_RATING));
        assertThat(event.getBasePrice(), is(THIRD_EVENT_PRICE));
    }

    @Test
    public void findAll() {
        assertNotNull(eventRepo);

        val events = eventRepo.findAll();

        assertNotNull(events);
        assertThat(events, hasSize(3));

        events.forEach(event -> {
            assertNotNull(event);
            assertNotNull(event.getId());
            assertNotNull(event.getStatus());
            assertNotNull(event.getName());
            assertNotNull(event.getRating());
        });
    }

    @After
    public void tearDown() {
        eventRepo.findAll().forEach(event -> eventRepo.delete(event));
    }
}