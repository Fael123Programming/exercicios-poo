package _library_simulation.concrete_classes.lending_of_publication.renovation;

public class Renovation {
    private String dateTimeString;//In pattern: dd/MM/yyyy HH:mm:ss
    
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