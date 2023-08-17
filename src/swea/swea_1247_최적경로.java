package swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1247_최적경로 {
    static int N, result = Integer.MAX_VALUE;
    static int[] company, home, a;
    static int[][] customers;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(new File("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1247_최적경로.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            result = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            company = new int[]{Integer.parseInt(st.nextToken()),
                                Integer.parseInt(st.nextToken())};
            home = new int[]{Integer.parseInt(st.nextToken()),
                             Integer.parseInt(st.nextToken())};
            customers = new int[N][];
            for (int i = 0; i < N; i++) {
                customers[i] = new int[]{Integer.parseInt(st.nextToken()),
                                         Integer.parseInt(st.nextToken())};
            }
            a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = i;
            }
            do {
                int d = calc();
                d += Math.abs(customers[a[0]][0]-company[0]) + Math.abs(customers[a[0]][1]-company[1]) + Math.abs(customers[a[N-1]][0]-home[0]) + Math.abs(customers[a[N-1]][1]-home[1]);
                result = Math.min(result, d);
            } while (nPn());
            sb.append("#").append(tc+1).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int calc(){
        int d = 0;
        for (int i = 0; i < N-1; i++) {
            d += Math.abs(customers[a[i]][0]-customers[a[i+1]][0]) + Math.abs(customers[a[i]][1]-customers[a[i+1]][1]);
        }
        return d;
    }
    static boolean nPn(){
        // 1 꼭대기 찾기
        int i = N - 1;
        while (i>0 && a[i-1] >= a[i]) i--;
        if (i==0) return false;
        // 2 i-1 과 바꿀 위치 찾아서 바꾸기
        int j = N - 1;
        while (a[i-1] >= a[j]) j--;
        swap(i-1, j);

        // 3 뒷 부분 정렬
        int k = N - 1;
        while (i<k) swap(i++,k--);
        return true;
    }

    static void swap(int i, int j){
        int T = a[i];
        a[i] = a[j];
        a[j] = T;
    }
}
