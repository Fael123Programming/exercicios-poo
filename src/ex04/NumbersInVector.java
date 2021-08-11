package ex04;

import java.util.Scanner;

public class NumbersInVector {
    private int normalVector[] = new int[10];
    private int invertedVector[] = new int[10];

    public NumbersInVector() {
    }

    public void fillUpNormalVector() {
        Scanner input = new Scanner(System.in);
        for (int num = 0; num < 10; num++) {
            System.out.printf("Posicao (%d):", num);
            normalVector[num] = input.nextInt();
        }
    }

    public void fillUpInvertedVector() {
        int counter = 9;
        for (int num = 0; num < 10; num++) {
            this.invertedVector[num] = this.normalVector[counter];
            counter--;
        }
    }

    public void showNormalVector() {
        System.out.println("-- Vector normal --");
        for (int num : this.normalVector) System.out.println(num);
    }

    public void showInvertedVector() {
        System.out.println("-- Vector invertido --");
        for (int num : this.invertedVector) System.out.println(num);
    }
}

