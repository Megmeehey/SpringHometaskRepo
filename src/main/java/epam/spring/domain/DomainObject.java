package epam.spring.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"status"})
public abstract class DomainObject implements Serializable {
    protected Long id;
    private Status status;
}