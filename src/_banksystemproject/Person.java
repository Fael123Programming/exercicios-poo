package _banksystemproject;
public class Person {
    private String name;
    private String identification;//In our case, CPF or CNPJ
    
    public Person(String name,String ident){
        this.name=name;
        this.identification=ident;
    }

    public String getName() {return this.name;}

    public String getIdentification() {return this.identification;}
}
