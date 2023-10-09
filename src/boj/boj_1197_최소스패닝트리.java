package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1197_최소스패닝트리 {
    static int V,E;
    static int[][] g;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        g = new int[V+1][V+1];
        boolean[] v = new boolean[V+1];
        int[] minEdge = new int[V+1];
        for (int i = 1; i <= V; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g[a][b] = c;
            g[b][a] = c;
        }

        int result = 0;
        int cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1])); // 가중치를 기준으로 가장 작은 값
        minEdge[1]=0; // 초기 시작 노드
        pq.offer(new int[]{1,minEdge[1]}); // 정점,가중치
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min=cur[1];
            if (v[minVertex]) continue; // 현재 정점이 이미 방문했다면 jump
            v[minVertex]=true; // 방문!
            result+=min; // 가중치 더하기!
            if (cnt++==V-1) break; // N-1개의 간선 -> MST
            for (int j = 1; j <= V; j++) { // 다음 시점을 위한 세팅. 방금 추가된 노드에서 진출하는 간선 중에 이미 세팅된 minEdge보다 더 작은 값이 있으면 추가.
                if (!v[j] && g[minVertex][j]!=0 && minEdge[j]>g[minVertex][j]){
                    minEdge[j] = g[minVertex][j];
                    pq.offer(new int[]{j,minEdge[j]});
                }
            }
        }



        System.out.println(result);



    }
}

/*
3 3
1 2 1
2 3 2
1 3 3
 */


