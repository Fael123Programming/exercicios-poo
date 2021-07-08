package _banksystemproject.aux_classes.accounts;

import _banksystemproject.aux_classes.ownersofaccounts.PhysicalPerson;

public class SavingsAccount extends Account{
    private static double yieldPercentage=0.005;
            
    public SavingsAccount(PhysicalPerson owner,int accountNumber){
        super(owner,accountNumber);
    }
    
    public SavingsAccount(PhysicalPerson owner,int accountNumber,String agency){
        super(owner,accountNumber,agency);
    }
    
    public boolean makeMoney(){
        if(this.getCurrentAmount()==0) return false;
        this.deposit(this.getCurrentAmount()*SavingsAccount.yieldPercentage);
        return true;
    }
    
    public static double getYieldPercentage(){
        return SavingsAccount.yieldPercentage*100;
    }
    
    public static void setYieldPercentage(double newPercentage){
        SavingsAccount.yieldPercentage=newPercentage/100;
    }
    
    @Override
    public boolean withdraw(double quant){
        if(quant<=0||this.getCurrentAmount()<quant) return false; 
        this.setCurrentAmount(this.getCurrentAmount()-quant);
        return true;
    }
    
    @Override
    public boolean transfer(Account target,double quant){
        if(quant<=0||this.getCurrentAmount()<quant) return false;
        target.deposit(quant);
        this.withdraw(quant);
        return true;
    }
}
