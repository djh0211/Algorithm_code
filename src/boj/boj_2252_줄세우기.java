package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2252_줄세우기 {
    static int N,M;
    static List<Integer>[] g;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new List[N + 1];
        inDegree = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            inDegree[b]++;
        }
        topologicalSort();
        System.out.println(sb.toString());


    }
    static void topologicalSort(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i]==0){
                q.offer(i);
            }
        }
        while (!q.isEmpty()){
            int i = q.poll();
            sb.append(i).append(" ");
            for (int o:g[i]
                 ) {
                if(--inDegree[o] == 0){
                    q.offer(o);
                }
            }
        }
    }
}
