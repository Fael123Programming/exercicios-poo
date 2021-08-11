package _banksystemproject.aux_classes.accounts;

import _banksystemproject.aux_classes.ownersofaccounts.Customer;

public abstract class Account implements Comparable<Account> {
    private static int numberOfAccounts = 0;
    private int accountNumber;
    private double currentAmount, valueEspecialCheck;
    private String agency;
    private Customer owner;

    public Account() {//Empty constructor function
        Account.numberOfAccounts++;
    }

    public Account(Customer owner, int accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.currentAmount = 0.0;
        this.valueEspecialCheck = 0.0;
        Account.numberOfAccounts++;
    }

    public Account(Customer owner, int accountNumber, String agency, double valueEspecialCheck) {
        this(owner, accountNumber);//It calls the constructor function above
        this.agency = agency;
        this.valueEspecialCheck = valueEspecialCheck;
        this.currentAmount = 0.0;
        Account.numberOfAccounts++;
    }

    public boolean withdraw(double quant) {
        if (quant <= 0 || quant > this.valueEspecialCheck + this.getCurrentAmount()) return false;
        this.setCurrentAmount(this.getCurrentAmount() - quant);
        return true;
    }

    public boolean transfer(Account targetAccount, double quant) {
        if (this.withdraw(quant)) {
            targetAccount.deposit(quant);
            return true;
        }
        return false;
    }

    public boolean deposit(double quant) {
        if (quant <= 0) return false;
        this.currentAmount += quant;
        return true;
    }

    public static int getNumberOfAccounts() {
        return Account.numberOfAccounts;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int newNumber) {
        this.accountNumber = newNumber;
    }

    public Customer getOwner() {
        return this.owner;
    }

    public void setOwner(Customer newOwner) {
        this.owner = newOwner;
    }

    public double getCurrentAmount() {
        return this.currentAmount;
    }

    protected void setCurrentAmount(double newAmount) {
        this.currentAmount = newAmount;
    }

    public String getAgency() {
        return this.agency;
    }

    public void setAgency(String newAgency) {
        this.agency = newAgency;
    }

    public double getValueEspecialCheck() {
        return this.valueEspecialCheck;
    }

    public String getType() {
        String type = this.getClass().descriptorString().split("/")[2].replace(";", "");
        //It catches only the name of the class object 'this' belongs to.
        switch (type) {
            case "CurrentAccount":
                type = "Conta corrente";
                break;
            case "EspecialAccount":
                type = "Conta especial/Pessoa fisica";
                break;
            case "BusinessAccount":
                type = "Conta especial/Pessoa juridica";
                break;
            case "SavingsAccount":
                type = "Conta poupança";
                break;
            default:
                type = null;
        }
        return type;
    }

    @Override
    public int compareTo(Account accountToCompareWith) {//It sorts from greater amounts to less ones
        if (this.currentAmount > accountToCompareWith.getCurrentAmount()) return -1;
        else if (this.currentAmount < accountToCompareWith.getCurrentAmount()) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("--------------------------------------%n"
                        + "Responsável: %s%nSaldo atual: R$ %.2f%n"
                        + "Número: %d%nTipo: %s%nAgencia: %s%n"
                        + "--------------------------------------",
                this.owner.getName(), this.currentAmount,
                this.accountNumber, this.getType(), this.agency);
    }
}