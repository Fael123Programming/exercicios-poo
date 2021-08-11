package _library_simulation.concrete_classes.types_of_users;

import _library_simulation.abstract_classes.user.User;

public class CommonUser extends User {
    private final static int MAXIMUM = 3;//Maximum of lendings to be made.

    public CommonUser(String name, String phone, String email, String cpf) {
        super(name, phone, email, cpf, CommonUser.MAXIMUM);
    }
}