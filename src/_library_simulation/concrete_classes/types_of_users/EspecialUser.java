package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;

public class EspecialUser extends User {
    private final static int MAXIMUM = -1;

    public EspecialUser(String name, String phone, String email, String cpf) {
        super(name, phone, email, cpf, EspecialUser.MAXIMUM);
    }
}