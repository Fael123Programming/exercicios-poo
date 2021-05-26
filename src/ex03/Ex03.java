package ex03;
import java.util.Scanner;

public class Ex03{
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	Fibonacci fb = new Fibonacci();
	int n = input.nextInt();
	System.out.printf("Posicao (%d): %d%n",n,fb.getFibonacciNumber(n));
    }
}
