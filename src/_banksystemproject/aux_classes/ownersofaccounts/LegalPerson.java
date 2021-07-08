package _banksystemproject.aux_classes.ownersofaccounts;

import java.util.Date;

public class LegalPerson extends Customer{
    private String cnpj;
    private String dateOfCreation;//In format dd/mm/aaaa.
    
    public LegalPerson(String name,String cnpj){
        super(name);
        this.cnpj=cnpj;
    }
    
    public LegalPerson(String name,String cnpj,String dateOfCreation){
        this(name,cnpj);
        this.dateOfCreation=dateOfCreation;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDateOfCreation() {
        return this.dateOfCreation;
    }
    
    public int getAge(){
        if(this.dateOfCreation==null) return -1;
        Date dt=new Date();
        return Integer.parseInt(dt.toString().split(" ")[5])-
                Integer.parseInt(this.dateOfCreation.split("/")[2]);
        //That means: current year minus year company was created.
    }
}
