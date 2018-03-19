package epam.spring.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"firstName", "lastName", "email", "tickets"}, callSuper = true)
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity {
    @NonNull String firstName;
    @NonNull String lastName;
    @NonNull String email;
    LocalDate dateOfBirth;
    NavigableSet<Ticket> tickets = new TreeSet<>();
}
