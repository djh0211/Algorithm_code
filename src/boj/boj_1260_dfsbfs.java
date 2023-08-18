package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1260_dfsbfs {
    static int N,M,V;
    static int a,b;
    static StringBuilder sb;
    static int[][] g;
    static boolean[] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        g = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            g[a][b] = g[b][a] = 1;
        }
        sb = new StringBuilder();

        v = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        v = new boolean[N+1];
        bfs(V);

        System.out.println(sb.toString());


    }
    static void dfs(int node){
        sb.append(node).append(" ");
        v[node] = true;
        for (int i = 1; i < N+1; i++) {
            if (g[node][i]==1 && !v[i]){
                dfs(i);
            }
        }
    }
    static void bfs(int start){
        v[start] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");
            for (int i = 1; i < N+1; i++) {
                if (g[cur][i]==1 && !v[i]){
                    q.offer(i);
                    v[i] = true;
                }
            }
        }



    }
}
