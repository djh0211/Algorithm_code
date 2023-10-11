package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2458_키순서 {
    static int N,M,INF=Integer.MAX_VALUE/2;
    static int[][] distance;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N+1][N+1]; // 1부터 사용
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                distance[i][j] = INF;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            distance[a][b] = 1;
        }
        for (int i = 0; i < N+1; i++) {
            distance[i][i]=0;
        }
//        System.out.println(Arrays.deepToString(distance));
//        for (int i = 1; i < N+1; i++) {
//            for (int j = 1; j < N+1; j++) {
//                System.out.print(distance[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        for (int k = 1; k <= N; k++) { //경유지
            for (int i = 1; i <= N; i++) {
                if (i==k) continue;
                for (int j = 1; j <= N; j++) {
                    if (i==j || j==k) continue;
                    if (distance[i][j] > distance[i][k]+distance[k][j]){
                        distance[i][j] = distance[i][k]+distance[k][j];
                    }
                }
            }
        }

//        for (int i = 1; i < N+1; i++) {
//            for (int j = 1; j < N+1; j++) {
//                System.out.print(distance[i][j]+" ");
//            }
//            System.out.println();
//        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            // i->j j->i 둘 중 하나는 있어야해
            for (int j = 1; j <= N; j++) {
                if (i==j) continue;
                if (distance[i][j]>=INF){
                    // 못가는것
                    if (distance[j][i]>=INF){
                        // 키를 알 수 없는 애들
                        cnt++;
                        break;
                    }
                }
            }
        }
        System.out.println(N-cnt);

    }
}
