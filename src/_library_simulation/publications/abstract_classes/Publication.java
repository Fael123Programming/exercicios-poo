package _library_simulation.publications.abstract_classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Publication {
    private String dateOfPublication,title;
    private List<Publication> relatedWorks;
    
    public Publication(String dateOfPublication,String title,Publication relatedWork){
        //At least one related publication and one author shall be passed through this constructor function.
        this.dateOfPublication=dateOfPublication;
        this.title=title;
        this.relatedWorks=new ArrayList<>();
        this.relatedWorks.add(relatedWork);
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

    public void addRelatedWord(Publication newRelatedWord){
        if(newRelatedWord==null) return;
        this.relatedWorks.add(newRelatedWord);
    }
}
