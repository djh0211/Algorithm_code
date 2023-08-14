package youtubeLive;

import java.util.Scanner;

public class SquareNumberTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long X = sc.nextInt();
        int N = sc.nextInt();
        System.out.println(exp2(2, 30));

    }
    // 분할 정복 미적용
    // X^n = X^{n-1}
    // ....
    static long exp1(long x, int n){
        return x * exp1(x, n-1);
    }
    static long exp2(long x, int n){
        if (n==1) return x;
        long y = exp2(x,n/2);
        if (n%2==0) return y*y;
        else return y*y*x;
    }
}
