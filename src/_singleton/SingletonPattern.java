package _singleton;

public class SingletonPattern {
    private String description;
    private static SingletonPattern instance = new SingletonPattern();//Just an unique object is created.

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance() {
        return SingletonPattern.instance;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
