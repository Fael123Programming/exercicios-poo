package _library_simulation.abstract_classes.user;

import _library_simulation.concrete_classes.lending_of_publication.lending_main_class.Lending;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import static java.lang.Math.abs;

public abstract class User {
    //-> CommonUser can have at maximum, 3 lendings;
    //-> EspecialUser doesn't have limit on it.
    private String name,phone,email,cpf;
    private ArrayList<Lending> lendings;
    private int limitOfLendings;
    
    public User(String name,String phone,String email,String cpf,int limitOfLendings){
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.cpf=cpf;
        this.limitOfLendings=limitOfLendings;
        this.lendings=new ArrayList<>();
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
    
    public int getLimitOfLendings(){
        return this.limitOfLendings;
    }
    
    public void setLimitOfLendings(int newLimit){
        this.limitOfLendings=newLimit;
    }
    
    public ArrayList getLendings(){
        return this.lendings;
    }
    
    public boolean canBorrow(){
        return this.lendings.size()<this.limitOfLendings || this.limitOfLendings==-1;
    }
    
    public double endLending(String titleOfPublication){
        if(this.lendings.isEmpty()) return 0.0;
        for(Lending toEnd:this.lendings) {
            if(toEnd.getPublication().getTitle().equals(titleOfPublication)){
                toEnd.getPublication().setAvailable(true);
                this.lendings.remove(toEnd);
                return this.calculateFine(toEnd);
            }
        }
        return 0.0;
    }
    
    private double calculateFine(Lending lending){
        if(lending==null) return 0.0;
        if(!lending.isCreated()) return 0.0;
        int days=Period.between(LocalDate.now(),lending.getDeliveryDate()).getDays();
        if(days<0){//There's a debt to pay
            return abs(days)*lending.getPublication().getFineValue();
        }
        return 0.0;
    }
}