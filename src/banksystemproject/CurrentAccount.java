package banksystemproject;
 
public class CurrentAccount {
    public int number;
    public double totalAmount;
    public double limitAmount = 300000000;//300 Millions
    public String agency;
    public String ownersName;
    public String ownersIdentification;//In our case, CPF or CNPJ
    
    public CurrentAccount(){}
    
    public boolean withDraw(double quant){//to get money
        if(this.totalAmount<quant||quant<=0) return false; 
        this.totalAmount -= quant;
        return true;
    }
    
    public boolean deposit(double quant){
        if(this.totalAmount+quant>this.limitAmount||quant<=0) return false;
        this.totalAmount += quant;
        return true;
    }
    
    public boolean transfer(CurrentAccount targetAccount,double quant){
        boolean verify;
        if(this.totalAmount<quant) return false;
        verify = targetAccount.deposit(quant);
        if(verify) {
            this.withDraw(quant);
            return true;
        }
        return false;
    }
}
