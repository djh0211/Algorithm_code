package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2239_스도쿠 {
    static int[][] board = new int[9][9];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb;
        for (int i = 0; i < 9; i++) {
            sb = new StringBuffer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = sb.charAt(j) - '0';
            }
        }


    }
    static boolean isRowSafe(int row){
        boolean[] rowV = new boolean[9 + 1];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != 0){ // 0은 안 쓰는 인덱스
                if (rowV[board[row][i]]){ // 한 라인에 숫자 중복
                    return false;
                }
                rowV[board[row][i]] = true; // 중복 아니면 체크
            }
        }
        return true;
    }
    static boolean isColSafe(int col){
        boolean[] colV = new boolean[9 + 1];
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != 0){ // 0은 안 쓰는 인덱스
                if (colV[board[i][col]]){ // 한 라인에 숫자 중복
                    return false;
                }
                colV[board[i][col]] = true; // 중복 아니면 체크
            }
        }
        return true;
    }
    static boolean isBoxSafe(int x, int y) {
        boolean[] boxV = new boolean[9 + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[x + i][y + j] != 0) {
                    if (boxV[board[x + i][y + j]]) { // 한 박스에 숫자 중복
                        return false;
                    }
                    boxV[board[x + i][y + j]] = true; // 중복 아니면 체크
                }
            }
        }
        return true;
    }
//    static void
}
