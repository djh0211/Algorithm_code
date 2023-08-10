package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_2001_파리퇴치 {
    static int N,M;
    static int[][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int x = 0, y = 0;
            int sum = 0, result = Integer.MIN_VALUE;
            int nx = x, ny = y;
            for (int j = 0; j < N-M+1; j++) {
                nx = x + j;
                for (int k = 0; k < N-M+1; k++) {
                    ny = y + k;
                    sum = 0;
                    for (int l = 0; l < M; l++) {
                        for (int m = 0; m < M; m++) {
                            if (0<=nx+l && nx+l<N && 0<=ny+m && ny+m<N){
                                sum += board[nx+l][ny+m];
                            }
                        }
                    }
                    result = Math.max(result, sum);
                }
            }
            sb.append("#").append(i+1).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
