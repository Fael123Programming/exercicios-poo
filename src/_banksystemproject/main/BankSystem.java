package _banksystemproject.main;
//There are two types of owners.

import _banksystemproject.classes.accounts.*;
import _banksystemproject.classes.ownersofaccounts.LegalPerson;
import _banksystemproject.classes.ownersofaccounts.PhysicalPerson;

import _banksystemproject.exceptions.AccountNotFoundException;
import _banksystemproject.exceptions.InsufficientFundsException;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankSystem {
    private static final List<Account> accounts = new ArrayList<>();
    public static void main(String[] args) {
        while (true) {
            switch (BankSystem.menu("""
                    <<<<< Sistema de Banco 2.0.0 >>>>>
                    1. Criar nova conta
                    2. Exibir informacoes de uma conta
                    3. Movimentar conta
                    4. Tabela de contas criadas
                    5. Sair do sistema""")) {
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
                    BankSystem.tableOfAccounts();
                    break;
                case 5:
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
        for (Account account : BankSystem.accounts) {
            if (account.getAccountNumber() == numberOfAccount) return account;
        }
        throw new AccountNotFoundException();
    }

    private static boolean isThisNumberBeingUsed(int numberOfAnAccount) {
        for(Account acc : BankSystem.accounts) if(acc.getAccountNumber() == numberOfAnAccount) return true;
        return false;
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
                    BankSystem.accounts.add(newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta Corrente criada com sucesso >>>>>\n Numero da conta: %d", accountNumber));
                    break;
                case 2:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira o nome da agencia");
                    newAccount = new SavingsAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), accountNumber, agency);
                    BankSystem.accounts.add(newAccount);
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
                    BankSystem.accounts.add(newAccount);
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
                    BankSystem.accounts.add(newAccount);
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

    private static void tableOfAccounts() {
        if (BankSystem.accounts.isEmpty()) {
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
            return;
        }
        Collections.sort(BankSystem.accounts);
        System.out.println("<<<<< Contas cadastradas no sistema ordenadas alfabeticamente >>>>>");
        for (Account account : BankSystem.accounts) {
            System.out.println(account + "\n");
        }
        System.out.println("\n");
        BankSystem.showMessage("<<<<< Tabela de contas desenhada no console ou prompt de comando >>>>>");
    }
}