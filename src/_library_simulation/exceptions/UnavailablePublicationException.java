package _library_simulation.exceptions;

public class UnavailablePublicationException extends RuntimeException{
    public UnavailablePublicationException(){
        super("Publication is not available to be borrowed");
    }
}
