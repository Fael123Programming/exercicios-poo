package _library_simulation.abstract_classes.user;

import _library_simulation.concrete_classes.lending_of_publication.lending_main_class.Lending;

public abstract class User {
    private String name,phone,email,cpf;
    
    public User(String name,String phone,String email,String cpf){
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.cpf=cpf;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String newCpf) {
        this.cpf = newCpf;
    }
    
    public abstract boolean addNewLending(Lending lending);
    
    public abstract double endLending(String titleOfPublication);
    
    public abstract boolean renewLending(String titleOfPublication,int daysAfterCurrentDeliveryDateTime);
    
    public abstract double calculateFine(Lending lending);
    
}
