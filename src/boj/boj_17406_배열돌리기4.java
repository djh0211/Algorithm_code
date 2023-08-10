package boj;
import java.util.*;
import java.io.*;

public class boj_17406_배열돌리기4 {
    static int N, M, K;
    static int r,c,s;
    static int[][] arr;
    static int[][] arr2;
    static int[][] tcs;
    static boolean[] v;
    static int[][] order;
    static ArrayList<int[][]> orders = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static void getMin(){
        int temp = 0;
        for (int i = 1; i <= N; i++) {
            temp = 0;
            for (int j = 1; j <=M ; j++) {
                temp += arr[i][j];
            }
            min = Math.min(temp, min);
        }
    }
    static void perm(int cnt){
        if (cnt == K){
            int[][] tempCopy = new int[K][3];
            for (int i = 0; i < order.length; i++) {
                for (int j = 0; j < order[i].length; j++) {
                    tempCopy[i][j] = order[i][j];
                }
            }
            orders.add(tempCopy);
            return;
        }
        for (int i = 0; i < K; i++) {
            if (v[i]) continue;
            v[i] = true;
            order[cnt] = tcs[i];
            perm(cnt+1);
            v[i] = false;
        }
    }


    static void rotate(int r, int c, int s){
        for (int g = 0; g < s; g++) {
            int T = arr[r-s+g][c-s+g];
            for (int i = 0; i < 2*(s-g); i++) {
                arr[r-s+g+i][c-s+g]=arr[r-s+g+i+1][c-s+g];
            }
            for (int i = 0; i < 2*(s-g); i++) {
                arr[r+s-g][c-s+g+i]=arr[r+s-g][c-s+g+i+1];
            }
            for (int i = 0; i < 2*(s-g); i++) {
                arr[r+s-g-i][c+s-g]=arr[r+s-g-i-1][c+s-g];
            }
            for (int i = 0; i < 2*(s-g)-1; i++) {
                arr[r-s+g][c+s-g-i]=arr[r-s+g][c+s-g-i-1];
            }
            arr[r-s+g][c-s+g+1] = T;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        arr2 = new int[N + 1][M + 1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arr2[i][j] = arr[i][j];

            }
        }
        tcs = new int[K][3];
        order = new int[K][3];
        v = new boolean[K];
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            tcs[tc] = new int[]{r,c,s};
        }
        perm(0);
        int cvcv=1;
        for (int[][] ords:orders
             ) {
            for (int[] ord:ords
                 ) {
                rotate(ord[0], ord[1], ord[2]);
            }
            getMin();
            clear();
        }
        System.out.println(min);
    }
    static void clear(){
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                arr[i][j] = arr2[i][j];
            }
        }
    }
}
