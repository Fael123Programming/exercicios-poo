package _banksystemproject.aux_classes.accounts;

import _banksystemproject.aux_classes.ownersofaccounts.Customer;
import _banksystemproject.aux_classes.ownersofaccounts.PhysicalPerson;

public class SavingsAccount extends Account {
    //Change it, setting this account as subclass of class currentAccount instead.
    private static double yieldPercentage = 0.005;

    public SavingsAccount(PhysicalPerson owner, int accountNumber) {
        super(owner, accountNumber);
    }

    public SavingsAccount(PhysicalPerson owner, int accountNumber, String agency) {
        super(owner, accountNumber, agency, 0.0);
    }

    public boolean makeMoney() {
        if (super.getCurrentAmount() == 0) return false;
        super.deposit(super.getCurrentAmount() * SavingsAccount.yieldPercentage);
        return true;
    }

    public static double getYieldPercentage() {
        return SavingsAccount.yieldPercentage * 100;
    }

    public static void setYieldPercentage(double newPercentage) {
        SavingsAccount.yieldPercentage = newPercentage / 100;
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
