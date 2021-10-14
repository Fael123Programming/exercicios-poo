package _banksystemproject.classes.accounts;

import _banksystemproject.classes.ownersofaccounts.*;
import _banksystemproject.exceptions.*;

import java.time.LocalDate;
import java.util.*;

public abstract class Account implements Comparable<Account> {
    private static final int CURRENT_ACCOUNT = 1, SAVINGS_ACCOUNT = 2, ESPECIAL_ACCOUNT = 3, BUSINESS_ACCOUNT = 4;
    private static int numberOfAccounts = 0;
    private int accountNumber;
    private double currentBalance, valueEspecialCheck;
    private String agency;
    private Customer owner;

    public Account() { //Empty constructor function
        Account.numberOfAccounts++;
    }

    public Account(Customer owner, int accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        Account.numberOfAccounts++;
    }

    public Account(Customer owner, int accountNumber, String agency, double valueEspecialCheck) {
        this(owner, accountNumber);//It calls the constructor function above
        this.agency = agency;
        this.valueEspecialCheck = valueEspecialCheck;
        Account.numberOfAccounts++;
    }

    public void withdraw(double quantity) throws InsufficientFundsException, IllegalArgumentException {
        if(quantity <= 0) throw new IllegalArgumentException("<<<<< Quantia inválida >>>>>");
        if(quantity > this.valueEspecialCheck + this.currentBalance) throw new InsufficientFundsException(quantity);
        this.currentBalance -= quantity;
    }

    public void transfer(Account targetAccount, double quantity) throws InsufficientFundsException,IllegalArgumentException {
        this.withdraw(quantity);
        targetAccount.deposit(quantity);
    }

    public void deposit(double quantity) throws IllegalArgumentException {
        if (quantity < 0) throw new IllegalArgumentException("<<<<< Quantia inválida >>>>>");
        this.currentBalance += quantity;
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

    public double getCurrentBalance() {
        return this.currentBalance;
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

    public int getType() {
        if (CurrentAccount.class.equals(this.getClass())) {
            return Account.CURRENT_ACCOUNT;
        } else if (SavingsAccount.class.equals(this.getClass())) {
            return Account.SAVINGS_ACCOUNT;
        } else if (EspecialAccount.class.equals(this.getClass())) {
            return Account.ESPECIAL_ACCOUNT;
        } else if (BusinessAccount.class.equals(this.getClass())) {
            return Account.BUSINESS_ACCOUNT;
        }
        return -1;
    }

    public static Account parseString(String accountStr) throws UnparseableStringAccount {
        if (accountStr == null || accountStr.isBlank()) throw new UnparseableStringAccount();
        Account parsedAccount;
        String[] keysAndValues = new String[6]; //All attributes an account has is six.
        StringBuilder builder = new StringBuilder();
        boolean nestedObject = false;
        byte counter = 0;
        accountStr = accountStr.substring(1, accountStr.length() - 1); //Getting the first couple of {} out of the string.
        for (char ch : accountStr.toCharArray()) {
            builder.append(ch);
            if (ch == '{') nestedObject = true;
            else if (ch == '}') nestedObject = false;
            if (nestedObject) continue;
            if (ch == ',') {
                builder.deleteCharAt(builder.length() - 1); //The remaining comma!
                keysAndValues[counter++] = builder.toString();
                builder = new StringBuilder();
            }
        }
        keysAndValues[counter] = builder.toString();
        String agency = keysAndValues[5].split(":")[1].trim();
        int accountNumber = Integer.parseInt(keysAndValues[2].split(":")[1].trim()), accountType = Integer.parseInt(keysAndValues[1].split(":")[1].trim());
        double currentBalance = Double.parseDouble(keysAndValues[3].split(":")[1].trim()), valueEspecialCheck = Double.parseDouble(keysAndValues[4].split(":")[1].trim());
        String[] ownerKeysAndValues = keysAndValues[0].substring(keysAndValues[0].indexOf('{') + 1, keysAndValues[0].indexOf('}')).split(",");
        String ownerName = ownerKeysAndValues[0].split(":")[1], ownerDateOfBirth = ownerKeysAndValues[1].split(":")[1].trim(), ownerIdentification = ownerKeysAndValues[2].split(":")[1].trim();
        Customer accountOwner = switch(accountType) {
            case Account.CURRENT_ACCOUNT, Account.SAVINGS_ACCOUNT, Account.ESPECIAL_ACCOUNT -> new PhysicalPerson(ownerName, LocalDate.parse(ownerDateOfBirth), ownerIdentification);
            case Account.BUSINESS_ACCOUNT -> new LegalPerson(ownerName, LocalDate.parse(ownerDateOfBirth), ownerIdentification);
            default -> throw new UnparseableStringAccount();
        };
        switch(accountType) {
            case Account.CURRENT_ACCOUNT -> parsedAccount = new CurrentAccount((PhysicalPerson) accountOwner,accountNumber, agency);
            case Account.SAVINGS_ACCOUNT -> parsedAccount = new SavingsAccount((PhysicalPerson) accountOwner, accountNumber, agency);
            case Account.ESPECIAL_ACCOUNT -> parsedAccount = new EspecialAccount((PhysicalPerson) accountOwner, accountNumber, agency, valueEspecialCheck);
            case Account.BUSINESS_ACCOUNT -> parsedAccount = new BusinessAccount((LegalPerson) accountOwner, accountNumber, agency, valueEspecialCheck);
            default -> throw new UnparseableStringAccount();
        }
        parsedAccount.deposit(currentBalance);
        return parsedAccount;
    }

    public String getStandardized(){
        StringBuilder info = new StringBuilder();
        info.append("--------------------------------------------------------------------------\n");
        info.append("Responsavel: ").append(this.getOwner().getName()).append("\n");
        info.append("Numero da conta: ").append(this.getAccountNumber()).append("\n");
        info.append("Tipo da conta: ").append(this.getType()).append("\n");
        info.append("Saldo atual: ").append(this.getCurrentBalance()).append("\n");
        info.append("Agencia: ").append(this.getAgency()).append("\n");
        info.append("--------------------------------------------------------------------------");
        return info.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.owner, this.getType(), this.accountNumber, this.currentBalance, this.valueEspecialCheck, this.agency); //This method utilizes its arguments to generate a hash code to an object of this class.
    }

    @Override
    public boolean equals(Object toCompare) {
        if(!(toCompare instanceof Account casted)) return false;
        return (this.owner == casted.owner && this.getType() == casted.getType() && this.accountNumber == casted.accountNumber && this.currentBalance == casted.currentBalance && this.valueEspecialCheck == casted.valueEspecialCheck && this.agency.equals(casted.agency));
    }

    @Override
    public String toString() {
        return String.format("{responsavel: %s, tipo: %d, numero: %d, saldo atual: %.2f, valor cheque especial: %.2f, agencia: %s}", this.owner, this.getType(), this.accountNumber, this.currentBalance, this.valueEspecialCheck, this.agency);
    }

    @Override
    public int compareTo(Account toCompare) {
        return Integer.compare(this.hashCode(),toCompare.hashCode());
    }
}