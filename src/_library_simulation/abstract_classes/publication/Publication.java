package _library_simulation.abstract_classes.publication;

import _library_simulation.concrete_classes.publication.author.Author;

import java.util.ArrayList;

public abstract class Publication {
    private String dateOfPublication, title;
    private ArrayList<Publication> relatedWorks;
    private ArrayList<Author> authors;
    private double fineValue;
    private boolean available;

    public Publication(String dateOfPublication, String title, Publication relatedWork, Author author, double fineValue) {
        //At least one related publication shall be passed through this constructor function.
        this.dateOfPublication = dateOfPublication;
        this.title = title;
        this.relatedWorks = new ArrayList<>();
        this.relatedWorks.add(relatedWork);
        this.authors = new ArrayList<>();
        this.authors.add(author);
        this.fineValue = fineValue;
        this.available = true;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getFineValue() {
        return this.fineValue;
    }

    public void setFineValue(double fineValue) {
        this.fineValue = fineValue;
    }

    public String getDateOfPublication() {
        return this.dateOfPublication;
    }

    public void setDateOfPublication(String newDateOfPublication) {
        this.dateOfPublication = newDateOfPublication;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public ArrayList<Author> getAuthors() {
        return this.authors;
    }

    public boolean addAuthor(Author newAuthor) {
        if (newAuthor == null) return false;
        return this.authors.add(newAuthor);
    }

    public Author removeAuthor(Author authorToRemove) {
        if (this.authors.isEmpty()) return null;
        for (Author author : this.authors) {
            if (author.getName().equals(authorToRemove.getName())) {
                this.authors.remove(author);
                return author;
            }
        }
        return null;
    }

    public ArrayList<Publication> getRelatedWorks() {
        return this.relatedWorks;
    }

    public boolean addRelatedWork(Publication newRelatedWork) {
        if (newRelatedWork == null) return false;
        this.relatedWorks.add(newRelatedWork);
        return true;
    }
}