package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1753_최단경로re {
    static int V,E,K;
    static boolean[] v;
    static int[] d;
    static List<int[]>[] g;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        g = new List[V+1];
        v = new boolean[V+1];
        d = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            g[i] = new ArrayList<>();
            d[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g[a].add(new int[]{b,c});
        }
        dijkstra();
        for (int i = 1; i <= V; i++) {
            System.out.print(d[i]+" ");
        }

    }
    static void dijkstra(){
        v = new boolean[V+1];
        d = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        d[K] = 0;
        pq.offer(new int[]{K,0});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int min = cur[1];
            int minVertex = cur[0];
            if (v[minVertex]) continue;
            v[minVertex] = true;
            for (int[] to:g[minVertex]
                 ) {
                if (!v[to[0]] && min+to[1]<d[to[0]]){
                    d[to[0]] = min+to[1];
                    pq.offer(new int[]{to[0],d[to[0]]});
                }
            }
        }
    }
}
