package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.Customer;
import _banksystemproject.exceptions.InsufficientFundsException;

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

    public void withdraw(double quantity) throws InsufficientFundsException,IllegalArgumentException {
        if(quantity <= 0) throw new IllegalArgumentException("<<<<< Quantia inválida >>>>>");
        if(quantity > this.valueEspecialCheck + this.currentAmount) throw new InsufficientFundsException(quantity);
        this.currentAmount -= quantity;
    }

    public void transfer(Account targetAccount, double quantity) throws InsufficientFundsException,IllegalArgumentException{
        this.withdraw(quantity);
        targetAccount.deposit(quantity);
    }

    public void deposit(double quantity) throws IllegalArgumentException{
        if (quantity <= 0) throw new IllegalArgumentException("<<<<< Quantia inválida >>>>>");
        this.currentAmount += quantity;
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
        if (CurrentAccount.class.equals(this.getClass())) {
            return "Conta Corrente";
        } else if (EspecialAccount.class.equals(this.getClass())) {
            return "Conta Especial/Pessoa Fisica";
        } else if (BusinessAccount.class.equals(this.getClass())) {
            return "Conta Especial/Pessoa Juridica";
        } else if (SavingsAccount.class.equals(this.getClass())) {
            return "Conta Poupança";
        }
        return null;
    }

    @Override
    public int compareTo(Account accountToCompareWith) {//It sorts accounts by their owner names, alphabetically.
        return this.owner.getName().compareTo(accountToCompareWith.getOwner().getName());
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