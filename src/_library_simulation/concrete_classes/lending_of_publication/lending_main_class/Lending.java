package _library_simulation.concrete_classes.lending_of_publication.lending_main_class;

import _library_simulation.concrete_classes.lending_of_publication.time_class.StylishDateTime;
import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.lending_of_publication.renovation.Renovation;
import java.util.ArrayList;
import java.util.List;

public class Lending {
    private Publication publication;
    private String lendingDateTime,deliveryDateTime;
    private List<Renovation> renovations;
 
    public Lending(Publication publication,int lendingDay,int deliveryDay) {
        if(!publication.isAvailable() || lendingDay>=deliveryDay) return;
        //You can`t make a new lending with an unavailable material or wrong dates!
        this.publication = publication;
        this.publication.setAvailable(false);
        this.lendingDateTime=StylishDateTime.getDateTimeString(lendingDay);
        this.deliveryDateTime=StylishDateTime.getDateTimeString(deliveryDay);
        //See class StylishDateTime documentations!
        this.renovations=new ArrayList<>();
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getLendingDateTime() {
        return this.lendingDateTime;
    }

    public void setLendingDateTime(String lendingDateTime) {
        this.lendingDateTime = lendingDateTime;
    }

    public String getDeliveryDateTime() {
        return this.deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }
    
    public Renovation[] getRenovations(){
        if(this.renovations.isEmpty()) return null;
        return (Renovation[]) this.renovations.toArray();
    }
    
    public boolean addRenovation(Renovation newRenovation){
        if(newRenovation==null) return false;
        this.renovations.add(newRenovation);
        return true;
    }
}
