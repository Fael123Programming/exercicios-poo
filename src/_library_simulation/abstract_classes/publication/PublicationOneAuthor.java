package _library_simulation.abstract_classes.publication;

import _library_simulation.concrete_classes.author_of_publication.Author;

public abstract class PublicationOneAuthor extends Publication{
    private Author author;
    
    public PublicationOneAuthor(String dateOfPublication,String title,Publication relatedWork,
            double fineValue,Author author) {
        super(dateOfPublication,title,relatedWork,fineValue);
        this.author=author;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author newAuthor) {
        this.author = newAuthor;
    }
}