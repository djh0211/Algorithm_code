package boj;
import java.util.*;
import java.io.*;

public class boj_10026_적록색약 {
    static char[][] board;
    static int N, cnt1, cnt2;
    static boolean[][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]){
                    dfs(i,j);
                    cnt1++;
                }
            }
        }
        v = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'G') {
                    board[i][j] = 'R';
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]){
                    dfs(i,j);
                    cnt2++;
                }
            }
        }
        System.out.println(cnt1 +" "+ cnt2);



    }
    static void dfs(int i, int j){
        v[i][j] = true;
        char color = board[i][j];
        for (int[] dxy:move
             ) {
            int nx = i + dxy[0];
            int ny = j + dxy[1];
            if (0<=nx && nx<N && 0<=ny && ny<N && !v[nx][ny] && board[nx][ny]==color){
                dfs(nx,ny);
            }
        }
    }
}
