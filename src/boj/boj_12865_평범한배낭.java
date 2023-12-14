package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_12865_평범한배낭 {
    static int N,K;
    static int[] W,V;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        W = new int[N+1];
        V = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(W));

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j<W[i]){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]]+V[i]);
                }
            }
        }

//        for (int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(dp[N][K]);


    }
}
