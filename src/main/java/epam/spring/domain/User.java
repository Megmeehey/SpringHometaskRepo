package epam.spring.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.NavigableSet;
import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"firstName", "lastName", "email", "tickets"}, callSuper = true)
public class User extends DomainObject {
    private String firstName;
    private String lastName;
    private String email;
    private NavigableSet<Ticket> tickets = new TreeSet<>();
}
