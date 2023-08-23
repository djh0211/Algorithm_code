package algoLive;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimPQMain {
    static int N;
    static int[][] g;
    static int[] p;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] minEdge = new int[N]; // 가중치를 가지는 1차원 배열
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g[i][j] = sc.nextInt(); // 진출 차수
            }
            minEdge[i] = Integer.MAX_VALUE; // 매 시점에서 mst에 포함된 노드들에서 다른 노드로 향하는 최소 가중치
        }
        int result = 0;
        int cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1])); // 가중치를 기준으로 가장 작은 값
        minEdge[0]=0; // 초기 시작 노드
        pq.offer(new int[]{0,minEdge[0]}); // 정점,가중치
        System.out.println(Arrays.toString(minEdge));
        System.out.println();
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min=cur[1];
            if (v[minVertex]) continue; // 현재 정점이 이미 방문했다면 jump
            v[minVertex]=true; // 방문!
            result+=min; // 가중치 더하기!
            if (cnt++==N-1) break; // N-1개의 간선 -> MST
            for (int j = 0; j < N; j++) { // 다음 시점을 위한 세팅. 방금 추가된 노드에서 진출하는 간선 중에 이미 세팅된 minEdge보다 더 작은 값이 있으면 추가.
                if (!v[j] && g[minVertex][j]!=0 && minEdge[j]>g[minVertex][j]){
                    minEdge[j] = g[minVertex][j];
                    pq.offer(new int[]{j,minEdge[j]});
                }
            }
        }
        System.out.println(result);
        sc.close();
    }
}
/*
5
0 5 10 8 7
5 0 5 3 6
10 5 0 1 3
8 3 1 0 1
7 6 3 1 0

output==>10

7
0 32 31 0 0 60 51
32 0 21 0 0 0 0
31 21 0 0 46 0 25
0 0 0 0 34 18 0
0 0 46 34 0 40 51
60 0 0 18 40 0 0
51 0 25 0 51 0 0

output==>175
*/