package _banksystemproject.aux_classes;
public class CurrentAccount extends Account{
    public CurrentAccount(PhysicalPerson owner,int accountNumber){
        super(owner,accountNumber);
    }
    
    public CurrentAccount(PhysicalPerson owner,int accountNumber,String agency){
        super(owner,accountNumber,agency);
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
