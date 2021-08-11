package _athleticism_simulation.classes;

import _athleticism_simulation.interfaces.ISwimmer;

public class Swimmer extends Athlete implements ISwimmer {
    public Swimmer(String name, String identification, String nationality, int age) {
        super(name, identification, nationality, age);
    }

    @Override
    public void warmUp() {//This athlete warms up this own way.
        System.out.println(super.getName() + " (" + this.modality() + ") is warming up...");
    }

    @Override
    public void swim() {
        System.out.println(super.getName() + " is swimming...");
    }

    @Override
    public String modality() {
        return "Swimmer";
    }
}
