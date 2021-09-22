package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.Customer;
import _banksystemproject.classes.ownersofaccounts.LegalPerson;

public class BusinessAccount extends EspecialAccountGeneric {
    public BusinessAccount(LegalPerson owner, int accountNumber, String agency,
                           double valueEspecialCheck) {
        super(owner, accountNumber, agency, valueEspecialCheck);
    }

    @Override
    public LegalPerson getOwner() {
        return (LegalPerson) super.getOwner();
    }

    @Override
    public void setOwner(Customer newOwner) {
        if (newOwner.getClass().equals(LegalPerson.class)) {
            super.setOwner(newOwner);
        } else throw new IllegalArgumentException("## Invalid Argument ##");
    }
}
