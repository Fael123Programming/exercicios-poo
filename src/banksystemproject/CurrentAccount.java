package banksystemproject;
 
public class CurrentAccount {
    private int number;
    private double currentAmount,limitAmount = 300000000;//300 Millions
    private String agency,ownersName,ownersIdentification;//In our case, CPF or CNPJ
    
    public CurrentAccount(){}
    
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
    
    public boolean transfer(CurrentAccount targetAccount,double quant){
        boolean verify;
        if(this.currentAmount<quant) return false;
        verify = targetAccount.deposit(quant);
        if(verify) {
            this.withDraw(quant);
            return true;
        }
        return false;
    }
    
    public int getNumber(){return this.number;}
    
    public double getCurrentAmount(){return this.currentAmount;}
    
    public double getLimitAmount(){return this.limitAmount;}
    
    public String getAgency(){return this.agency;}
    
    public String getOwnersName(){return this.ownersName;}
    
    public String getOwnersIdentification(){return this.ownersIdentification;}
}
