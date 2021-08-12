package _library_simulation.abstract_classes.user;

public abstract class User {
    //-> CommonUser can have at maximum, 3 lendings;
    //-> EspecialUser doesn't have a limit on it.
    private String name, phone, email, cpf;
    private int totalOfLoans, limitOfLoans, limitOfRenovations;

    public User(String name, String phone, String email, String cpf, int limitOfLoans, int limitOfRenovations) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.cpf = cpf;
        this.limitOfLoans = limitOfLoans;
        this.limitOfRenovations = limitOfRenovations;
        this.totalOfLoans = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String newCpf) {
        this.cpf = newCpf;
    }

    public int getLimitOfLoans() {
        return this.limitOfLoans;
    }

    public int getLimitOfRenovations(){return this.limitOfRenovations;}

    public int getTotalOfLoans() {
        return this.totalOfLoans;
    }

    public boolean setTotalOfLoans(int newQuantity){
        if(newQuantity < 0) return false;
        this.totalOfLoans = newQuantity;
        return true;
    }

    public boolean canBorrow() {
        return this.totalOfLoans < this.limitOfLoans || this.limitOfLoans == -1;
        //limitOfLendings == -1 is used for especial users who can borrow unlimitedly
    }
}