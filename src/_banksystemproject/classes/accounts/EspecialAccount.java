package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.Customer;
import _banksystemproject.classes.ownersofaccounts.PhysicalPerson;

public class EspecialAccount extends EspecialAccountGeneric {
    public EspecialAccount(PhysicalPerson owner, int accountNumber) {
        super(owner, accountNumber);
    }

    public EspecialAccount(PhysicalPerson owner, int accountNumber, String agency,
                           double valueEspecialCheck) {
        super(owner, accountNumber, agency, valueEspecialCheck);
    }

    @Override
    public PhysicalPerson getOwner() {
        return (PhysicalPerson) super.getOwner();
    }

    @Override
    public void setOwner(Customer newOwner) {
        if (newOwner.getClass().equals(PhysicalPerson.class)) {
            super.setOwner(newOwner);
        } else throw new IllegalArgumentException("## Invalid Argument ##");
    }
}
