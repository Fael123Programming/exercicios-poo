package banksystemproject;

import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BankSystem {
    private static ArrayList<Account> accounts=new ArrayList();
    //Main function
    public static void main(String[] args) {
        while(true){
            switch(menu("<<<<< Sistema de Banco 1.0.0 >>>>>\n1. Criar nova conta\n2. Exibir informacoes de uma conta\n"
                    + "3. Movimentar conta\n4. Tabela de contas criadas\n5. Sair do sistema")){
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
                case 5: BankSystem.exit();
                default:
                    BankSystem.showMessage("<<<<< Escolha uma opcao valida! >>>>>");
            }
        }
    }
    //Auxiliary functions
    public static int menu(String options){return BankSystem.inputDialogForIntegerNumber(options);}
    
    public static void exit(){
        BankSystem.showMessage("<<<<< Sessao finalizada! >>>>>");
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
        if(BankSystem.accounts.isEmpty()){return accountNumber;}
        while(BankSystem.getAccountThroughItsNumber(accountNumber)!=null){
            /*It will repeat whereas the current value of accountNumber already
            exists belonging to an account looking for an unprecedented number.*/
            accountNumber=(int) (firstBound+Math.random()*((secondBound+1)-firstBound));
        }
        return accountNumber;
    }
    
    //Elemental functions: they deal with the objects that compose the system
    public static void createAccount(){
        String ownerName,ownerIdentification,agency;
        int accountNumber;
        ownerName=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nInsira o nome do titular");
        ownerIdentification=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nInsira a identificacao legal do titular (CPF or CNPJ)");
        AccountOwner owner=new AccountOwner(ownerName,ownerIdentification);
        agency=BankSystem.inputDialog("<<<<< Criar nova conta >>>>>\nDigite o codigo da agencia");
        accountNumber=BankSystem.generateAccountNumber(1000,2000);//Between this range: 1000 and 2000
        BankSystem.accounts.add(new Account(owner,accountNumber,agency));
        BankSystem.showMessage("<<<< Conta criada com sucesso! >>>>>\nNumero da conta (auto-gerado): "+accountNumber);
    }
    
    public static void informationsOfAnAccount(){
        if(BankSystem.accounts.isEmpty()){
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada! >>>>>");
            return;
        }
        int numberOfAccount=BankSystem.inputDialogForIntegerNumber("<<<<< Informacoes sobre conta >>>>>\nInsira"
                + " o numero da conta");
        Account requiredAccount=BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if(requiredAccount==null){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        BankSystem.showMessage("<<<<< Informacoes sobre conta >>>>>\n"+requiredAccount);
    }
    
    public static Account getAccountThroughItsNumber(int numberOfAccount){
        for(Account account:BankSystem.accounts){
            if(account.getAccountNumber()==numberOfAccount) return account;
        }
        return null;//In case that the account wasn't found
    }
    
    public static void movimentAccount(){
        if(BankSystem.accounts.isEmpty()){
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
                    BankSystem.depositMoneyInAnAccount();
                    break;
                case 3:
                    BankSystem.transferMoneyBetweenAccounts();
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
        Account requiredAccount=BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if(requiredAccount==null){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        if(requiredAccount.getCurrentAmount()==0){
            BankSystem.showMessage("<<<<< Conta sem saldo! >>>>>");
            return;
        }
        double amountToGet=BankSystem.inputDialogForFloatNumber(String.format("<<<<< Sacar quantia de uma conta >>>>>%n"
                + "%s%nInsira a quantia a sacar",requiredAccount));
        if(requiredAccount.withDraw(amountToGet)){
                    BankSystem.showMessage(String.format("<<<<< Sacar quantia de uma conta >>>>>%nSaque efetuado com sucesso!%n"
                            + "Saldo atual da conta: R$%.2f",requiredAccount.getCurrentAmount()));
                    return;
        }
        BankSystem.showMessage("<<<<< Quantia invalida! >>>>>");
    }
    
    public static void depositMoneyInAnAccount(){
        int numberOfAccount=BankSystem.inputDialogForIntegerNumber("<<<<< Depositar quantia em uma conta >>>>>\n"
                + "Insira o numero da conta");
        Account requiredAccount=BankSystem.getAccountThroughItsNumber(numberOfAccount);
        if(requiredAccount==null){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        double amountToDeposit=BankSystem.inputDialogForFloatNumber(String.format("<<<<< Depositar quantia em uma conta >>>>>%n"
                + "%s%nInsira a quantia a depositar",requiredAccount));
        if(requiredAccount.deposit(amountToDeposit)){
                    BankSystem.showMessage(String.format("<<<<< Depositar quantia em uma conta >>>>>%nDeposito efetuado com sucesso!%n"
                            + "Saldo atual da conta: R$%.2f",requiredAccount.getCurrentAmount()));
                    return;
        }
        BankSystem.showMessage("<<<<< Quantia invalida! >>>>>");
    }

    public static void transferMoneyBetweenAccounts(){
        if(BankSystem.accounts.size()==1){
            BankSystem.showMessage("<<<<< Apenas uma conta foi cadastrada! >>>>>");
            return;
        }
        int numberOfAccountToTransferWith=BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                + " de uma conta para outra >>>>>%nInsira o numero da conta depositante"));
        Account accountToTransferWith=BankSystem.getAccountThroughItsNumber(numberOfAccountToTransferWith);
        if(accountToTransferWith==null){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        int numberOfAccountToReceiveAmount=BankSystem.inputDialogForIntegerNumber(String.format("<<<<< Transferir"
                + " de uma conta para outra >>>>>%nInsira o numero da conta receptora"));
        Account accountToReceiveAmount=BankSystem.getAccountThroughItsNumber(numberOfAccountToReceiveAmount);
        if(accountToReceiveAmount==null){
            BankSystem.showMessage("<<<<< Conta nao encontrada! >>>>>");
            return;
        }
        double amountToTransfer=BankSystem.inputDialogForFloatNumber(String.format("<<<<< Transferir de uma conta para outra >>>>>%n"
        + "%n> Conta depositante <%n%s%n%n> Conta receptora <%n%s%n%nInsira a quantia a transferir",accountToTransferWith,
        accountToReceiveAmount));
        if(amountToTransfer<=0){
            BankSystem.showMessage("<<<<< Quantia invalida! >>>>>");
            return;
        }
        if(BankSystem.inputDialog(String.format("<<<<< Transferir de uma conta para outra >>>>>%n"
        + "%n> Conta depositante <%n%s%n%n> Conta receptora <%n%s%n%nConfirmar operacao? (s/n)",accountToTransferWith,
        accountToReceiveAmount)).toLowerCase().split("")[0].equals("s")){
            if(accountToTransferWith.transfer(accountToReceiveAmount,amountToTransfer)){
                BankSystem.showMessage(String.format("<<<<< Transferencia realizada com sucesso! >>>>>%nConta depositante: "
                        + "saldo atual (R$%.2f)%nConta receptora: saldo atual (R$%.2f)",accountToTransferWith.getCurrentAmount(),
                        accountToReceiveAmount.getCurrentAmount()));
            }else BankSystem.showMessage("<<<<< Impossivel efetuar a transferencia! >>>>>\n<<<<< Verifique os saldos das contas em operacao >>>>>");
        }else BankSystem.showMessage("<<<<< Operacao cancelada com sucesso! >>>>>");
    }
    
    public static void tableOfAccounts(){
        if(BankSystem.accounts.isEmpty()){
            BankSystem.showMessage("<<<<< Nenhuma conta foi cadastrada! >>>>>");
            return;
        }
        Collections.sort(BankSystem.accounts);
        System.out.println("<<<<< Contas cadastradas no sistema ordenadas do maior para o menor saldo >>>>>");
        for(Account account:BankSystem.accounts){
            System.out.println(account+"\n");
        }
        System.out.println("\n");
        BankSystem.showMessage("<<<<< Tabela de contas desenhada no console ou prompt de comando! >>>>>");
    }
}