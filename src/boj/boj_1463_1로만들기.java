package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1463_1로만들기 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i < N ; i++) {
            dp[i+1] = Math.min(dp[i] + 1, dp[i+1]);
            if (i*2 <= N) dp[i*2] = Math.min(dp[i] + 1, dp[i*2]);
            if (i*3 <= N) dp[i*3] = Math.min(dp[i] + 1, dp[i*3]);
        }
        System.out.println(dp[N]);

    }
}
