package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1012_유기농배추 {
    static int N,M,K;
    static int[][] board;
    static boolean[][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            v = new boolean[N][M];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[y][x] = 1;
            }
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (board[j][k]==1 && !v[j][k]) {
                        v[j][k] = true;
                        dfs(j,k);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);



//            break;

        }
    }
    static void dfs(int x, int y) {
        for (int[] dxy:move
             ) {
            int nx = x+dxy[0];
            int ny = y+dxy[1];
            if (0<=nx && nx<N && 0<=ny && ny<M && board[nx][ny]==1 && !v[nx][ny]) {
                v[nx][ny] = true;
                dfs(nx,ny);
            }
        }
    }
}
