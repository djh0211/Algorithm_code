package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_7569_토마토 {
    static int M,N,H;
    static int[][] move = {{-1,0,0},{0,-1,0},{0,0,1},{0,1,0},{0,0,-1},{1,0,0}};
    static int[][][] board;
    static int[][][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][N][M];
        v = new int[H][N][M];

        for (int i = H-1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    v[i][j][k] = -1;
                }
            }
        }
        bfs();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (board[i][j][k]!=-1 && v[i][j][k]==-1) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    max = Math.max(max, v[i][j][k]);
                }
            }
        }
        System.out.println(max);



        }
        static void bfs() {
            ArrayDeque<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        if (board[i][j][k] == 1) {
                            q.offer(new int[]{i,j,k,0}); // i, j, k, 일수
                            v[i][j][k] = 0;
                        }
                    }
                }
            }
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dxyz : move
                     ) {
                    int nz = cur[0] + dxyz[0];
                    int nx = cur[1] + dxyz[1];
                    int ny = cur[2] + dxyz[2];
                    if (0<=nx && nx<N && 0<=ny && ny<M && 0<=nz && nz<H && board[nz][nx][ny]==0 && v[nz][nx][ny]==-1) {
                        v[nz][nx][ny] = cur[3]+1;
                        q.offer(new int[]{nz,nx,ny,cur[3]+1});
                    }
                }
            }
        }
}
