package ex01;
import java.util.Scanner;

public class Ex01{
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	Price prices = new Price(input.nextFloat(),input.nextFloat());
	System.out.println(prices.whatIsBetter());
    }
}
