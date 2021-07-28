package _library_simulation.abstract_classes.publication;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.author_of_publication.Author;
import java.util.ArrayList;
import java.util.List;

public abstract class PublicationManyAuthors extends Publication{
    private List<Author> authors;
    
    public PublicationManyAuthors(String dateOfPublication,String title,
            Publication relatedWork,double fineValue,Author author){
        //At least one related publication and one author shall be passed through this constructor function.
        super(dateOfPublication,title,relatedWork,fineValue);
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
