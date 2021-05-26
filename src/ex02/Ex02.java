package ex02;
import java.util.Scanner;

public class Ex02{
	public static void main(String[] args){
            Scanner input = new Scanner(System.in);
            System.out.print("R$ ");
            Monetary mn = new Monetary(input.nextInt());
            System.out.println(mn.getQuantitiesOfNotes());
	}
}
