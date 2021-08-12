package _library_simulation.concrete_classes.user_types;

import _library_simulation.abstract_classes.user.User;

public class EspecialUser extends User {
    private final static int LOANS_MAX = -1, RENOVATIONS_MAX = 5;

    public EspecialUser(String name, String phone, String email, String cpf) {
        super(name, phone, email, cpf, EspecialUser.LOANS_MAX, EspecialUser.RENOVATIONS_MAX);
    }
}