package algoLive;

import java.util.Arrays;
import java.util.Scanner;

public class DijkstraMain {
    static int N;
    static int[][] g;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        g = new int[N][N];
        boolean[] v = new boolean[N];
        int[] dist = new int[N]; // 다익스트라 d[]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                g[i][j] = sc.nextInt(); // 진출 차수
            }
            dist[i] = Integer.MAX_VALUE; // 매 시점에서 mst에 포함된 노드들에서 다른 노드로 향하는 최소 가중치
        }
        dist[0]=0; // 초기 시작 노드
        System.out.println(Arrays.toString(dist));
        System.out.println();
        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!v[j] && min>dist[j]){ // 아직 해당 노드 미탐색 + 해당 시점에서 갈 수 있는 최소거리 노드를 찾는 과정
                    minVertex=j; // for j 가 다 돌면 해당 변수는 가장 가까운 노드를 담는다.
                    min=dist[j]; // 다 돌면 해당 변수는 가장 가까운 노드까지의 가중치를 담는다.
                }
            }
            v[minVertex]=true; // 방문!
            System.out.println(Arrays.toString(v));
            System.out.println("minVertex="+minVertex);
            System.out.println("min="+min);
            if (minVertex==N-1) break; // N-1개의 간선 -> MST
            for (int j = 0; j < N; j++) { // 다음 시점을 위한 세팅. 방금 추가된 노드에서 진출하는 간선 중에 이미 세팅된 minEdge보다 더 작은 값이 있으면 추가.
                if (!v[j] && g[minVertex][j]!=0 && dist[j]>min+g[minVertex][j]){
                    dist[j] = min+g[minVertex][j];
                }
            }
            System.out.println(Arrays.toString(dist));
            System.out.println("=============================");
        }
        System.out.println(dist[N-1]);
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