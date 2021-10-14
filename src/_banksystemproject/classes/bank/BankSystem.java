package _banksystemproject.classes.bank;

import _banksystemproject.classes.accounts.Account;
import _banksystemproject.classes.accounts.CurrentAccount;
import _banksystemproject.classes.ownersofaccounts.PhysicalPerson;
import _banksystemproject.data_persistence.FileHandler;
import _banksystemproject.exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class BankSystem {
    private static String agency = "007-JB";
    private String path;
    private static int index = 1000;

    public BankSystem(String filePath){
        this.path = filePath;
    }

    public void start() {
        //Checking if the file already exists or not. If not, this code tries to create a new file based on the first argument passed by terminal to keep all accounts recorded.
        if (!FileHandler.fileExists(this.path)) {
            File file = new File(this.path);
            try {
                if (!file.createNewFile()) throw new IOException();
            } catch (IOException e) {
                Manager.showMessage("<<<<< Infelizmente, um problema aconteceu na criação do arquivo!");
                Manager.exit();
            }
        }
        //------------------------------------------------------------------
        while (true) {
            switch (Manager.menu("""
                    <<<<< Sistema de Banco 2.0.0 >>>>>
                    1. Criar nova conta
                    2. Exibir informacoes de uma conta
                    3. Movimentar conta
                    4. Sair do sistema""")) {
                case 1 -> this.createAccount();
                case 2 -> this.informationOfAnAccount();
                //case 3 -> this.movementAccount();//Here withdraw and deposit methods were implemented
                case 4 -> Manager.exit();
                default -> Manager.showMessage("<<<<< Escolha uma opcao valida >>>>>");
            }
        }
    }


    private Account getAccountThroughItsNumber(int numberOfAccount) throws AccountNotFoundException, FileNotFoundException, UnparseableStringAccount {
        if (numberOfAccount - BankSystem.index >= FileHandler.fileSize(this.path)) throw new AccountNotFoundException();
        String accountString = FileHandler.getLine(this.path, numberOfAccount - BankSystem.index);
        return Account.parseString(accountString);
    }

    //Elemental functions: they deal with the objects that compose the system.
    private void createAccount() {
        Account newAccount;
        int typeOfAccount, numberOfAccount;
        while (true) {
            try {
                numberOfAccount = BankSystem.index + FileHandler.fileSize(this.path);
                typeOfAccount = Manager.menu("""
                        <<<<< Criar Nova Conta >>>>>
                        1. Conta Corrente   
                        2. Conta Poupança
                        3. Conta Especial (Pessoa Fisica)
                        4. Conta Especial (Pessoa Juridica)
                        5. Voltar ao menu principal""");
                switch (typeOfAccount) {
                    case 1 -> {
                        String ownerName, cpf, dateOfBirth;
                        ownerName = Manager.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira seu nome");
                        cpf = Manager.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira seu CPF");
                        dateOfBirth = Manager.inputDialog("<<<<< Criar Nova Conta Corrente >>>>>\nInsira sua data de nascimento (formato aaaa-mm-dd)");
                        newAccount = new CurrentAccount(new PhysicalPerson(ownerName, LocalDate.of(Integer.parseInt(dateOfBirth.split("-")[0].trim()), Integer.parseInt(dateOfBirth.split("-")[1].trim()), Integer.parseInt(dateOfBirth.split("-")[2].trim())), cpf), numberOfAccount, BankSystem.agency);
                        Manager.showMessage(String.format("<<<<< Conta Corrente criada com sucesso >>>>>\n Numero da conta: %d", numberOfAccount));
                        FileHandler.appendTo(this.path, newAccount.toString());
                    }/*
                    case 2 -> {
                        ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu nome");
                        cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira seu CPF");
                        dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Poupança >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                        newAccount = new SavingsAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), BankSystem.INDEX++, BankSystem.MAIN_AGENCY);
                        BankSystem.accounts.put(accountNumber, newAccount);
                        BankSystem.showMessage(String.format("<<<<< Conta Poupança criada com sucesso >>>>>\nNumero da conta: %d\nRendimento: %.2f%%", accountNumber, SavingsAccount.getYieldPercentage()));
                    }
                    case 3 -> {
                        double valueEspecialCheck;
                        ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira seu nome");
                        cpf = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira seu CPF");
                        dateOfBirth = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira sua data de nascimento (formato dd/mm/aaaa)");
                        valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova Conta Especial (Pessoa Fisica) >>>>>\nInsira o valor do cheque especial");
                        newAccount = new EspecialAccount(new PhysicalPerson(ownerName, cpf, dateOfBirth), BankSystem.INDEX++, BankSystem.MAIN_AGENCY, valueEspecialCheck);
                        BankSystem.accounts.put(accountNumber, newAccount);
                        BankSystem.showMessage(String.format("<<<<< Conta Especial/Pessoa Fisica criada com sucesso >>>>>\nNumero da conta: %d\nCheque especial: R$ %.2f", accountNumber, valueEspecialCheck));
                    }
                    case 4 -> {
                        String cnpj, dateOfCreation;
                        ownerName = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o nome");
                        cnpj = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o CNPJ");
                        dateOfCreation = BankSystem.inputDialog("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira a data de criaçao da empresa/instituiçao (formato dd/mm/aaaa)");
                        valueEspecialCheck = BankSystem.inputDialogForFloatNumber("<<<<< Criar Nova Conta Especial (Pessoa Juridica) >>>>>\nInsira o valor do cheque especial");
                        newAccount = new BusinessAccount(new LegalPerson(ownerName, cnpj, dateOfCreation), accountNumber, BankSystem.MAIN_AGENCY, valueEspecialCheck);
                        BankSystem.accounts.put(accountNumber, newAccount);
                        BankSystem.showMessage(String.format("<<<<< Conta Especial/Pessoa Juridica criada com sucesso >>>>>\nNumero da conta: %d\nCheque especial: R$ %.2f", accountNumber, valueEspecialCheck));
                    }*/
                    case 5 -> {
                        return;
                    }
                    default -> Manager.showMessage("<<<<< Escolha uma opçao valida >>>>>");
                }
            } catch (NumberFormatException | FileNotFoundException e) {
                Manager.showMessage("<<<< Opção Inválida >>>>>");
            }
        }
    }

    private void informationOfAnAccount() {
        try {
            if (FileHandler.fileIsEmpty(this.path)) {
                Manager.showMessage("<<<<< Nenhuma conta foi cadastrada >>>>>");
                return;
            }
            int numberOfAccount = Manager.inputDialogForIntegerNumber("<<<<< Informacoes sobre conta >>>>>\nInsira"
                    + " o numero da conta");
            Account requiredAccount = this.getAccountThroughItsNumber(numberOfAccount);
            Manager.showMessage("<<<<< Informacoes sobre conta >>>>>\n" + requiredAccount.getStandardized());
        } catch (FileNotFoundException e) {
            Manager.showMessage("<<<<< Impossivel acessar o arquivo de contas >>>>>");
        } catch (AccountNotFoundException exception) {
            Manager.showMessage(exception.getMessage());
        } catch (NumberFormatException exception) {
            Manager.showMessage("<<<<< Entrada inválida >>>>>");
        } catch(UnparseableStringAccount exception) {
            Manager.showMessage("<<<<<< Um erro ocorreu ao procurar pela conta >>>>>");
        }
    }

    /*private static void movementAccount() {
        if (BankSystem.numbersOfAccounts.isEmpty()) {
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
                    + "%s%nInsira a quantia a sacar", requiredAccount.getStandardized()));
            requiredAccount.withdraw(amountToGet); //This line can throw an InsufficientFundsException or an IllegalArgumentException!
            BankSystem.showMessage(String.format("<<<<< Sacar quantia de uma conta >>>>>%nSaque efetuado com sucesso!%n" + "Saldo atual da conta: R$%.2f", requiredAccount.getCurrentBalance())); //If everything went as expected, we'll get here!
        } catch (AccountNotFoundException | InsufficientFundsException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }

    */
    /*private static void depositMoneyInAnAccount() {
        int numberOfAccount = BankSystem.inputDialogForIntegerNumber("<<<<< Depositar quantia em uma conta >>>>>\nInsira o numero da conta");
        try {
            Account requiredAccount = BankSystem.getAccountThroughItsNumber(numberOfAccount); //This line can throw an AccountNotFoundException.
            double amountToDeposit = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Depositar quantia em uma conta >>>>>%n%s%nInsira a quantia a depositar", requiredAccount.getStandardized()));
            requiredAccount.deposit(amountToDeposit); //This line can throw an IllegalArgumentException
            BankSystem.showMessage(String.format("<<<<< Depositar quantia em uma conta >>>>>%nDeposito efetuado com sucesso!%nSaldo atual da conta: R$%.2f", requiredAccount.getCurrentBalance()));
        } catch (AccountNotFoundException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }

    private static void transferMoneyBetweenAccounts() {
        if (BankSystem.numbersOfAccounts.size() == 1) {
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
            double amountToTransfer = BankSystem.inputDialogForFloatNumber(String.format("<<<<< Transferir de uma conta para outra >>>>>%n%n> Conta depositante <%n%s%n> Conta receptora <%n%s%nInsira a quantia a transferir", accountToTransferWith.getStandardized(),accountToReceiveAmount.getStandardized()));
            accountToTransferWith.transfer(accountToReceiveAmount, amountToTransfer);
            BankSystem.showMessage(String.format("<<<<< Transferencia realizada com sucesso! >>>>>%nConta depositante: saldo atual (R$%.2f)%nConta receptora: saldo atual (R$%.2f)", accountToTransferWith.getCurrentBalance(), accountToReceiveAmount.getCurrentBalance()));
        }catch(AccountNotFoundException | InsufficientFundsException | IllegalArgumentException exception) {
            BankSystem.showMessage(exception.getMessage());
        }
    }*/

    public String getAccountsFilePath(){
        return this.path;
    }
}