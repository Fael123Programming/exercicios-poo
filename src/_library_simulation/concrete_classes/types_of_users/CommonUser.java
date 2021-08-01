package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.lending_of_publication.lending_main_class.Lending;
import _library_simulation.concrete_classes.lending_of_publication.renovation.Renovation;
import _library_simulation.concrete_classes.lending_of_publication.time_class.StylishDateTime;

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
        if(newLending==null) return false;
        if(!newLending.getPublication().isAvailable() || this.lendingsMade==3) return false;
        this.lendings[this.lendingsMade++]=newLending;
        return true;
    }
    
    @Override
    public double endLending(String titleOfPublication){
        if(this.lendingsMade==0) return -1;
        Lending toEnd;
        for(byte counter=0;counter<this.lendingsMade;counter++){
            toEnd=this.lendings[counter];
            if(toEnd.getPublication().getTitle().equals(titleOfPublication)){
                Double fineValue=this.calculateFine(toEnd);
                toEnd.getPublication().setAvailable(true);
                //Hereafter,I'm going to reorder the array that contains the lendings
                for(int toOrderArray=counter;toOrderArray<this.lendingsMade-1;toOrderArray++){
                    this.lendings[toOrderArray]=this.lendings[toOrderArray+1];
                }
                this.lendings[--this.lendingsMade]=null;//That means, last position of array this.lendings
                return fineValue;
            }
        }
        return -1;//In case that wanted lending is not found
    }
    
    @Override
    public boolean renewLending(String titleOfPublication,int daysAfterCurrentDeliveryDateTime){
        if(this.lendingsMade==0) return false;
        Lending lendingToVerify;
        for(int counter=0;counter<this.lendingsMade;counter++){
            lendingToVerify=this.lendings[counter];
            if(lendingToVerify.getPublication().getTitle().equals(titleOfPublication)){
                lendingToVerify.addRenovation(new Renovation(StylishDateTime.
                     addAmountOfDaysInDateTimeStringBasedOnCurrentTime(lendingToVerify.
                             getDeliveryDateTime(),daysAfterCurrentDeliveryDateTime)));//It's just made timestamping of new renovation made
                //See StylishDateTime documentations!
                lendingToVerify.setDeliveryDateTime(lendingToVerify.
                        getRenovations()[lendingToVerify.getRenovations().length-1].
                        getDateTimeString());//Setting a new date that user will have to deliver the publication he/she borrowed
                return true;//Everything went nice!
            }
        }
        return false;
    }
    
    @Override
    public double calculateFine(Lending lending){
        if(lending==null) return 0;
        if(lending.getRenovations()==null) return 0;//No renovations were made.
        if(lending.getRenovations().length<=3) return 0;//Within limit: no additional charge to pay
        return (lending.getRenovations().length-3)*lending.getPublication().getFineValue();
    }
}