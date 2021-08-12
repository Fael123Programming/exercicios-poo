package _library_simulation.concrete_classes.publication.types;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.publication.author.Author;

public class Book extends Publication {
    private int editionNumber;
    private String publisher, isbn;//International Standard Book Number.

    public Book(String dateOfPublication, String title, Publication relatedWork,
                Author author, double fineValue, int editionNumber, String publisher, String isbn) {
        super(dateOfPublication, title, relatedWork, author, fineValue);
        this.editionNumber = editionNumber;
        this.publisher = publisher;
        this.isbn = isbn;
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