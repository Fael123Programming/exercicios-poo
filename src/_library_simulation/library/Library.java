package _library_simulation.library;

import _library_simulation.publications.abstract_classes.Publication;
import java.util.List;
import java.util.ArrayList;

public class Library {
    private String name,address;
    private List<Publication> publications;
    
    public Library(String name,String address){
        this.name=name;
        this.address=address;
        this.publications= new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public Publication[] getPublications() {
        if(this.publications.isEmpty()) return null;
        return (Publication[]) this.publications.toArray();
    }

    public void addPublication(Publication pub){
        this.publications.add(pub);
    }
    
    public Publication removePublicationByTitle(String title){
        if(this.publications.isEmpty()) return null;
        for(int counter=0;counter<this.publications.size();counter++){
            if(this.publications.get(counter).getTitle().equals(title)){
                Publication toRemove=this.publications.get(counter);
                this.publications.remove(counter);
                return toRemove;
            }
        }
        return null;
    }
}
