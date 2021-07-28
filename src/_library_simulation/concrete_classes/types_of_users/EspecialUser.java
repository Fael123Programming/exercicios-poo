package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.lending_of_publication.Lending;
import java.util.List;
import java.util.ArrayList;

public class EspecialUser extends User{
    private List<Lending> lendings;

    public EspecialUser(String name,String phone,String email,String cpf,Lending lending) {
        super(name, phone, email, cpf);
        this.lendings=new ArrayList<>();
        this.lendings.add(lending);
    }
    
    
    public EspecialUser(String name,String phone,String email,String cpf) {
        super(name,phone,email,cpf);
        this.lendings=new ArrayList<>();
    }
    
    @Override
    public boolean addNewLending(Lending lending){
        if(lending==null) return false;
        this.lendings.add(lending);
        return true;
    }
    
    @Override
    public boolean renewLending(String titleOfPublication){
        if(this.lendings.isEmpty()) return false;
        for(int counter=0;counter<this.lendings.size();counter++){
            if(this.lendings.get(counter).getPublication().getTitle().equals(titleOfPublication)){
                this.lendings.get(counter).setRenews(this.lendings.get(counter).getRenews()+1);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public double calculateFine(Lending lending){
        if(lending==null) return 0;
        if(lending.getRenews()<=5) return 0;
        return (lending.getRenews()-5)*lending.getPublication().getFineValue();
    }
}
