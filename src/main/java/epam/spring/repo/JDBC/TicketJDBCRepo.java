package epam.spring.repo.JDBC;

import epam.spring.entity.Ticket;
import epam.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TicketJDBCRepo {
    private static UserJDBCRepo userRepository;
    private static EventJDBCRepo eventRepository;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setUserRepository(UserJDBCRepo userRepository) {
        TicketJDBCRepo.userRepository = userRepository;
    }

    @Autowired
    public void setEventRepository(EventJDBCRepo eventRepository) {
        TicketJDBCRepo.eventRepository = eventRepository;
    }

    public Ticket save(@Nonnull Ticket ticket) {
        Map<String, Object> params = new HashMap<>();
        params.put("seat", ticket.getSeat());
        params.put("date_time", Timestamp.valueOf(ticket.getDateTime()));
        params.put("user_id", ticket.getUser().getId());
        params.put("event_id", ticket.getEvent().getId());

        String sql = "INSERT INTO tickets(seat, date_time, user_id, event_id) VALUES (:seat, :date_time, :user_id, :event_id)";
        namedParameterJdbcTemplate.update(sql, params);
        return ticket;
    }

    public void remove(@Nonnull Ticket ticket) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", ticket.getId());
        String sql = "DELETE FROM tickets WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, params);

    }

    public Set<Ticket> getByUser(@Nonnull User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user.getId());
        String sql = "SELECT * FROM tickets WHERE user_id = :used_id";
        List<Ticket> tickets = namedParameterJdbcTemplate.query(sql, params, new TicketMapper());
        return new HashSet<>(tickets);
    }

    public Map<User, Set<Ticket>> getAll() {
        Map<User, Set<Ticket>> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT * FROM tickets";
        List<Ticket> query = namedParameterJdbcTemplate.query(sql, params, new TicketMapper());
        for (Ticket ticket : query) {
            result.put(ticket.getUser(), getByUser(ticket.getUser()));
        }
        return result;
    }

    private static final class TicketMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setUser(userRepository.getById(resultSet.getLong("user_id")));
            ticket.setEvent(eventRepository.getById(resultSet.getLong("event_id")));
            ticket.setSeat(resultSet.getInt("seat"));
            ticket.setDateTime(LocalDateTime.ofInstant(resultSet.getDate("date_time").toInstant(), ZoneId.systemDefault()));
            return ticket;
        }
    }
}
