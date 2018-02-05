package dao;

public class PersistException extends Exception {
    public PersistException() {}

    public PersistException(final String message) {
        super(message);
    }

    public PersistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersistException(final Throwable cause) {
        super(cause);
    }
}
