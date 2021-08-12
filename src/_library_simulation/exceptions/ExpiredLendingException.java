package _library_simulation.exceptions;

public class ExpiredLendingException extends RuntimeException{
    public ExpiredLendingException(){
        super("Lending already expired");
    }
}
