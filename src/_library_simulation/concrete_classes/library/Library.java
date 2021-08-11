package _library_simulation.concrete_classes.library;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.abstract_classes.user.User;

import java.util.ArrayList;

public class Library {
    private String name, address;
    private ArrayList<Publication> publications;
    private ArrayList<User> users;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.publications = new ArrayList<>();
        this.users = new ArrayList<>();
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

    public ArrayList getPublications() {
        if (this.publications.isEmpty()) return null;
        return this.publications;
    }

    public boolean addPublication(Publication pub) {
        if (pub == null) return false;
        this.publications.add(pub);
        return true;
    }

    public Publication removePublicationByTitle(String title) {
        if (this.publications.isEmpty()) return null;
        for (Publication toRemove : this.publications) {
            if (toRemove.getTitle().equals(title)) {
                this.publications.remove(toRemove);
                return toRemove;
            }
        }
        return null;
    }

    public Publication getPublicationByTitle(String title) {
        if (this.publications.isEmpty()) return null;
        for (Publication wanted : this.publications) if (wanted.getTitle().equals(title)) return wanted;
        return null;
    }

    public boolean addNewUser(User newUser) {
        if (newUser == null) return false;
        this.users.add(newUser);
        return true;
    }

    public User removeUserByName(String name) {
        if (this.users.isEmpty()) return null;
        for (User wanted : this.users) {
            if (wanted.getName().equals(name)) {
                this.users.remove(wanted);
                return wanted;
            }
        }
        return null;
    }

    public User getUserByName(String name) {
        if (this.users.isEmpty()) return null;
        for (User wanted : this.users) if (wanted.getName().equals(name)) return wanted;
        return null;
    }

    public ArrayList getUsers() {
        if (this.users.isEmpty()) return null;
        return this.users;
    }
}