package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.*;

public class BusinessAccount extends EspecialAccountGeneric {

    public BusinessAccount(LegalPerson owner, int accountNumber, String agency, double valueEspecialCheck) {
        super(owner, accountNumber, agency, valueEspecialCheck);
    }

    @Override
    public LegalPerson getOwner() { return (LegalPerson) super.getOwner(); }

    @Override
    public void setOwner(Customer newOwner) throws IllegalArgumentException {
        if (!(newOwner instanceof LegalPerson)) throw new IllegalArgumentException("Invalid Argument!");
        super.setOwner(newOwner);
    }
}
