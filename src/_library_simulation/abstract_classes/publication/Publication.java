package _library_simulation.abstract_classes.publication;

import java.util.ArrayList;
import java.util.List;

public abstract class Publication {
    private String dateOfPublication,title;
    private List<Publication> relatedWorks;
    private double fineValue;
    private boolean available;
    
    public Publication(String dateOfPublication,String title,Publication relatedWork,double fineValue){
        //At least one related publication and one author shall be passed through this constructor function.
        this.dateOfPublication=dateOfPublication;
        this.title=title;
        this.relatedWorks=new ArrayList<>();
        this.relatedWorks.add(relatedWork);
        this.fineValue=fineValue;
        this.available=true;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
    public double getFineValue() {
        return this.fineValue;
    }

    public void setFineValue(double fineValue) {
        this.fineValue = fineValue;
    }

    public String getDateOfPublication() {
        return this.dateOfPublication;
    }

    public void setDateOfPublication(String newDateOfPublication) {
        this.dateOfPublication = newDateOfPublication;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Publication[] getRelatedWorks() {
        if(this.relatedWorks.isEmpty()) return null;
        return (Publication[]) this.relatedWorks.toArray();
    }

    public void addRelatedWork(Publication newRelatedWork){
        if(newRelatedWork==null) return;
        this.relatedWorks.add(newRelatedWork);
    }
}
