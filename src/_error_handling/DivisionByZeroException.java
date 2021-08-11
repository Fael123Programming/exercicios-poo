package _error_handling;

public class DivisionByZeroException extends ArithmeticException{
    public DivisionByZeroException(){
        super("Can't divide an integer by zero");
    }
}
