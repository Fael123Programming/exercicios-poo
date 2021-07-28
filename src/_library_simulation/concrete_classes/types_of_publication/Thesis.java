package _library_simulation.concrete_classes.types_of_publication;

import _library_simulation.abstract_classes.publication.PublicationOneAuthor;
import _library_simulation.abstract_classes.publication.Publication;
import _teaching_institution.Institution;
import _library_simulation.concrete_classes.author_of_publication.Author;

public class Thesis extends PublicationOneAuthor{
    private int numberOfPages;
    private String resume,dateOfDefense;
    private Institution institution;
    
    public Thesis(String dateOfPublication,String title,Publication relatedWork,double fineValue,Author author,
            int numberOfPages,String resume,String dateOfDefense,Institution institution) {
        super(dateOfPublication,title,relatedWork,fineValue,author);
        this.numberOfPages=numberOfPages;
        this.resume=resume;
        this.dateOfDefense=dateOfDefense;
        this.institution=institution;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int newNumberOfPages) {
        this.numberOfPages = newNumberOfPages;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(String newResume) {
        this.resume = newResume;
    }

    public String getDateOfDefense() {
        return this.dateOfDefense;
    }

    public void setDateOfDefense(String newDateOfDefense) {
        this.dateOfDefense = newDateOfDefense;
    }

    public Institution getInstitution() {
        return this.institution;
    }

    public void setInstitution(Institution newInstitution) {
        this.institution = newInstitution;
    }
}
