package _error_handling;

public class MultiCatch {
    public static void main(String[] args) {
        int myNumbers[] = {5,10,15,20,25,30};
        MultiCatch.showArray(myNumbers);
        try {
            MultiCatch.divideValuesOfAnArray(myNumbers, 0, 12);
        }catch(DivisionByZeroException | IndexOutOfBoundsException exc) { //An unique exception object which can refer to two exception classes
            System.out.println(exc.getMessage());
        }finally{
            MultiCatch.showArray(myNumbers);
        }
    }

    public static void divideValuesOfAnArray(int []array, int divisor, int lastPosition) throws DivisionByZeroException, IndexOutOfBoundsException{
        if( divisor == 0) throw new DivisionByZeroException();
        //if( lastPosition >= array.length) throw new IndexOutOfBoundsException(); <- Not necessary because IndexOutOfBoundsException is an unchecked exception
        for(int counter = 0 ; counter <= lastPosition ; counter ++){
            array [counter] = array [counter] / divisor;
        }
    }

    public static void showArray(int []array){
        for(int number : array) System.out.println(number);
    }
}
