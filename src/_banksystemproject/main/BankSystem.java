package _banksystemproject.main;
//There are two types of owners.

import _banksystemproject.classes.accounts.*;
import _banksystemproject.classes.ownersofaccounts.LegalPerson;
import _banksystemproject.classes.ownersofaccounts.PhysicalPerson;

import _banksystemproject.exceptions.AccountNotFoundException;
import _banksystemproject.exceptions.InsufficientFundsException;

import javax.swing.JOptionPane;
import java.util.*;

public class BankSystem {
    private static final Map<Integer,Account> accounts = new HashMap<>();
    //When searching objects maps are faster than lists.
    //In this case, Integer refers to the type of key (which will be the number of the account) and Account
    //refers to the value a key points to.

    public static void main(String[] args) {
//        Account mine = new SavingsAccount(new PhysicalPerson("Rafael","123","11/06/2002"),12345,"CN-201 AW56");
//        Account yours = new CurrentAccount(new PhysicalPerson("Ana","456","12/04/1899"),123456,"CN-201 AW56");
//        System.out.println("Are mine and yours equal? " + mine.equals(yours));
//        System.out.println(mine.hashCode() + " " + yours.hashCode());*/
//        If the account number and agency are the same, then those objects will be deemed as equal by the method of the same
//        name. Also, they will have the same hashCode (which was made based on these very attributes).
        while (true) {
            switch (BankSystem.menu("""
                    <<<<< Sistema de Banco 2.0.0 >>>>>
                    1. Criar nova conta
                    2. Exibir informacoes de uma conta
                    3. Movimentar conta
                    4. Sair do sistema""")) {
                case 1:
                    BankSystem.createAccount();
                    break;
                case 2:
                    BankSystem.informationOfAnAccount();
                    break;
                case 3:
                    BankSystem.movementAccount();//Here withdraw and deposit methods were implemented
                    break;
                case 4:
                    BankSystem.exit();
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida >>>>>");
            }
        }
    }
    
    //Auxiliary functions
    private static int menu(String options) {
        return BankSystem.inputDialogForIntegerNumber(options);
    }

    private static void exit() {
        BankSystem.showMessage("<<<<< Sessao finalizada >>>>>");
        System.exit(0);
    }

    private static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private static int inputDialogForIntegerNumber(String msg) {
        return Integer.parseInt(BankSystem.inputDialog(msg));
    }

    private static double inputDialogForFloatNumber(String msg) {
        return Double.parseDouble(BankSystem.inputDialog(msg));
    }

