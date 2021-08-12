package _library_simulation.types_of_controllers;

import _library_simulation.abstract_classes.controller.Controller;
import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.library.Library;

import java.util.ArrayList;

public class PublicationController extends Controller {
    public PublicationController(Library library){
        super(library);
    }

    public boolean addPublication(Publication newPublication){
        if(newPublication == null) return false;
        super.getLibrary().getPublications().add(newPublication);
        return true;
    }

    public boolean removePublication(Publication publicationToRemove){
        if(publicationToRemove == null || super.getLibrary().getPublications().isEmpty()) return false;
        return super.getLibrary().getPublications().remove(publicationToRemove);
    }

    public ArrayList<Publication> getPublications(){ return this.getLibrary().getPublications();}

    public Publication getPublication(int position){ return this.getPublications().get(position);}
}
