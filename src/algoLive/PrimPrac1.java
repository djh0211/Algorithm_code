package algoLive;

import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimPrac1 {
    static int N;
    static int[][] g;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] minEdge = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g[i][j] = sc.nextInt();
            }
            minEdge[i] = Integer.MAX_VALUE;
        }

        int result=0, cnt=0;
        minEdge[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{0,minEdge[0]});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min = cur[1];
            if (v[minVertex]) continue;
            v[minVertex] = true;
            result += min;
            if (cnt++==N-1) break;
            for (int j = 0; j < N; j++) {
                if (!v[j] && g[minVertex][j]!=0 && g[minVertex][j] < minEdge[j]){
                    minEdge[j] = g[minVertex][j];
                    pq.offer(new int[]{j,minEdge[j]});
                }
            }
        }
        System.out.println(result);
        sc.close();
    }
}
