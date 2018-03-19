package epam.spring.repo.map;

import epam.spring.entity.counter.EventCounter;
import epam.spring.entity.counter.EventType;
import epam.spring.util.ValidationUtils;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class EventCounterMapRepo extends AbstractMapRepo<EventCounter> {
    private void isValid(@NonNull EventCounter entity) {
        if (ValidationUtils.isValidLong(entity.getId())
                && ValidationUtils.isValidLong(entity.getCount())) {
            throw new IllegalArgumentException("EventCounter " + entity + " is not a valid EventCounter");
        }
    }

    public EventCounterMapRepo() {
        source.clear();
        this.save(new EventCounter().setCount(0L).setEventType(EventType.NAME));
        this.save(new EventCounter().setCount(0L).setEventType(EventType.PRICE));
        this.save(new EventCounter().setCount(0L).setEventType(EventType.BOOKING));
    }

    public long getAmountByName() {
        return source.get(1L).getCount();
    }

    public long getAmountByPrice() {
        return source.get(2L).getCount();
    }

    public long getAmountByBooking() {
        return source.get(3L).getCount();
    }

    public void addToCounterByName(long count) {
        final EventCounter counter = source.get(1L);
        source.put(1L, counter.setCount(counter.getCount() + count));
    }

    public void addToCounterByPrice(long count) {
        final EventCounter counter = source.get(2L);
        source.put(2L, counter.setCount(counter.getCount() + count));
    }

    public void addToCounterByBooking(long count) {
        final EventCounter counter = source.get(3L);
        source.put(3L, counter.setCount(counter.getCount() + count));
    }
}
