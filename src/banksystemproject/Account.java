package banksystemproject;
 
public class Account implements Comparable<Account>{
    private static int numberOfAccounts=0;
    private int accountNumber;
    private double currentAmount,limitAmount;
    private String agency,ownerName,ownerIdentification;//In our case, CPF or CNPJ
    
    public Account(){//Empty constructor function
        Account.numberOfAccounts++;
    }
    
    public Account(String ownerName,int accountNumber){
        this.ownerName=ownerName;
        this.accountNumber=accountNumber;
        Account.numberOfAccounts++;
    }
    public Account(String ownerName,String ownerIdentification,int accountNumber,String agency){
        this(ownerName,accountNumber);//It calls the constructor function above
        this.ownerIdentification=ownerIdentification;
        this.agency=agency;
        this.currentAmount=0;
        this.limitAmount=300000000;//300 millions is the limit
        Account.numberOfAccounts++;
    }
    
    public boolean withDraw(double quant){//to get money
        if(this.currentAmount<quant||quant<=0) return false; 
        this.currentAmount -= quant;
        return true;
    }
    
    public boolean deposit(double quant){
        if(this.currentAmount+quant>this.limitAmount||quant<=0) return false;
        this.currentAmount += quant;
        return true;
    }
    
    public boolean transfer(Account targetAccount,double quant){
        if(this.currentAmount<quant) return false;
        if(targetAccount.deposit(quant)){
            this.withDraw(quant);
            return true;
        }
        return false;
    }
    
    public static int getNumberOfAccounts(){return Account.numberOfAccounts;}
    
    public int getAccountNumber(){return this.accountNumber;}
    
    public double getCurrentAmount(){return this.currentAmount;}
    
    public double getLimitAmount(){return this.limitAmount;}
    
    public String getAgency(){return this.agency;}
    
    public String getOwnerName(){return this.ownerName;}
    
    public String getOwnerIdentification(){return this.ownerIdentification;}
    
    @Override
    public String toString(){
        return String.format("<<< Nome do titular: %s  Numero da conta: %d"
                + "     Identificacao legal do titular: %s     Saldo atual: %.2f"
                + "     Agencia: %s >>>",this.ownerName,this.accountNumber,
                this.ownerIdentification,this.currentAmount,this.agency);
    }
    
    @Override
    public int compareTo(Account accountToCompareWith){//It sorts from less account's number to greater ones
        if(this.accountNumber>accountToCompareWith.getAccountNumber()) return 1;
        else if(this.accountNumber<accountToCompareWith.getAccountNumber()) return -1;
        return 0;
    }
}
