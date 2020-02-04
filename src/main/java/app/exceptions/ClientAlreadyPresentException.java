package app.exceptions;

public class ClientAlreadyPresentException extends RuntimeException {

    public ClientAlreadyPresentException() {
        super("This client is already in the DB");
    }

}