    private static String inputDialog(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    private static int generateAccountNumber(int firstBound, int secondBound) {
        int accountNumber = (int) (firstBound + Math.random() * ((secondBound + 1) - firstBound)); //It Generates a number between firstBound and secondBound valuesEx.: 1000 and 2000
        if (BankSystem.accounts.isEmpty()) return accountNumber;
        while (BankSystem.isThisNumberBeingUsed(accountNumber)) {
            //It will repeat whereas the current value of accountNumber already exists belonging to an account looking out for an unprecedented number
            accountNumber = (int) (firstBound + Math.random() * ((secondBound + 1) - firstBound));
        }
        return accountNumber;
    }

    private static Account getAccountThroughItsNumber(int numberOfAccount) throws AccountNotFoundException {
        Account wanted = BankSystem.accounts.get(numberOfAccount);
        if(wanted == null) {
            throw new AccountNotFoundException();
        }
        return wanted;
    }

    private static boolean isThisNumberBeingUsed(int numberOfAnAccount) {
        return BankSystem.accounts.containsKey(numberOfAnAccount);
    }

    //Elemental functions: they deal with the objects that compose the system
    private static void createAccount() {
        Account newAccount;
        int typeOfAccount, accountNumber;
        while (true) {
            typeOfAccount = BankSystem.menu("""
                    <<<<< Criar Nova Conta >>>>>
                    1. Conta Corrente   
                    2. Conta Poupança
                    3. Conta Especial (Pessoa Fisica)
                    4. Conta Especial (Pessoa Juridica)
                    5. Voltar ao menu principal""");
            accountNumber = BankSystem.generateAccountNumber(1000, 10000);
            switch (typeOfAccount) {
                case 1:
                    String ownerName, cpf, dateOfBirth, agency;
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira o nome da agencia");
                    newAccount = new CurrentAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), accountNumber, agency);
                    BankSystem.accounts.put(accountNumber,newAccount); //key is the account number. Value is the account itself.
                    BankSystem.showMessage(String.format("<<<<< Conta Corrente criada com sucesso >>>>>\n Numero da conta: %d", accountNumber));
                    break;
                case 2:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira o nome da agencia");
                    newAccount = new SavingsAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), accountNumber, agency);
                    BankSystem.accounts.put(accountNumber,newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta Poupança criada com sucesso >>>>>\nNumero da conta: %d\nRendimento: %.2f%%", accountNumber, SavingsAccount.getYieldPercentage()));
                    break;
                case 3:
                    double valueEspecialCheck;
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira o nome da agencia");
                    valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira o valor do cheque especial");
                    newAccount = new EspecialAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), accountNumber, agency, valueEspecialCheck);
                    BankSystem.accounts.put(accountNumber,newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta Especial/Pessoa Fisica criada com sucesso >>>>>\nNumero da conta: %d\nCheque especial: R$ %.2f", accountNumber, valueEspecialCheck));
                    break;
                case 4:
                    String cnpj,dateOfCreation;
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o nome");
                    cnpj = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o CNPJ");
                    dateOfCreation = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira a data de criaçao da empresa/instituiçao (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o nome da agencia");
                    valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o valor do cheque especial");
                    newAccount = new BusinessAccount(new LegalPerson(ownerName, cnpj, dateOfCreation), accountNumber, agency, valueEspecialCheck);
                    BankSystem.accounts.put(accountNumber,newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta Especial/Pessoa Juridica criada com sucesso >>>>>\nNumero da conta: %d\nCheque especial: R$ %.2f", accountNumber, valueEspecialCheck));
                    break;
                case 5: return;
                default: BankSystem.showMessage("<<<<< Escolha uma opçao valida >>>>>");
            }
        }
    }

    private static void informationOfAnAccount() {
        if (BankSystem.accounts.isEmpty()) {
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
            return;
        }
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Informacoes sobre conta >>>>>\nInsira"
                + " o numero da conta");
        try {
            Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount);
            BankSystem.showMessage("<<<<< Informacoes sobre conta >>>>>\n" + requiredAccount);
        }catch(AccountNotFoundException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }

    private static void movementAccount() {
        if (BankSystem.accounts.isEmpty()) {
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
            return;
        }
        while (true) {
            switch (BankSystem.menu("<<<<< Movimentar conta >>>>>\n1. Sacar quantia\n2. Depositar quantia\n"
                    + "3. Transferir de uma conta para outra\n4. Voltar ao menu principal")) {
                case 1:
                    BankSystem.withDrawMoneyOfAnAccount();
                    break;
                case 2:
                    BankSystem.depositMoneyInAnAccount();
                    break;
                case 3:
                    BankSystem.transferMoneyBetweenAccounts();
                    break;
                case 4: return;
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida >>>>>");
            }
        }
    }

    private static void withDrawMoneyOfAnAccount() {
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Sacar quantia de uma conta >>>>>\n"
                + "Insira o numero da conta");
        try {
            Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount); //This line can throw an AccountNotFoundException!
            double amountToGet = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Sacar quantia de uma conta >>>>>%n"
                    + "%s%nInsira a quantia a sacar", requiredAccount));
            requiredAccount.withdraw(amountToGet); //This line can throw an InsufficientFundsException or an IllegalArgumentException!
            BankSystem.showMessage(String.format("<<<<< Sacar quantia de uma conta >>>>>%nSaque efetuado com sucesso!%n" + "Saldo atual da conta: R$%.2f", requiredAccount.getCurrentAmount())); //If everything went as expected, we'll get here!
        }catch(AccountNotFoundException | InsufficientFundsException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }

    private static void depositMoneyInAnAccount() {
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Depositar quantia em uma conta >>>>>\nInsira o numero da conta");
        try {
            Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount); //This line can throw an AccountNotFoundException
            double amountToDeposit = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Depositar quantia em uma conta >>>>>%n%s%nInsira a quantia a depositar", requiredAccount));
            requiredAccount.deposit(amountToDeposit); //This line can throw an IllegalArgumentException
            BankSystem.showMessage(String.format("<<<<< Depositar quantia em uma conta >>>>>%nDeposito efetuado com sucesso!%nSaldo atual da conta: R$%.2f", requiredAccount.getCurrentAmount()));
        }catch(AccountNotFoundException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }

    private static void transferMoneyBetweenAccounts() {
        if (BankSystem.accounts.size() == 1) {
            BankSystem.showMessage("<<<<< Apenas uma conta foi cadastrada >>>>>");
            return;
        }
        int numberOfAccountToTransferWith = BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                + " de uma conta para outra >>>>>%nInsira o numero da conta depositante"));
        try {
            Account accountToTransferWith = BankSystem.getAccountThroughItsNumber(numberOfAccountToTransferWith);
            int numberOfAccountToReceiveAmount = BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                    + " de uma conta para outra >>>>>%nInsira o numero da conta receptora"));
            Account accountToReceiveAmount = BankSystem.getAccountThroughItsNumber(numberOfAccountToReceiveAmount);
            double amountToTransfer = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Transferir de uma conta para outra >>>>>%n%n> Conta depositante <%n%s%n> Conta receptora <%n%s%nInsira a quantia a transferir", accountToTransferWith,accountToReceiveAmount));
            accountToTransferWith.transfer(accountToReceiveAmount,amountToTransfer);
            BankSystem.showMessage(String.format("<<<<< Transferencia realizada com sucesso! >>>>>%nConta depositante: saldo atual (R$%.2f)%nConta receptora: saldo atual (R$%.2f)", accountToTransferWith.getCurrentAmount(), accountToReceiveAmount.getCurrentAmount()));
        }catch(AccountNotFoundException | InsufficientFundsException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }
}