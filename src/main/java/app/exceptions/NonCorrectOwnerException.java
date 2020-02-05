package app.exceptions;

public class NonCorrectOwnerException extends RuntimeException{
    public NonCorrectOwnerException(){
        super("Owner and car don't match");
    }
}
