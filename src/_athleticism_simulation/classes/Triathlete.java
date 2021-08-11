package _athleticism_simulation.classes;

import _athleticism_simulation.interfaces.ICyclist;
import _athleticism_simulation.interfaces.IRunner;
import _athleticism_simulation.interfaces.ISwimmer;

public class Triathlete extends Athlete implements IRunner, ISwimmer, ICyclist {
    public Triathlete(String name, String identification, String nationality, int age) {
        super(name, identification, nationality, age);
    }

    @Override
    public void warmUp() {//This athlete warms up this own way.
        System.out.println(super.getName() + " (" + this.modality() + ") is warming up...");
    }

    @Override
    public void run() {
        System.out.println(super.getName() + " is running...");
    }

    @Override
    public void swim() {
        System.out.println(super.getName() + " is swimming...");
    }

    @Override
    public void pedal() {
        System.out.println(super.getName() + " is pedaling...");
    }

    @Override
    public String modality() {
        return "Triathlete";
    }
}
