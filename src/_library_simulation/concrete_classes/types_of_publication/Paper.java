package _library_simulation.concrete_classes.types_of_publication;

import _library_simulation.abstract_classes.publication.PublicationManyAuthors;
import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.author_of_publication.Author;

public class Paper extends PublicationManyAuthors{
    private String _abstract;
    
    public Paper(String dateOfPublication,String title,Publication relatedWork,double fineValue,Author author,String _abstract){
        super(dateOfPublication,title,relatedWork,fineValue,author);
        this._abstract=_abstract;
    }

    public String getAbtract() {
        return this._abstract;
    }

    public void setAbtract(String newAbstract) {
        this._abstract = newAbstract;
    }
    
    
}
