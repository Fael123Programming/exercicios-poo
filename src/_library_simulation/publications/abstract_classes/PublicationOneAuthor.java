package _library_simulation.publications.abstract_classes;

import _library_simulation.publications.abstract_classes.Publication;
import _library_simulation.authorofpublication.Author;

public abstract class PublicationOneAuthor extends Publication{
    private Author author;
    
    public PublicationOneAuthor(String dateOfPublication,String title,Publication relatedWork,Author author) {
        super(dateOfPublication, title, relatedWork);
        this.author=author;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author newAuthor) {
        this.author = newAuthor;
    }
}
