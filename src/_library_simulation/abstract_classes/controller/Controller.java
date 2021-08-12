package _library_simulation.abstract_classes.controller;

import _library_simulation.concrete_classes.library.Library;

public abstract class Controller {
    private Library library;

    public Controller(Library library){
        this.library = library;
    }

    public Library getLibrary(){ return this.library;}

    public boolean setLibrary(Library newLibrary){
        if(newLibrary == null) return false;
        this.library = newLibrary;
        return true;
    }
}
