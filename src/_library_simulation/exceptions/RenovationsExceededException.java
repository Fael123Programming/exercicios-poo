package _library_simulation.exceptions;

public class RenovationsExceededException extends RuntimeException{
    public RenovationsExceededException(){
        super("This lending cannot be renewed");
    }
}
