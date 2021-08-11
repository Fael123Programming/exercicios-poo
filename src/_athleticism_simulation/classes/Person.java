package _athleticism_simulation.classes;

public abstract class Person {
    private String name, identification, nationality;
    private int age;

    public Person(String name, String identification, String nationality, int age) {
        this.name = name;
        this.identification = identification;
        this.nationality = nationality;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
