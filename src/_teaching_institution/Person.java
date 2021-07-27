package _teaching_institution;

public class Person {
    private String name;
    private String cpf;
    private String phoneNumber;
    private String address;
    
    public Person(String name,String cpf,String phoneNumber,String address){
        this.name=name;
        this.cpf=cpf;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
