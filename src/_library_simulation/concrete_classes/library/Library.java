package _library_simulation.concrete_classes.library;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.publication.loan.Loan;

import java.util.ArrayList;

public class Library {
    private String name, address;
    private ArrayList<Publication> publications;
    private ArrayList<User> users;
    private ArrayList<Loan> loans;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.publications = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public ArrayList<Publication> getPublications() { return this.publications;}

    public ArrayList<User> getUsers() {return this.users;}

    public ArrayList<Loan> getLoans(){ return this.loans;}
}