package _banksystemproject.main;
//There are two types of owners.

import _banksystemproject.aux_classes.accounts.*;
import _banksystemproject.aux_classes.ownersofaccounts.LegalPerson;
import _banksystemproject.aux_classes.ownersofaccounts.PhysicalPerson;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankSystem {
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            switch (BankSystem.menu("<<<<< Sistema de Banco 2.0.0 >>>>>\n1. Criar nova conta\n"
                    + "2. Exibir informacoes de uma conta\n3. Movimentar conta\n4. Tabela de "
                    + "contas criadas\n5. Sair do sistema")) {
                case 1:
                    BankSystem.createAccount();
                    break;
                case 2:
                    BankSystem.informationsOfAnAccount();
                    break;
                case 3:
                    BankSystem.movimentAccount();//Here withdraw and deposit methods were implemented
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
        int accountNumber = (int) (firstBound + Math.random() * ((secondBound + 1) - firstBound));
        /*Generates a number between firstBound and secondBound values
            Ex.: 1000 and 2000*/
        if (BankSystem.accounts.isEmpty()) return accountNumber;
        while (BankSystem.getAccountThroughItsNumber(accountNumber) != null) {
            /*It will repeat whereas the current value of accountNumber already
            exists belonging to an account looking for an unprecedented number.*/
            accountNumber = (int) (firstBound + Math.random() * ((secondBound + 1) - firstBound));
        }
        return accountNumber;
    }

    private static Account getAccountThroughItsNumber(int numberOfAccount) {
        for (Account account : BankSystem.accounts) {
            if (account.getAccountNumber() == numberOfAccount) return account;
        }
        return null;//In case that we didn't find an account.
    }

    //Elemental functions: they deal with the objects that compose the system
    private static void createAccount() {
        Account newAccount;
        String ownerName, cpf, cnpj, dateOfBirth, dateOfCreation, agency;
        int typeOfAccount, accountNumber;
        double valueEspecialCheck;
        while (true) {
            typeOfAccount = BankSystem.menu("<<<<< Criar Nova Conta >>>>>\n1. Co"
                    + "nta corrente\n2. Conta poupança\n3. Conta especial (Pessoa "
                    + "fisica)\n4. Conta especial (Pessoa juridica)\n5. Voltar ao "
                    + "menu principal");
            accountNumber = BankSystem.generateAccountNumber(1000, 10000);
            switch (typeOfAccount) {
                case 1:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova conta corrente"
                            + " >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova conta corrente"
                            + " >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova conta corrente"
                            + " >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova conta corrente"
                            + " >>>>>\nInsira o nome da agencia");
                    newAccount = new CurrentAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth),
                            accountNumber, agency);
                    BankSystem.accounts.add(newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta corrente criada com sucesso >>>>>\n"
                            + "Numero da conta: %d", accountNumber));
                    break;
                case 2:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova conta poupança"
                            + ">>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova conta poupança"
                            + ">>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova conta poupança"
                            + ">>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova conta poupança"
                            + ">>>>>\nInsira o nome da agencia");
                    newAccount = new SavingsAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth)
                            , accountNumber, agency);
                    BankSystem.accounts.add(newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta poupança criada com sucesso >>>>>\n"
                                    + "Numero da conta: %d\nRendimento: %.2f%%",
                            accountNumber, SavingsAccount.getYieldPercentage()));
                    break;
                case 3:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa fisica) >>>>>\nInsira seu nome");
                    cpf = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa fisica) >>>>>\nInsira seu CPF");
                    dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa fisica) >>>>>\nInsira sua data de nasci"
                            + "mento (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa fisica) >>>>>\nInsira o nome da agencia");
                    valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova "
                            + "conta especial (Pessoa fisica) >>>>>\nInsira o valor"
                            + " do cheque especial");
                    newAccount = new EspecialAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth)
                            , accountNumber, agency, valueEspecialCheck);
                    BankSystem.accounts.add(newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta especial/Pessoa fisica criada com sucesso >>>>>\n"
                                    + "Numero da conta: %d\nCheque especial: R$ %.2f",
                            accountNumber, valueEspecialCheck));
                    break;
                case 4:
                    ownerName = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa juridica) >>>>>\nInsira o nome");
                    cnpj = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa juridica) >>>>>\nInsira o CNPJ");
                    dateOfCreation = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa juridica) >>>>>\nInsira a data de criaçao da empresa/"
                            + "instituiçao (formato dd/mm/aaaa)");
                    agency = BankSystem.inputDialog("<<<<< Criar Nova conta especial"
                            + " (Pessoa juridica) >>>>>\nInsira o nome da agencia");
                    valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova "
                            + "conta especial (Pessoa juridica) >>>>>\nInsira o valor"
                            + " do cheque especial");
                    newAccount = new BusinessAccount(new LegalPerson(ownerName, cnpj, dateOfCreation)
                            , accountNumber, agency, valueEspecialCheck);
                    BankSystem.accounts.add(newAccount);
                    BankSystem.showMessage(String.format("<<<<< Conta especial/Pessoa juridica criada com sucesso >>>>>\n"
                                    + "Numero da conta: %d\nCheque especial: R$ %.2f",
                            accountNumber, valueEspecialCheck));
                    break;
                case 5:
                    return;
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opçao valida >>>>>");
            }
        }
    }

    private static void informationsOfAnAccount() {
        if (BankSystem.accounts.isEmpty()) {
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
            return;
        }
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Informacoes sobre conta >>>>>\nInsira"
                + " o numero da conta");
        Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if (requiredAccount == null) {
            BankSystem.showMessage("<<<<< Conta nao encontrada >>>>>");
            return;
        }
        BankSystem.showMessage("<<<<< Informacoes sobre conta >>>>>\n" + requiredAccount);
    }

    private static void movimentAccount() {
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
                case 4:
                    return;
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida >>>>>");
            }
        }
    }

    private static void withDrawMoneyOfAnAccount() {
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Sacar quantia de uma conta >>>>>\n"
                + "Insira o numero da conta");
        Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if (requiredAccount == null) {
            BankSystem.showMessage("<<<<< Conta nao encontrada >>>>>");
            return;
        }
        if (requiredAccount.getCurrentAmount() == 0 && !(requiredAccount.getType().equals("Conta especial/"
                + "Pessoa fisica") ^ requiredAccount.getType().equals("Conta especial/Pessoa juridica"))) {
            //If requiredAccount hasn't anymore money and doesn't belong to an especial account type.
            BankSystem.showMessage("<<<<< Conta sem saldo >>>>>");
            return;
        }
        if (requiredAccount.getType().equals("Conta especial/Pessoa fisica") ^ requiredAccount.getType().equals(
                "Conta especial/Pessoa juridica")) {
            EspecialAccount castingAccount = (EspecialAccount) requiredAccount;
            if ((castingAccount.getCurrentAmount() * -1) == castingAccount.getValueEspecialCheck()) {
                BankSystem.showMessage("<<<<< Cheque especial atingido >>>>>");
                return;
            }
        }
        double amountToGet = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Sacar quantia de uma conta >>>>>%n"
                + "%s%nInsira a quantia a sacar", requiredAccount));
        if (requiredAccount.withdraw(amountToGet)) {
            BankSystem.showMessage(String.format("<<<<< Sacar quantia de uma conta >>>>>%nSaque efetuado com sucesso!%n"
                    + "Saldo atual da conta: R$%.2f", requiredAccount.getCurrentAmount()));
            return;
        }
        BankSystem.showMessage("<<<<< Erro no saque - Verifique sua quantia e saldo >>>>>");
    }

    private static void depositMoneyInAnAccount() {
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Depositar quantia em uma conta >>>>>\n"
                + "Insira o numero da conta");
        Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if (requiredAccount == null) {
            BankSystem.showMessage("<<<<< Conta nao encontrada >>>>>");
            return;
        }
        double amountToDeposit = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Depositar quantia em uma conta >>>>>%n"
                + "%s%nInsira a quantia a depositar", requiredAccount));
        if (requiredAccount.deposit(amountToDeposit)) {
            BankSystem.showMessage(String.format("<<<<< Depositar quantia em uma conta >>>>>%nDeposito efetuado com sucesso!%n"
                    + "Saldo atual da conta: R$%.2f", requiredAccount.getCurrentAmount()));
            return;
        }
        BankSystem.showMessage("<<<<< Erro no deposito - Verifique sua quantia >>>>>");
    }

    private static void transferMoneyBetweenAccounts() {
        if (BankSystem.accounts.size() == 1) {
            BankSystem.showMessage("<<<<< Apenas uma conta foi cadastrada >>>>>");
            return;
        }
        int numberOfAccountToTransferWith = BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                + " de uma conta para outra >>>>>%nInsira o numero da conta depositante"));
        Account accountToTransferWith = BankSystem.getAccountThroughItsNumber(numberOfAccountToTransferWith);
        if (accountToTransferWith == null) {
            BankSystem.showMessage("<<<<< Conta nao encontrada >>>>>");
            return;
        }
        int numberOfAccountToReceiveAmount = BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                + " de uma conta para outra >>>>>%nInsira o numero da conta receptora"));
        Account accountToReceiveAmount = BankSystem.getAccountThroughItsNumber(numberOfAccountToReceiveAmount);
        if (accountToReceiveAmount == null) {
            BankSystem.showMessage("<<<<< Conta nao encontrada >>>>>");
            return;
        }
        double amountToTransfer = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Transferir de uma conta para outra >>>>>%n"
                        + "%n> Conta depositante <%n%s%n%n> Conta receptora <%n%s%n%nInsira a quantia a transferir", accountToTransferWith,
                accountToReceiveAmount));
        if (amountToTransfer <= 0) {
            BankSystem.showMessage("<<<<< Quantia invalida >>>>>");
            return;
        }
        if (BankSystem.inputDialog(String.format("<<<<< Transferir de uma conta para outra >>>>>%n"
                        + "%n> Conta depositante <%n%s%n%n> Conta receptora <%n%s%n%nConfirmar operacao? (s/n)", accountToTransferWith,
                accountToReceiveAmount)).toLowerCase().split("")[0].equals("s")) {
            if (accountToTransferWith.transfer(accountToReceiveAmount, amountToTransfer)) {
                BankSystem.showMessage(String.format("<<<<< Transferencia realizada com sucesso! >>>>>%nConta depositante: "
                                + "saldo atual (R$%.2f)%nConta receptora: saldo atual (R$%.2f)", accountToTransferWith.getCurrentAmount(),
                        accountToReceiveAmount.getCurrentAmount()));
            } else BankSystem.showMessage("<<<<< Impossivel efetuar a transferencia >>>>>");
        } else BankSystem.showMessage("<<<<< Operacao cancelada com sucesso >>>>>");
    }

    private static void tableOfAccounts() {
        if (BankSystem.accounts.isEmpty()) {
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
            return;
        }
        Collections.sort(BankSystem.accounts);
        System.out.println("<<<<< Contas cadastradas no sistema ordenadas do maior para o menor saldo >>>>>");
        for (Account account : BankSystem.accounts) {
            System.out.println(account + "\n");
        }
        System.out.println("\n");
        BankSystem.showMessage("<<<<< Tabela de contas desenhada no console ou prompt de comando >>>>>");
    }
}