package ex03;

public class Fibonacci {

    public Fibonacci() {
    }

    public long getFibonacciNumber(int position) {
        long n1 = 1, n2 = 0, res = 0;
        int counter = 0;
        //1,1,2,3,5,8,13,...
        if (position <= 0) return -1;
        while (counter < position) {
            res = n1 + n2;
            if (counter == 1) res = 1;
            n2 = n1;
            n1 = res;
            counter++;
        }
        return res;
    }
}
