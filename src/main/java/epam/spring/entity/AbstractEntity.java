package epam.spring.entity;

import epam.spring.base.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"status"})
public abstract class AbstractEntity implements Serializable {
    protected Long id;
    private Status status;
}