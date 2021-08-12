package _library_simulation.types_of_controllers;

import _library_simulation.abstract_classes.controller.Controller;
import _library_simulation.abstract_classes.user.User;
import _library_simulation.concrete_classes.library.Library;

import java.util.ArrayList;

public class UserController extends Controller {
    public UserController(Library library){
        super(library);
    }

    public boolean addUser(User newUser){
        if(newUser == null) return false;
        super.getLibrary().getUsers().add(newUser);
        return true;
    }

    public boolean removeUser(User userToRemove){
        if(userToRemove == null || super.getLibrary().getUsers().isEmpty()) return false;
        return super.getLibrary().getUsers().remove(userToRemove);
    }

    public ArrayList<User> getUsers(){ return super.getLibrary().getUsers();}

    public User getUser(int position){ return this.getUsers().get(position);}
}
