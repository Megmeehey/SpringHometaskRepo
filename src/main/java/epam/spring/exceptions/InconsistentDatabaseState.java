package epam.spring.exceptions;

public class InconsistentDatabaseState extends RuntimeException {
    public InconsistentDatabaseState(final String message) {
        super(message);
    }
}
