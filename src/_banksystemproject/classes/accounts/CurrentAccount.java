package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.*;
import _banksystemproject.interfaces.ITaxable;

public class CurrentAccount extends Account implements ITaxable {

    public CurrentAccount(PhysicalPerson owner, int accountNumber) {
        super(owner, accountNumber);
    }

    public CurrentAccount(PhysicalPerson owner, int accountNumber, String agency) {
        super(owner, accountNumber, agency, 0.0);//0.0 is the value of the especial check.
    }

    @Override
    public PhysicalPerson getOwner() {
        return (PhysicalPerson) super.getOwner();
    }

    @Override
    public void setOwner(Customer newOwner) {
        if(!(newOwner instanceof PhysicalPerson)) throw new IllegalArgumentException("Invalid Argument!");
        super.setOwner(newOwner);
    }

    @Override
    public double getTaxValue(){
        return super.getCurrentBalance() * 0.01;
    }
}
