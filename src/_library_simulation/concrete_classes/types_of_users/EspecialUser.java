package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.lending_of_publication.lending_main_class.Lending;
import _library_simulation.concrete_classes.lending_of_publication.renovation.Renovation;
import _library_simulation.concrete_classes.lending_of_publication.time_class.StylishDateTime;
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
    public boolean addNewLending(Lending newLending){
        if(newLending==null) return false;
        if(!newLending.getPublication().isAvailable()) return false;
        this.lendings.add(newLending);
        return true;
    }
    
    @Override
    public double endLending(String titleOfPublication){
        if(this.lendings.isEmpty()) return -1;
        Lending toEnd;
        for(int counter=0;counter<this.lendings.size();counter++){
            toEnd=this.lendings.get(counter);
            if(toEnd.getPublication().getTitle().equals(titleOfPublication)){
                Double fineValue=this.calculateFine(toEnd);
                toEnd.getPublication().setAvailable(true);
                this.lendings.remove(counter);
                return fineValue;
            }
        }
        return -1;//In case that wanted lending is not found
    }
    
    @Override
    public boolean renewLending(String titleOfPublication,int daysAfterCurrentDeliveryDateTime){
        if(this.lendings.isEmpty()) return false;
        Lending toVerify;
        for(int counter=0;counter<this.lendings.size();counter++){
            toVerify=this.lendings.get(counter);
            if(toVerify.getPublication().getTitle().equals(titleOfPublication)){
                toVerify.addRenovation(new Renovation(StylishDateTime.
                        addAmountOfDaysInDateTimeStringBasedOnCurrentTime(
                                toVerify.getDeliveryDateTime(),daysAfterCurrentDeliveryDateTime)));//It's just made timestamping of new renovation made
                toVerify.setDeliveryDateTime(toVerify.getRenovations()[toVerify.
                        getRenovations().length-1].getDateTimeString());//Setting a new date that user will have to deliver the publication he/she borrowed        
                return true;
            }
        }
        return false;
    }
    
    @Override
    public double calculateFine(Lending lending){
        if(lending==null) return 0;
        if(lending.getRenovations()==null) return 0;//No renovations were made yet
        if(lending.getRenovations().length<=5) return 0;//Within the limit of free-of-charge renovations
        return (lending.getRenovations().length-5)*lending.getPublication().getFineValue();
    }
}