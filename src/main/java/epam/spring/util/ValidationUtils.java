package epam.spring.util;

import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
public final class ValidationUtils {

    public static boolean isValidId(final Long id) {
        return id == null || id > 0;
    }

    public static boolean isValidPrice(final double price) {
        return price >= 0;
    }

    public static boolean isNotEmpty(final String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isValid(final Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.stream().noneMatch(Objects::isNull);
    }

    public static boolean hasEmptyElements(final Collection<?> collection) {
        return collection.stream().anyMatch(Objects::isNull);
    }
}