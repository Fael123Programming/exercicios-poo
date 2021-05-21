package banksystemproject;

import java.util.Scanner;
public class BankSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CurrentAccount mine = new CurrentAccount();
        CurrentAccount yours = new CurrentAccount();
        mine.deposit(100);
        System.out.println("mine's current amount: $"+mine.totalAmount);//mine has $100
        mine.withDraw(101);//It will not happen
        mine.withDraw(50);//Now, it's fine
        System.out.println("mine's current amount: $"+mine.totalAmount);//mine has $50
        
        yours.deposit(2000);
        System.out.println("your's current amount: $"+yours.totalAmount);//yours has $2000
        yours.withDraw(500);
        System.out.println("your's current amount: $"+yours.totalAmount);//yours has $1500
        yours.transfer(mine,1300);
        System.out.println("your's current amount: $"+yours.totalAmount);//yours has $200
        System.out.println("mine's current amount: $"+mine.totalAmount);//mine has $1350

        mine.transfer(yours,1500);//It will not happen
        mine.transfer(yours,1300);//Ok, there's no problem
        System.out.println("mine's current amount: $"+mine.totalAmount);//mine has $50
        System.out.println("your's current amount: $"+yours.totalAmount);//yours has $1500
    }
    
}