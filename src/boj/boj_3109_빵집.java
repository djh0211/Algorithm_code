package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3109_빵집 {
    static int R,C,res;
    static boolean flag;
    static char[][] board;
    static int[] b;
    static int[] move = {-1,0,1};
    static boolean[][] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        v = new boolean[R][C];
        b = new int[C];

        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = temp.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
//            for (boolean[] a:v
//            ) {
//                System.out.println(Arrays.toString(a));
//            }

            flag = false;
            v[i][0] = true;
            b[0] = i;
            if (dfs(i,0)) res++;
            else v[i][0] = false;
//            System.out.println();
        }
        System.out.println(res);
//
    }
    static boolean dfs(int x, int y){ // y+1번째 열의 지나가는 행을 골라주세요.
        // 기저
        if (y == C-1){
//            routes.add(Arrays.copyOf(b,C));
//            System.out.println(Arrays.toString(b));
            return true;
        }
        // 유도
        int nx,ny;
        for (int dx:move
             ) {
            nx = x+dx;
            ny = y+1;
            // 범위 내 빈공간, 미 방문 지나갈수 있다.
            if (0<=nx && nx<R && 0<=ny && ny<C && board[nx][ny]=='.' && !v[nx][ny]){
                v[nx][ny] = true;
                b[ny] = nx;
                if (dfs(nx,ny)) return true;
//                if (flag) return;
//                v[nx][ny] = false;
            }
        }
        return false;
    }
}
