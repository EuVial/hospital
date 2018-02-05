package util;

public class FactoryException extends Exception {
    public FactoryException() {}

    public FactoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FactoryException(final String message) {
        super(message);
    }

    public FactoryException(final Throwable cause) {
        super(cause);
    }
}
