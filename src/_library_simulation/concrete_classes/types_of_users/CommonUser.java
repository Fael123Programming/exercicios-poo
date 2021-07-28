package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.lending_of_publication.Lending;

public class CommonUser extends User{
    private Lending lendings[];
    private int lendingsMade;
    
    public CommonUser(String name,String phone,String email,String cpf,Lending lending) {
        super(name, phone, email, cpf);
        this.lendings=new Lending[3];
        this.lendings[0]=lending;
        this.lendingsMade=1;
    }

    public CommonUser(String name,String phone,String email,String cpf) {
        super(name, phone, email, cpf);
        this.lendings=new Lending[3];
        this.lendingsMade=0;
    }
    
    @Override
    public boolean addNewLending(Lending newLending){
        if(!newLending.getPublication().isAvailable() || this.lendingsMade==3) return false;
        this.lendings[this.lendingsMade]=newLending;
        this.lendingsMade++;
        return true;
    }
    
    @Override
    public boolean renewLending(String titleOfPublication){
        if(this.lendingsMade==0) return false;
        for(int counter=0;counter<this.lendingsMade;counter++){
            if(this.lendings[counter].getPublication().getTitle().equals(titleOfPublication)){
                this.lendings[counter].setRenews(this.lendings[counter].getRenews()+1);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public double calculateFine(Lending lending){
        if(lending==null) return 0;
        if(lending.getRenews()<=3) return 0;
        return (lending.getRenews()-3)*lending.getPublication().getFineValue();
    }
}
