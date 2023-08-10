package algoLive;
import java.util.*;
import java.io.*;
public class nextPermMain {
    static int N=4,C=0;
    static int[] a={3,1,4,2};
    static void swap(int i, int j){
        int T = a[i];
        a[i] = a[j];
        a[j] = T;
    }
    static boolean npn(){
        // 1. i 교환위치 찾기
        int i = N-1;
        while(i>0 && a[i-1] >= a[i]) i--; // 꼭대기
        if (i==0) return false;

        // 2. j 교환할 값 찾기
        int j = N-1;
        while(a[i-1] >= a[j]) j--; // 꼭대기
        swap(i-1, j);

        // 3. k 이후 오름차순 정렬
        int k = N-1;
        while (i<k) swap(i++,k--);
        return true;
    }
    public static void main(String[] args) throws Exception{
        Arrays.sort(a);
        do {
            System.out.println(Arrays.toString(a));
            C++;
            System.out.println(C);
        } while (npn());




    }
}
