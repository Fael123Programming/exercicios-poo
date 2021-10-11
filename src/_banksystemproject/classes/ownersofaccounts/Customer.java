package _banksystemproject.classes.ownersofaccounts;

import java.time.LocalDate;

public abstract class Customer {
    private String name;
    private LocalDate dateOfBirth;

    public Customer(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public LocalDate getDateOfBirth(){ return this.dateOfBirth; }

    public void setDateOfBirth(LocalDate newDate) { this.dateOfBirth = newDate; }

    public int getAge(){
        if (this.dateOfBirth == null) return -1;
        int age = LocalDate.now().getYear() - this.dateOfBirth.getYear();
        if (LocalDate.now().isBefore(LocalDate.of(LocalDate.now().getYear(), this.dateOfBirth.getMonth(), this.dateOfBirth.getDayOfMonth()))) {
            return --age;
        }
        return age;
    }

    @Override
    public String toString(){
        return String.format("{nome: %s, data de nascimento: %s", this.name, this.dateOfBirth.toString());
    }
}
