package swea;

import java.io.*;
import java.util.*;

public class swea_1227_미로2 {
    static int[][] board;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1227_미로2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            board = new int[100][100];
            int t = Integer.parseInt(br.readLine());
            char[] tempCharArr;
            tempCharArr = br.readLine().toCharArray();
//            for (int j = 0; j < tempCharArr.length; j++) {
//                board[i][j] = ;
//            }
        }
    }
}
