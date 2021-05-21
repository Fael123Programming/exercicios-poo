package ex03;
import java.util.Scanner;

public class Ex03{
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	Fibonacci fib = new Fibonacci();
	int n = input.nextInt();
	System.out.printf("Posicao (%d): %d%n",n,fib.getFibonacciNumber(n));
    }
}
