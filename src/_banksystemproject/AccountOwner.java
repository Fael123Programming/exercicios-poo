package _banksystemproject;
public class AccountOwner {
    private String name;
    private String identification;//In our case, CPF or CNPJ
    
    public AccountOwner(String name,String ident){
        this.name=name;
        this.identification=ident;
    }

    public String getName() {return this.name;}

    public String getIdentification() {return this.identification;}
}
