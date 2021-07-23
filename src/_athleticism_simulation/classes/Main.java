package _athleticism_simulation.classes;

import _athleticism_simulation.interfaces.IAthlete;

public class Main {
    public static void main(String[] args) {
        Cyclist cyc=new Cyclist("Maycon","1234-123","Brazilian",25);
        Runner runn=new Runner("Joseon","4342-444","German",20);
        Swimmer swin=new Swimmer("Josh","3341-999","French",35);
        Triathlete tri=new Triathlete("Andersen","8763-211","Australian",27);
        Main.prepareAthlete(cyc);
        Main.prepareAthlete(runn);
        Main.prepareAthlete(swin);
        Main.prepareAthlete(tri);
    }
    
    private static void prepareAthlete(IAthlete athlete){
        athlete.warmUp();
    }
}
