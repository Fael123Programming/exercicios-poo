package _banksystemproject.aux_classes.accounts;
import _banksystemproject.aux_classes.ownersofaccounts.Customer;
import _banksystemproject.aux_classes.ownersofaccounts.PhysicalPerson;

public class CurrentAccount extends Account{
    public CurrentAccount(PhysicalPerson owner,int accountNumber){
        super(owner,accountNumber);
    }
    
    public CurrentAccount(PhysicalPerson owner,int accountNumber,String agency){
        super(owner,accountNumber,agency,0.0);//0.0 is the value of the especial check.
    }
    
    @Override
    public PhysicalPerson getOwner(){return (PhysicalPerson) super.getOwner();}
    
    @Override
    public void setOwner(Customer newOwner){
        if(newOwner.getClass().equals(PhysicalPerson.class)){
            super.setOwner(newOwner);
        }else throw new IllegalArgumentException("## Invalid Argument ##");
    }
}
