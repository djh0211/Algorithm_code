package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1992_쿼드트리 {
    static int N;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer buffer;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            buffer = new StringBuffer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = buffer.charAt(j) - '0';
            }
        }
        goSmall(N,0,0);
        System.out.println(sb.toString());


    }
    static void goSmall(int size, int x, int y){
        // 전체 같은색인지 확인
        int sum = 0;
        int nextSize = size/2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += board[x+i][y+j];
            }
        }
        if (sum == size*size){ // 모두 흑
            sb.append("1");
        } else if (sum == 0) { // 모두 백
            sb.append("0");
        } else { // 섞임
            sb.append("(");
            goSmall(nextSize, x, y);
            goSmall(nextSize, x, y+nextSize);
            goSmall(nextSize, x+nextSize, y);
            goSmall(nextSize, x+nextSize, y+nextSize);
            sb.append(")");
        }
    }
}
