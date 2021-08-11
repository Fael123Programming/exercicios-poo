package _error_handling;

//According to where you make error handling, your code may have different behaviours

public class ErrorHandling_1 {
    public static void main(String[] args) {
        System.out.println("Method main is starting");
        method_01();
        System.out.println("Method main is finishing");
    }

    private static void method_01(){
        System.out.println("Method_01 is starting");
        method_02();
        System.out.println("Method_01 is finishing");
    }

    private static void method_02(){//The exception handling was made in the same place it occurred
        System.out.println("Method_02 is starting");
        try {
            double division = 10 / 0;
        }catch(ArithmeticException exc) {
            System.out.println("Error: " + exc.getMessage()); //An exception is handled here
        }
        System.out.println("Method_02 is finishing");//Execution continues
    }
}
