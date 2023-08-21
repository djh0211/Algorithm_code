package algoLive;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopologicalSortDfs {
    static int N,M; // 정점수,간선수
    static List<Integer>[] g;
    static ArrayDeque<Integer> s; // stack
    static boolean[] v;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        g = new List[N+1]; // 진출 차수
        for (int i = 0; i < N+1; i++) {
            g[i] = new ArrayList<>();
        }
        s = new ArrayDeque<>();
        v = new boolean[N+1];
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
        }
        for (int i = 1; i < N+1; i++) {
            if (!v[i]) dfs(i);
        }
        while (!s.isEmpty()) System.out.print(s.poll()+" ");
        sc.close();

    }
    static void dfs(int i){
        v[i] = true;
        for (int j:g[i]
             ) {
            if (!v[j]) dfs(j);
        }
        s.push(i);

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