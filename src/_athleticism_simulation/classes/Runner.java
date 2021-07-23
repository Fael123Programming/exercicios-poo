package _athleticism_simulation.classes;

import _athleticism_simulation.interfaces.IRunner;

public class Runner extends Athlete implements IRunner{
    public Runner(String name,String identification,String nationality,int age){
        super(name,identification,nationality,age);
    }
    
    @Override
    public void warmUp(){//This athlete warms up this own way.
        System.out.println(super.getName()+" is warming up...");
    }
    
    @Override
    public void run(){
        System.out.println(super.getName()+" is running...");
    }
    
    @Override
    public String modality(){
        return "Runner";
    }
}
