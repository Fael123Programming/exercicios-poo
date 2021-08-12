package _library_simulation.exceptions;

public class InvalidRenovationDate extends RuntimeException{
    public InvalidRenovationDate(){
        super("Renovation is too early");
    }
}
