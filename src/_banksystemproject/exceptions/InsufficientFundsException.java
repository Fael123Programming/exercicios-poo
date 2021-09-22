package _banksystemproject.exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(){
        super("<<<<< Saldo insuficiente >>>>>");
    }

    public InsufficientFundsException(double value) {
        super("<<<<< Saldo insuficiente para sacar R$ " + value + " >>>>>");
    }
}
