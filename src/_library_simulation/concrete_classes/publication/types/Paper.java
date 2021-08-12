package _library_simulation.concrete_classes.publication.types;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.concrete_classes.publication.author.Author;

public class Paper extends Publication {
    private String _abstract;

    public Paper(String dateOfPublication, String title, Publication relatedWork, Author author, double fineValue, String _abstract) {
        super(dateOfPublication, title, relatedWork, author, fineValue);
        this._abstract = _abstract;
    }

    public String getAbtract() {
        return this._abstract;
    }

    public void setAbtract(String newAbstract) {
        this._abstract = newAbstract;
    }
}