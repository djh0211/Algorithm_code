package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_7576_토마토 {
    static int N,M;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] board;
    static int[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        v = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                v[i][j] = -1;
            }
        }
        bfs();
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]!=-1 && v[i][j]==-1) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max,v[i][j]);
            }
        }
        System.out.println(max);


    }
    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==1) {
                    q.offer(new int[]{i,j,0});
                    v[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dxy:move
                 ) {
                int nx = cur[0] + dxy[0];
                int ny = cur[1] + dxy[1];
                if (0<=nx && nx<N && 0<=ny && ny<M && board[nx][ny]==0 && v[nx][ny]==-1) {
                    v[nx][ny] = cur[2]+1;
                    q.offer(new int[]{nx,ny,v[nx][ny]});
                }
            }
        }
    }

    static boolean isDone() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }
}
