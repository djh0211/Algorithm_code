package boj;

import java.io.*;
import java.util.*;

public class boj_1753_최단경로 {
    static int V,E,start;
    static ArrayList<int[]>[] adjList;
    static int[] distance;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        adjList = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,w});
        }

        distance = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        boolean[] v = new boolean[V + 1];
        distance[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        pq.offer(new int[]{start, distance[start]});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min = cur[1];
            if (v[minVertex]) break;
            for (int[] next:adjList[minVertex]
                 ) {
                if (!v[next[0]] && min + next[1]<distance[next[0]]){
                    distance[next[0]] = min + next[1];
                    pq.offer(new int[]{next[0], distance[next[0]]});
                }
            }
        }
        for (int i = 1; i < V + 1; i++) {
            System.out.println(distance[i]!=Integer.MAX_VALUE ? distance[i] : "INF");
        }




    }
}
