package _banksystemproject.aux_classes;

public abstract class Account implements Comparable<Account>{
    private static int numberOfAccounts=0;
    private int accountNumber;
    private double currentAmount;
    private String agency,type;
    private Customer owner;
    
    public Account(){//Empty constructor function
        Account.numberOfAccounts++;
    }
    
    public Account(Customer owner,int accountNumber){
        this.owner=owner;
        this.accountNumber=accountNumber;
        this.currentAmount=0.0;
        Account.numberOfAccounts++;
    }
    
    public Account(Customer owner,int accountNumber,String agency){
        this(owner,accountNumber);//It calls the constructor function above
        this.agency=agency;
        this.currentAmount=0.0;
        Account.numberOfAccounts++;
    }
    
    public abstract boolean withdraw(double quant);//to get money
    
    public abstract boolean transfer(Account targetAccount,double quant);

    public boolean deposit(double quant){
        if(quant<=0) return false;
        this.currentAmount += quant;
        return true;
    }

    public static int getNumberOfAccounts(){return Account.numberOfAccounts;}
    
    public int getAccountNumber(){return this.accountNumber;}
    
    public void setAccountNumber(int newNumber){
        this.accountNumber=newNumber;
    }
    
    public Customer getAccountOwner(){return this.owner;}
    
    public void setAccountOwner(Customer newOwner){
        this.owner=newOwner;
    }
    
    public double getCurrentAmount(){return this.currentAmount;}
    
    protected void setCurrentAmount(double newAmount){
        this.currentAmount=newAmount;
    }
    
    public String getAgency(){return this.agency;}
    
    public void setAgency(String newAgency){
        this.agency=newAgency;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String newType){//It'll be used in inheritance classes.
        this.type=newType;
    }
    
    @Override
    public int compareTo(Account accountToCompareWith){//It sorts from greater amounts to less ones
        if(this.currentAmount>accountToCompareWith.getCurrentAmount()) return -1;
        else if(this.currentAmount<accountToCompareWith.getCurrentAmount()) return 1;
        return 0;
    }
   
    @Override
    public String toString(){
        return String.format("<---- Responsável: %s   Saldo atual: R$ %.2f   "
                + "Número: %d   Tipo: %s   Agencia: %s ---->",
                this.owner.getName(),this.currentAmount,
                this.accountNumber,this.type,this.agency);
    }
}
