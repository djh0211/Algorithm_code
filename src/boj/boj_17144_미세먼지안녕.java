package boj;
import java.util.*;
import java.io.*;

public class boj_17144_미세먼지안녕 {
    static int R,C,T;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] board;
    static int[][] diff;
    static int[] machine;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        diff = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        flabel:for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1){
                    machine = new int[]{i,j};
                    diff[i][j] = -1;
                    diff[i+1][j] = -1;
                    break flabel;
                }
            }
        }
        for (int t = 1; t <= T; t++) {
            diffuse();
            refresh();
        }
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    sum+=board[i][j];
                }
            }
        }
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                System.out.print(board[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(sum);
    }
    static void refresh(){
        int x = machine[0];
        for (int i = 0; i < x-1; i++) {
            board[x-1-i][0] = board[x-2-i][0];
        }
        for (int i = 0; i < C-1; i++) {
            board[0][i] = board[0][i+1];
        }
        for (int i = 0; i < x; i++) {
            board[i][C-1] = board[i+1][C-1];
        }
        for (int i = 0; i < C-2; i++) {
            board[x][C-1-i] = board[x][C-2-i];
        }
        board[x][1] = 0;

        for (int i = 0; i < R-x-3; i++) {
            board[x+2+i][0] = board[x+3+i][0];
        }
        for (int i = 0; i < C-1; i++) {
            board[R-1][i] = board[R-1][i+1];
        }
        for (int i = 0; i < R-x-2; i++) {
            board[R-1-i][C-1] = board[R-2-i][C-1];
        }
        for (int i = 0; i < C-2; i++) {
            board[x+1][C-1-i] = board[x+1][C-2-i];
        }
        board[x+1][1] = 0;


    }
    static void diffuse() {
        int diffuse;
        int cnt = 0;
        int nx, ny;
        diff = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    cnt = 0;
                    for (int[] dxy : move
                    ) {
                        nx = i + dxy[0];
                        ny = j + dxy[1];
                        if (0 <= nx && nx < R && 0 <= ny && ny < C && board[nx][ny]!=-1) {
                            cnt++;
                        }
                    }
                    diffuse = board[i][j] / 5;
                    for (int[] dxy : move
                    ) {
                        nx = i + dxy[0];
                        ny = j + dxy[1];
                        if (0 <= nx && nx < R && 0 <= ny && ny < C && board[nx][ny]!=-1) {
                            diff[nx][ny] += diffuse;
                        }
                    }
                    board[i][j] -= cnt * diffuse;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] += diff[i][j];
            }
        }
    }
}
