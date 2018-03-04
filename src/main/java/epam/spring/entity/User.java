package epam.spring.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.NavigableSet;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"firstName", "lastName", "email", "tickets"}, callSuper = true)
public class User extends AbstractEntity {
    private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull String email;
    private NavigableSet<Ticket> tickets = new TreeSet<>();
}
