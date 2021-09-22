package _banksystemproject.exceptions;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(){
        super("<<<<< Conta nao encontrada >>>>>");
    }
}
