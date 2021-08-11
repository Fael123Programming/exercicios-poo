package _banksystemproject.aux_classes.ownersofaccounts;

import java.util.Date;

public class PhysicalPerson extends Customer {
    private String cpf;
    private String dateOfBirth;//In format dd/mm/aaaa

    public PhysicalPerson(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    public PhysicalPerson(String name, String cpf, String dateOfBirth) {
        this(name, cpf);
        this.dateOfBirth = dateOfBirth;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int getAge() {
        if (this.dateOfBirth == null) return -1;
        Date dt = new Date();
        return Integer.parseInt(dt.toString().split(" ")[5]) -
                Integer.parseInt(this.dateOfBirth.split("/")[2]);
        //That means: current year minus year person was born.
    }
}
