package _library_simulation.concrete_classes.user_types;

import _library_simulation.abstract_classes.user.User;

public class CommonUser extends User {
    private final static int LOANS_MAX = 3, RENOVATIONS_MAX = 3;//Maximum of lendings to be made.

    public CommonUser(String name, String phone, String email, String cpf) {
        super(name, phone, email, cpf, CommonUser.LOANS_MAX, CommonUser.RENOVATIONS_MAX );
    }
}