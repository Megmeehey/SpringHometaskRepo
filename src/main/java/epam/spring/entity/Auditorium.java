package epam.spring.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"name", "numberOfSeats", "vipSeats"}, callSuper = true)
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auditorium extends AbstractEntity {
    @NonNull String name;
    long numberOfSeats;
    Set<Long> vipSeats = Collections.emptySet();

    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param seats Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream()
                .filter(seat -> vipSeats.contains(seat))
                .count();
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1)
                .boxed()
                .collect(Collectors.toSet());
    }
}
