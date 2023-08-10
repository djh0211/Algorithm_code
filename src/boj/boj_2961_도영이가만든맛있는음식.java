package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2961_도영이가만든맛있는음식 {
    static int N, min = Integer.MAX_VALUE;
    static int[] sours;
    static int[] bits;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sours = new int[N];
        bits = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sours[i] = Integer.parseInt(st.nextToken());
            bits[i] = Integer.parseInt(st.nextToken());
        }
        superSet(0, 1, 0, 0);
        System.out.println(min);

    }
    static void superSet(int cnt, int sourSum, int bitSum, int len){
        if (cnt == N && len==0) {
            return;
        } else if (cnt == N && len > 0) {
            min = Math.min(min, Math.abs(sourSum - bitSum));
            return;
        }
        superSet(cnt + 1, sourSum * sours[cnt],
                bitSum + bits[cnt], len + 1);
        superSet(cnt + 1, sourSum, bitSum, len);


    }
}
