package _banksystemproject.aux_classes.accounts;

import _banksystemproject.aux_classes.ownersofaccounts.Customer;

public abstract class EspecialAccountGeneric extends Account{
    public EspecialAccountGeneric(Customer owner,int accountNumber){
        //The owner will be an instance of PhysicalPerson or LegalPerson (which are customers by definition).
        super(owner,accountNumber);
    }
    
    public EspecialAccountGeneric(Customer owner,int accountNumber,String agency,
            double valueEspecialCheck){
        super(owner,accountNumber,agency,valueEspecialCheck);
    }
}
