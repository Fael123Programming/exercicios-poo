package _banksystemproject.classes.ownersofaccounts;

import java.time.LocalDate;

public class PhysicalPerson extends Customer {
    private String CPF;

    public PhysicalPerson(String name, LocalDate dateOfBirth, String CPF) {
        super(name, dateOfBirth);
        this.CPF = CPF;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString(){
        return super.toString() + ", CPF: " + this.CPF + "}";
    }
}
