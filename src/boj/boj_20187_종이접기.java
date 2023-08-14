package boj;
import java.util.*;
import java.io.*;

public class boj_20187_종이접기 {
    static int k, h;
    static char[] orders;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        orders = new char[2*k];
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*k; i++) {
            orders[i] = st.nextToken().charAt(0);
        }
        h = Integer.parseInt(br.readLine());
        int[][] arr;
        arr = new int[1][1];
        arr[0] = new int[]{h};

        for (int i = orders.length - 1; i >= 0; i--) {
            char c = orders[i];
            arr = unfold(arr, c);
        }
        for (int[] row:arr
             ) {
            for (int o:row
                 ) {
                sb.append(o).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    static int[][] unfold(int[][] arr1, char c){
        int[][] arr2 = new int[1][1];
        if (c=='L'){
            int n = arr1.length;
            int m = arr1[0].length;
            arr2 = new int[n][m * 2];
            for (int i = 0; i < arr2.length; i++) {
                for (int j = 0; j < m; j++) {
                    arr2[i][j] = arr1[i][j];
                }
                for (int j = 0; j < m; j++) {
                    if (arr1[i][j] == 1){
                        arr2[i][2*m - 1 - j] = 0;
                    }
                    if (arr1[i][j] == 0){
                        arr2[i][2*m - 1 - j] = 1;
                    }
                    if (arr1[i][j] == 2){
                        arr2[i][2*m - 1 - j] = 3;
                    }
                    if (arr1[i][j] == 3) {
                        arr2[i][2*m - 1 - j] = 2;
                    }
                }
            }
        }
        else if (c=='R'){
            int n = arr1.length;
            int m = arr1[0].length;
            arr2 = new int[n][m * 2];
            for (int i = 0; i < arr2.length; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr1[i][j] == 1){
                        arr2[i][m - 1 - j] = 0;
                    }
                    if (arr1[i][j] == 0){
                        arr2[i][m - 1 - j] = 1;
                    }
                    if (arr1[i][j] == 2){
                        arr2[i][m - 1 - j] = 3;
                    }
                    if (arr1[i][j] == 3) {
                        arr2[i][m - 1 - j] = 2;
                    }
                }
                for (int j = 0; j < m; j++) {
                    arr2[i][m + j] = arr1[i][j];
                }
            }
        }
        else if (c=='U'){
            int n = arr1.length;
            int m = arr1[0].length;
            arr2 = new int[n*2][m];
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < m; j++) {
                    arr2[i][j] = arr1[i][j];
                }
                for (int j = 0; j < m; j++) {
                    if (arr1[i][j] == 1){
                        arr2[2*n - 1 - i][j] = 3;
                    }
                    if (arr1[i][j] == 3){
                        arr2[2*n - 1 - i][j] = 1;
                    }
                    if (arr1[i][j] == 2){
                        arr2[2*n - 1 - i][j] = 0;
                    }
                    if (arr1[i][j] == 0) {
                        arr2[2*n - 1 - i][j] = 2;
                    }
                }
            }
        }
        else if (c=='D'){
            int n = arr1.length;
            int m = arr1[0].length;
            arr2 = new int[n*2][m];
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr1[i][j] == 1){
                        arr2[n - 1 - i][j] = 3;
                    }
                    if (arr1[i][j] == 3){
                        arr2[n - 1 - i][j] = 1;
                    }
                    if (arr1[i][j] == 2){
                        arr2[n - 1 - i][j] = 0;
                    }
                    if (arr1[i][j] == 0) {
                        arr2[n - 1 - i][j] = 2;
                    }
                }
                for (int j = 0; j < m; j++) {
                    arr2[n + i][j] = arr1[i][j];
                }
            }
        }
        return arr2;
    }
}
