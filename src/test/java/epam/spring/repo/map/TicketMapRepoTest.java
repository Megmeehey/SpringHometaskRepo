package epam.spring.repo.map;

import epam.spring.UnitTest;
import epam.spring.base.EventRating;
import epam.spring.base.Status;
import epam.spring.entity.Auditorium;
import epam.spring.entity.Event;
import epam.spring.entity.Ticket;
import epam.spring.entity.User;
import epam.spring.repo.TicketRepoI;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@Category(UnitTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TicketMapRepoTest {
    private static final Status DEFAULT_STATUS = Status.Enabled;
    private static final long EVENT_ID = 3L;
    private static final String EVENT_NAME = "Test event";
    private static final double EVENT_BASE_PRICE = 10.0;
    private static final EventRating EVENT_RATING = EventRating.HIGH;
    private static final String USER_ONE_EMAIL = "User one email";
    private static final String USER_ONE_FIRST_NAME = "User one first name";
    private static final String USER_ONE_LAST_NAME = "User one last name";
    private static final String USER_TWO_EMAIL = "User two email";
    private static final String USER_TWO_FIRST_NAME = "User two first name";
    private static final String USER_TWO_LAST_NAME = "User two last name";
    private static final Long TICKET_ONE_ID = 1L;
    @Autowired
    private TicketRepoI ticketRepo;

    @Before
    public void setUp() {
        Event event = getEvent(EVENT_ID, DEFAULT_STATUS, EVENT_RATING, EVENT_BASE_PRICE, EVENT_NAME);

        User userOne = getUser(USER_ONE_EMAIL, USER_ONE_FIRST_NAME, USER_ONE_LAST_NAME);

        User userTwo = getUser(USER_TWO_EMAIL, USER_TWO_FIRST_NAME, USER_TWO_LAST_NAME);

        ticketRepo.save(buildTicket(1L, DEFAULT_STATUS, LocalDateTime.now(), 1L, event, userOne));
        ticketRepo.save(buildTicket(1L, DEFAULT_STATUS, LocalDateTime.now(), 2L, event, userOne));
        ticketRepo.save(buildTicket(1L, DEFAULT_STATUS, LocalDateTime.now(), 4L, event, userTwo));
    }

    private User getUser(String userOneEmail, String userOneFirstName, String userOneLastName) {
        val userOne = new User();
        userOne.setEmail(userOneEmail);
        userOne.setFirstName(userOneFirstName);
        userOne.setLastName(userOneLastName);
        return userOne;
    }

    private Event getEvent(long eventId, Status defaultStatus,
                           EventRating eventRating, double eventBasePrice, String eventName) {
        final TreeMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(LocalDateTime.now(), new Auditorium());

        final TreeSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(LocalDateTime.now());

        val event = new Event();
        event.setId(eventId);
        event.setStatus(defaultStatus);
        event.setAuditoriums(auditoriums);
        event.setAirDates(airDates);
        event.setRating(eventRating);
        event.setBasePrice(eventBasePrice);
        event.setName(eventName);
        return event;
    }

    private Ticket buildTicket(Long id, Status status, LocalDateTime date, long seat, Event event, User user) {
        val ticket = buildTicket(date, seat, event, user);
        ticket.setId(id);
        ticket.setStatus(status);
        return ticket;
    }

    private Ticket buildTicket(LocalDateTime date, long seat, Event event, User user) {
        Ticket ticket = new Ticket();
        ticket.setDateTime(date);
        ticket.setSeat(seat);
        ticket.setEvent(event);
        ticket.setUser(user);
        return ticket;
    }

    @After
    public void tearDown() {
        ticketRepo.findAll().forEach(ticket -> ticketRepo.delete(ticket));
    }

    @Test
    public void save() {
        assertNotNull(ticketRepo);

        final Ticket ticket = buildTicket(LocalDateTime.now(), 10L,
                getEvent(EVENT_ID, DEFAULT_STATUS, EVENT_RATING, EVENT_BASE_PRICE, EVENT_NAME),
                getUser(USER_ONE_EMAIL, USER_ONE_FIRST_NAME, USER_ONE_LAST_NAME));

        assertNull(ticket.getId());
        assertNull(ticket.getStatus());

        ticketRepo.save(ticket);

        assertNotNull(ticket.getId());
        assertNotNull(ticket.getStatus());
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void delete() {
        assertNotNull(ticketRepo);

        final Ticket ticket = ticketRepo.findById(3L);

        assertNotNull(ticket);
        assertNotNull(ticket.getId());
        assertNotNull(ticket.getEvent());
        assertNotNull(ticket.getUser());
        assertNotNull(ticket.getDateTime());

        ticketRepo.delete(ticket);

        final Ticket ticketAfter = ticketRepo.findById(3L);

        assertNull(ticketAfter);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void deleteById() {
        assertNotNull(ticketRepo);

        final Ticket ticket = ticketRepo.findById(3L);

        assertNotNull(ticket);
        assertNotNull(ticket.getId());
        assertNotNull(ticket.getEvent());
        assertNotNull(ticket.getUser());
        assertNotNull(ticket.getDateTime());

        ticketRepo.deleteById(ticket.getId());

        final Ticket ticketAfter = ticketRepo.findById(3L);

        assertNull(ticketAfter);
    }

    @Test
    public void findById() {
        final Ticket ticket = ticketRepo.findById(1L);

        assertNotNull(ticket);
        assertNotNull(ticket.getId());
        assertEquals( TICKET_ONE_ID, ticket.getId());
        assertNotNull(ticket.getEvent());
        assertNotNull(ticket.getUser());

        ticketRepo.deleteById(ticket.getId());
    }

    @Test
    public void findAll() {
        final List<Ticket> tickets = ticketRepo.findAll();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        tickets.forEach(ticket -> {
            assertNotNull(ticket);
            assertNotNull(ticket.getId());
            assertNotNull(ticket.getEvent());
            assertNotNull(ticket.getUser());
        });
    }
}