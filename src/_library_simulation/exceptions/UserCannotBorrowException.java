package _library_simulation.exceptions;

public class UserCannotBorrowException extends RuntimeException{
    public UserCannotBorrowException(){
        super("User cannot borrow anymore publication");
    }
}
