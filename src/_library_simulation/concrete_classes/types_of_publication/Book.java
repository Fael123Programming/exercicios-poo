package _library_simulation.concrete_classes.types_of_publication;

import _library_simulation.abstract_classes.publication.PublicationManyAuthors;
import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.author_of_publication.Author;

public class Book extends PublicationManyAuthors{
    private int editionNumber;
    private String publisher,isbn;//International Standard Book Number.
    
    public Book(String dateOfPublication,String title,Publication relatedWork,double fineValue,
            Author author,int editionNumber,String publisher,String isbn){
        super(dateOfPublication,title,relatedWork,fineValue,author);
        this.editionNumber=editionNumber;
        this.publisher=publisher;
        this.isbn=isbn;
    }

    public int getEditionNumber() {
        return this.editionNumber;
    }

    public void setEditionNumber(int newEditionNumber) {
        this.editionNumber = newEditionNumber;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String newPublisher) {
        this.publisher = newPublisher;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String newIsbn) {
        this.isbn = newIsbn;
    }
}