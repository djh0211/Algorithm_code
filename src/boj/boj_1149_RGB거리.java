package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class boj_1149_RGB거리 {
    static int[][] cost, dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); //R
            cost[i][1] = Integer.parseInt(st.nextToken()); //G
            cost[i][2] = Integer.parseInt(st.nextToken()); //B
        }
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        for (int i = 1; i < N; i++) {
            // i -> R
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + cost[i][0];
            // i -> G
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + cost[i][1];
            // i -> B
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + cost[i][2];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, dp[N-1][i]);
        }
        System.out.println(result);

    }
}
