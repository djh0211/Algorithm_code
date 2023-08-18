package boj;

import java.io.*;
import java.util.*;

public class boj_1987_알파벳 {
    static int R,C,result;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static char[][] board;
    static boolean[] checked;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        checked = new boolean[26];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        dfs(0,0,0);
        System.out.println(result+1);


    }
    static void dfs(int i, int j, int cnt){
        int nx,ny;
        checked[board[i][j]-'A'] = true;
        result = Math.max(result, cnt);
        for (int[] dxy:move
             ) {
            nx = i + dxy[0];
            ny = j + dxy[1];
            if (0<=nx && nx<R && 0<=ny && ny<C && !checked[board[nx][ny]-'A']){
                dfs(nx,ny,cnt+1);
            }
        }
        checked[board[i][j]-'A'] = false;
    }
}
