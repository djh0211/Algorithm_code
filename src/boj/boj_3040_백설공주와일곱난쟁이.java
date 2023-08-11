package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3040_백설공주와일곱난쟁이 {
    static int[] arr = new int[9];
    static int[] b = new int[7];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        comb(0,0);

    }
    static void comb(int cnt, int start){
        if (cnt==7){
            if (Arrays.stream(b).sum() == 100){
                for (int c:b
                     ) {
                    System.out.println(c);
                }
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            b[cnt] = arr[i];
            comb(cnt+1, i+1);
        }
    }
}
