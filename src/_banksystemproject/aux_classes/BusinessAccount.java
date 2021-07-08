package _banksystemproject.aux_classes;
public class BusinessAccount extends EspecialAccount {
    
    public BusinessAccount(LegalPerson owner,int accountNumber){
        super(owner,accountNumber);
    }
    
    public BusinessAccount(LegalPerson owner,int accountNumber,String agency,
            double especialCheck){
        super(owner,accountNumber,agency,especialCheck);
    }
}
