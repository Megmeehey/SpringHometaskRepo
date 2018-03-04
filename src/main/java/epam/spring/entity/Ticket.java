package epam.spring.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "event", "dateTime", "seat"}, callSuper = true)
public class Ticket extends AbstractEntity implements Comparable<Ticket> {
    private @NonNull User user;
    private @NonNull Event event;
    private @NonNull LocalDateTime dateTime;
    private long seat;

    @Override
    public int compareTo(@Nullable Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }
}
