package _library_simulation.concrete_classes.author_of_publication;

public class Author {
    private String name, titration;

    public Author(String name, String titration) {
        this.name = name;
        this.titration = titration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getTitration() {
        return this.titration;
    }

    public void setTitration(String newTitration) {
        this.titration = newTitration;
    }
}