package algoLive;

import java.util.*;

public class TopologicalSortBfs {
    static int N,M; // 정점수,간선수
    static List<Integer>[] g;
    static int[] inDegree;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        g = new List[N+1]; // 진출 차수
        for (int i = 0; i < N+1; i++) {
            g[i] = new ArrayList<>();
        }
        inDegree = new int[N+1]; // 진입차수 관리
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
            inDegree[b]++;
        }
        sc.close();

        topologicalSort();
    }
    static void topologicalSort(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
//        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i]==0){
                q.offer(i);
            }
        }
        while (!q.isEmpty()){
            int i = q.poll();
            System.out.print(i+" ");
            for (int j:g[i]
                 ) {
                if (--inDegree[j]==0){
                    q.offer(j);
                }
            }
        }
    }
}



/*
3 2
1 3
2 3
==> 1 2 3

4 2
4 2
3 1

==> 3 4 1 2


4 4
1 2
2 3
3 4
4 2
==> cycle
*/