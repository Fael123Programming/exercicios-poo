package _library_simulation.publications.abstract_classes;

import _library_simulation.publications.abstract_classes.Publication;
import _library_simulation.authorofpublication.Author;
import java.util.ArrayList;
import java.util.List;

public abstract class PublicationManyAuthors extends Publication{
    private List<Author> authors;
    
    public PublicationManyAuthors(String dateOfPublication,String title,Publication relatedWork,Author author){
        //At least one related publication and one author shall be passed through this constructor function.
        super(dateOfPublication,title,relatedWork);
        this.authors=new ArrayList<>();
        this.authors.add(author);
    }
    
    public Author[] getAuthors() {
        if(this.authors.isEmpty()) return null;
        return (Author[]) this.authors.toArray();
    }
    
    public void addNewAuthor(Author newAuthor){
        this.authors.add(newAuthor);
    }
}
