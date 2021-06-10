package banksystemproject;

import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BankSystem {
    private static ArrayList<Account> accounts=new ArrayList();
    private static ArrayList<Integer> numbersOfTheAccounts=new ArrayList();
    public static void main(String[] args) {
        while(true){
            switch(menu("<<<<< Sistema de Banco 1.0.0 >>>>>\n1. Criar nova conta\n2. Exibir informacoes de uma conta\n"
                    + "3. Movimentar conta\n")){
                case 1:
                    BankSystem.createAccount();
                    break;
                case 2:
                    BankSystem.informationsOfAnAccount();
                    break;
                case 3:
                    BankSystem.movimentAccount();//Here withdraw and deposit methods were implemented
                    break;
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida! >>>>>");
            }
        }
    }
    //Auxiliary functions
    public static int menu(String options){return BankSystem.inputDialogForIntegerNumber(options);}
    
    public static void exit(){
        BankSystem.showMessage("Sessao finalizada!");
        System.exit(0);
    }
    
    public static void showMessage(String msg){JOptionPane.showMessageDialog(null,msg);}
    
    public static int inputDialogForIntegerNumber(String msg){return Integer.parseInt(BankSystem.inputDialog(msg));}
    
    public static double inputDialogForFloatNumber(String msg){return Double.parseDouble(BankSystem.inputDialog(msg));}
    
    public static String inputDialog(String msg){return JOptionPane.showInputDialog(msg);}
    
    public static int generateAccountNumber(int firstBound,int secondBound){
        int accountNumber=(int) (firstBound+Math.random()*((secondBound+1)-firstBound));
        /*Generates a number between firstBound and secondBound values
            Ex.: 1000 and 2000*/
        if(BankSystem.numbersOfTheAccounts.size()==0){
            BankSystem.numbersOfTheAccounts.add(accountNumber);
            return accountNumber;
        }
        while(BankSystem.numbersOfTheAccounts.contains(accountNumber)){
            /*It will repeat whereas the current value of accountNumber already
            exists in numbersOfAccounts until we get an unprecedented number.*/
            accountNumber=(int) (firstBound+Math.random()*((secondBound+1)-firstBound));
        }
        return accountNumber;
        
    }
    
    //Main functions: they deal with the objects that compose the system
    public static void createAccount(){
        String ownerName,ownerIdentification,agency;
        int accountNumber;
        ownerName=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nInsira o nome do titular");
        ownerIdentification=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nInsira a identificacao legal do titular (CPF or CNPJ)");
        agency=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nDigite o codigo da agencia");
        accountNumber=BankSystem.generateAccountNumber(1000,2000);//Between this range: 1000 and 2000
        BankSystem.accounts.add(new Account(ownerName,ownerIdentification,accountNumber,agency));
        BankSystem.showMessage("<<<< Conta criada com sucesso! >>>>>\nNumero da conta (auto-gerado): "+accountNumber);
    }
    
    public static void informationsOfAnAccount(){
        if(BankSystem.accounts.size()==0){
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada! >>>>>");
            return;
        }
        int numberOfAnAccount=BankSystem.inputDialogForIntegerNumber("<<<<< Informacoes sobre conta >>>>>\nInsira"
                + " o numero da conta");
        if(!BankSystem.numbersOfTheAccounts.contains(numberOfAnAccount)){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        Account requiredAccount=BankSystem.accounts.get(BankSystem.numbersOfTheAccounts.indexOf(numberOfAnAccount));
        BankSystem.showMessage(String.format("<<<<< Informacoes sobre conta >>>>>\nNumero da conta:"
                + " %d     Nome do titular: %s     Saldo atual: R$ %.2f",numberOfAnAccount,requiredAccount.getOwnerName(),
                requiredAccount.getCurrentAmount()));
    }

    public static void movimentAccount(){
        if(BankSystem.accounts.size()==0){
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada! >>>>>");
            return;
        }
        while(true){
            int option=BankSystem.menu("<<<<< Movimentar conta >>>>>\n1. Sacar quantia\n2. Depositar quantia\n"
                    + "3. Transferir de uma conta para outra\n4. Voltar ao menu principal");
            switch(option){
                case 1:
                    BankSystem.withDrawMoneyOfAnAccount();
                    break;
                case 2:
                    //BankSystem.depositMoneyInAnAccount();
                    break;
                case 3:
                    if(BankSystem.accounts.size()==1){
                        BankSystem.showMessage("<<<<< Apenas uma conta foi cadastrada! >>>>>");
                        return;
                    }
                    //BankSystem.transferMoneyBetweenAccounts();
                    break;
                case 4: return;
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida! >>>>>");
            }
        }
    }
    
    public static void withDrawMoneyOfAnAccount(){
        int numberOfAccount=BankSystem.inputDialogForIntegerNumber("<<<<< Sacar quantia de uma conta >>>>>\n"
                + "Insira o numero da conta");
        if(!BankSystem.numbersOfTheAccounts.contains(numberOfAccount)){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        Account requiredAccount=BankSystem.accounts.get(BankSystem.numbersOfTheAccounts.indexOf(numberOfAccount));
        if(requiredAccount.getCurrentAmount()==0){
            BankSystem.showMessage("<<<<< Conta sem saldo! >>>>>");
            return;
        }
        double amountToGet=BankSystem.inputDialogForFloatNumber(String.format("<<<<< Sacar quantia de uma conta >>>>>\n"
                + "Numero da conta: %d     Saldo disponivel: R$ %.2f\nInsira a quantia a "
                + "sacar",numberOfAccount,requiredAccount.getCurrentAmount()));
        if(requiredAccount.withDraw(amountToGet)){
                    BankSystem.showMessage(String.format("<<<<< Sacar quantia de uma conta >>>>>\nSaque efetuado com sucesso!\n"
                            + "Saldo atual da conta: R$ %.2f",requiredAccount.getCurrentAmount()));
                    return;
        }
        BankSystem.showMessage("<<<<< Saldo insuficiente! >>>>>");
    }
    
}

