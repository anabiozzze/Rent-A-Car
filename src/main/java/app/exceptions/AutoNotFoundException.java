package app.exceptions;

public class AutoNotFoundException extends RuntimeException {
    public AutoNotFoundException() {
        super("This car is not found in the DB");
    }
}
