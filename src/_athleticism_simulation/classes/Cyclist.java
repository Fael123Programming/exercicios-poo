package _athleticism_simulation.classes;

import _athleticism_simulation.interfaces.ICyclist;

public class Cyclist extends Athlete implements ICyclist {
    public Cyclist(String name, String identification, String nationality, int age) {
        super(name, identification, nationality, age);
    }

    @Override
    public void warmUp() {//This athlete warms up this own way.
        System.out.println(super.getName() + " (" + this.modality() + ") is warming up...");
    }

    @Override
    public void pedal() {
        System.out.println(super.getName() + " is pedaling...");
    }

    @Override
    public String modality() {
        return "Cyclist";
    }
}
