package _banksystemproject.classes.ownersofaccounts;

import java.time.LocalDate;

public class LegalPerson extends Customer {
    private String CNPJ;

    public LegalPerson(String name, LocalDate dateOfCreation, String CNPJ) {
        super(name, dateOfCreation);
        this.CNPJ = CNPJ;
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString(){
        return super.toString() + ", CNPJ: " + this.CNPJ + "}";
    }
}
