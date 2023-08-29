package algoLive;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra0828 {
    static int N;
    static int[][] g;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] distance = new int[N]; // 가중치를 가지는 1차원 배열
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g[i][j] = sc.nextInt(); // 진출 차수
            }
            distance[i] = Integer.MAX_VALUE; // 매 시점에서 mst에 포함된 노드들에서 다른 노드로 향하는 최소 가중치
        }
        distance[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1],o2[1])); // 가중치를 기준으로 가장 작은 값
        pq.offer(new int[]{0,distance[0]});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min=cur[1];
            if (v[minVertex]) continue; // 현재 정점이 이미 방문했다면 jump
            v[minVertex]=true; // 방문!
            for (int j = 0; j < N; j++) { // 다음 시점을 위한 세팅. 방금 추가된 노드에서 진출하는 간선 중에 이미 세팅된 minEdge보다 더 작은 값이 있으면 추가.
                if (!v[j] && g[minVertex][j]!=0 && distance[j]>min+g[minVertex][j]){
                    distance[j] = min+g[minVertex][j];
                    pq.offer(new int[]{j,distance[j]});
                }
            }
        }
        System.out.println(distance[N-1]);

    }
}
