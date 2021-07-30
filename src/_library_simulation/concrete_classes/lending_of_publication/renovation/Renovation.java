package _library_simulation.concrete_classes.lending_of_publication.renovation;

public class Renovation {
    private String dateTimeString;
    
    public Renovation(String dateTimeString){
        this.dateTimeString=dateTimeString;
    }

    public String getDateTimeString() {
        return this.dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }
}
