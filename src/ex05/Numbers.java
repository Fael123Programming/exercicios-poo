package ex05;

import java.util.Scanner;

public class Numbers {
    private int quantityOfNumbers = 0;
    private int greaterNumber = 0;
    private int lessNumber = 0;
    private int sumOfAllNumbers = 0;
    private float averageOfAllNumbers;

    public Numbers() {
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        while (number != -1) {
            this.quantityOfNumbers++;
            if (this.quantityOfNumbers == 1) {
                this.greaterNumber = number;
                this.lessNumber = number;
            } else {
                if (number > this.greaterNumber) this.greaterNumber = number;
                if (number < this.lessNumber) this.lessNumber = number;
            }
            this.sumOfAllNumbers += number;
            number = input.nextInt();
        }
        this.averageOfAllNumbers = (float) this.sumOfAllNumbers / this.quantityOfNumbers;
        System.out.printf("Quantidade de numeros: %d%n", this.quantityOfNumbers);
        System.out.printf("Maior numero: %d%n", this.greaterNumber);
        System.out.printf("Menor numero: %d%n", this.lessNumber);
        System.out.printf("Media dos numeros: %.2f%n", this.averageOfAllNumbers);
    }
}
