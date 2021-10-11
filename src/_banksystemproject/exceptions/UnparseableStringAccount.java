package _banksystemproject.exceptions;

public class UnparseableStringAccount extends Exception {

    public UnparseableStringAccount(){
        super("Impossible to generate an account object based on the string");
    }
}
