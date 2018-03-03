package epam.spring.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EventRating {
    LOW(1),
    MID(2),
    HIGH(3);

    @Getter
    private int grade;
}