package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class boj_1475_방번호 {
    static int[] v;
    public static void main(String[] args) throws Exception {
        v = new int[10];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        for (int i = 0; i < N.length(); i++) {
            int n = N.charAt(i) - '0';
            v[n]++;
        }
        int max = 0;
        for (int i = 0; i < 10; i++) {
            if (i==6 || i==9) {
                continue;
            }
            max = Math.max(max, v[i]);
        }
        max = Math.max(max, (v[6]+v[9])%2==0?(v[6]+v[9])/2:(v[6]+v[9])/2+1 );
        System.out.println(max);


    }
}
