package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4012_요리사 {
    static int N;
    static int[][] table;
    static int[] list;
    static int[] list2;
    static int min = Integer.MAX_VALUE;
    static boolean[] v;
    static void comb(int cnt, int start){
        if (cnt==N/2){
            v = new boolean[N+1];
            for (int i = 0; i < N/2; i++) {
                v[list[i]] = true;
            }
            int idx = 0;
            for (int i = 1; i <= N; i++) {
                if (!v[i])
                    list2[idx++] = i;
            }

            int sumA = 0;
            for (int i = 0; i < N/2; i++) {
                for (int j = i+1; j < N/2; j++) {
                    sumA += table[list[i]][list[j]];
                    sumA += table[list[j]][list[i]];
                }
            }

            int sumB = 0;
            for (int i = 0; i < N/2; i++) {
                for (int j = i+1; j < N/2; j++) {
                    sumB += table[list2[i]][list2[j]];
                    sumB += table[list2[j]][list2[i]];
                }
            }

            min = Math.min(Math.abs(sumB-sumA), min);
            return;
        }
        for (int i = start; i <= N; i++) {
            list[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_4012_요리사.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            table = new int[N+1][N+1];
            // init table
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 조합 구하기
            list = new int[N/2];
            list2 = new int[N/2];

            comb(0,1);

            sb.append("#").append(tc+1).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }
}
