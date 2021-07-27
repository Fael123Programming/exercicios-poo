package _library_simulation.publications.types;

import _library_simulation.publications.abstract_classes.PublicationManyAuthors;
import _library_simulation.publications.abstract_classes.Publication;
import _library_simulation.authorofpublication.Author;

public class Paper extends PublicationManyAuthors{
    private String _abstract;
    
    public Paper(String dateOfPublication,String title,Publication relatedWork,Author author,String _abstract){
        super(dateOfPublication,title,relatedWork,author);
        this._abstract=_abstract;
    }

    public String getAbtract() {
        return this._abstract;
    }

    public void setAbtract(String newAbstract) {
        this._abstract = newAbstract;
    }
    
    
}
