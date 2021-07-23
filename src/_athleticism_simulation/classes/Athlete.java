package _athleticism_simulation.classes;
public abstract class Athlete extends Person{
    public Athlete(String name,String identification,String nationality,int age){
       super(name,identification,nationality,age);
    }
    
    public abstract String modality();
}
