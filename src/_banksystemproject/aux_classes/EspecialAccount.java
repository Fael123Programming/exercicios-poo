package _banksystemproject.aux_classes;
public class EspecialAccount extends Account{
    private double valueEspecialCheck;
    
    public EspecialAccount(Customer owner,int accountNumber){
        //The owner will be an instance of PhysicalPerson (which is a customer by definition).
        super(owner,accountNumber);
        this.valueEspecialCheck=0.0;
    }
    
    public EspecialAccount(Customer owner,int accountNumber,String agency,
            double valueEspecialCheck){
        super(owner,accountNumber,agency);
        this.valueEspecialCheck=valueEspecialCheck;
    }
    
    public double getValueEspecialCheck(){
        return this.valueEspecialCheck;
    }
    
    public void setValueEspecialCheck(double newValueEspecialCheck){
        this.valueEspecialCheck=newValueEspecialCheck;
    }
    
    @Override
    public boolean withdraw(double quant){
        if(quant<=0||quant>this.valueEspecialCheck+this.getCurrentAmount()) return false;
        this.setCurrentAmount(this.getCurrentAmount()-quant);
        return true;
    }
    
    @Override
    public boolean transfer(Account target,double quant){
        if(quant<=0||target==null||quant>this.valueEspecialCheck+this.getCurrentAmount()) return false;
        target.deposit(quant);//As amount was already checked,this method will always succeed.
        this.withdraw(quant);
        return true;
    }
}
