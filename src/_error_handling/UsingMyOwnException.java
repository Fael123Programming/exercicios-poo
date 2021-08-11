package _error_handling;

public class UsingMyOwnException {
    public static void main(String[] args) {
        //System.out.println(divide(12,0));
        try{
            divide(11,2);
            divide(5,6); //If it is being executed, it means that the previous line went nice.
            System.out.println("If it is being executed, it means that everything went cool...");
        }catch(DivisionByZeroException dbzexc){
            System.out.println(dbzexc.getMessage());
            //dbzexc.printStackTrace();
        }finally {
            System.out.println("Execution terminated");
        }
    }

    public static double divide(int numerator, int denominator) throws DivisionByZeroException{
        if(denominator == 0) throw new DivisionByZeroException();
        else return (double) numerator / denominator;
    }
}
