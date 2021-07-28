package _library_simulation.concrete_classes.lending_of_publication;

import _library_simulation.abstract_classes.publication.Publication;

public class Lending {
    private Publication publication;
    private String lendingDate,deliveryDate;
    private int renews;

    public Lending(Publication publication,String lendingDate,String deliveryDate) {
        if(!publication.isAvailable()) return;
        this.publication = publication;
        this.lendingDate = lendingDate;
        this.deliveryDate = deliveryDate;
        this.publication.setAvailable(false);
        
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getLendingDate() {
        return this.lendingDate;
    }

    public void setLendingDate(String lendingDate) {
        this.lendingDate = lendingDate;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    public int getRenews(){
        return this.renews;
    }
    
    public void setRenews(int newRenewsQuant){
        this.renews=newRenewsQuant;
    }
    
    
}
