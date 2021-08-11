package _error_handling;

public class ErrorHandling_2 {
    public static void main(String[] args) {
        System.out.println("Method main is starting");
        method_01();
        System.out.println("Method main is finishing");
    }

    private static void method_01(){ //Error handling is made in the place that calls the problematic code
        System.out.println("Method_01 is starting");
        try{
            method_02();
        }catch(ArithmeticException exc){
            System.out.println("Error: " + exc.getMessage());
        }
        System.out.println("Method_01 is finishing");
    }

    private static void method_02(){
        System.out.println("Method_02 is starting");
        double division = 10 / 0; //Because this line, this method is interrupted right here
        System.out.println("Method_02 is finishing"); //This line isn't executed
    }
}
