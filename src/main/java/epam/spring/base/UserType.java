package epam.spring.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserType {
    ADMIN(1),
    USER(2);

    @Getter
    private int code;
}