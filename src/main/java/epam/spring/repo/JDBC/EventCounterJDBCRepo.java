package epam.spring.repo.JDBC;

import epam.spring.entity.counter.EventCounter;
import epam.spring.entity.counter.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EventCounterJDBCRepo {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public long getCallingsByName() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventType.NAME.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    public long getCallingsByPrice() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventType.PRICE.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    public long getCallingsByBooking() {
        Map<String, Object> params = new HashMap<>();
        params.put("event_counter_case", EventType.BOOKING.ordinal());
        String sql = "SELECT * FROM event_counter WHERE event_counter_case=:event_counter_case";
        EventCounter eventCounter = namedParameterJdbcTemplate.queryForObject(sql, params, new EventCounterMapper());
        return eventCounter.getCount();
    }

    public void putCallingsByName(long count) {
        long newCount = getCallingsByName() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 0";
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void putCallingsByPrice(long count) {
        long newCount = getCallingsByPrice() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 1";
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void putCallingsByBooking(long count) {
        long newCount = getCallingsByBooking() + count;
        Map<String, Object> params = new HashMap<>();
        params.put("count", newCount);
        String sql = "UPDATE event_counter SET count=:count WHERE event_counter_case = 2";
        namedParameterJdbcTemplate.update(sql, params);
    }

    private static final class EventCounterMapper implements RowMapper<EventCounter> {
        @Override
        public EventCounter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            EventCounter eventCounter = new EventCounter();
            eventCounter.setId(resultSet.getLong("id"));
            eventCounter.setEventType(EventType.values()[resultSet.getInt("event_counter_case")]);
            eventCounter.setCount(resultSet.getLong("count"));
            return eventCounter;
        }
    }
}
