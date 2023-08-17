package algoLive;

import java.util.*;

// 인접리스트
public class GraphList {
    static List<Integer>[] g;
    static int N;
    static boolean[] v;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int E = sc.nextInt();
        g = new List[N];
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }
        v = new boolean[N];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            g[from].add(to);
            g[to].add(from);
        }
        for (List<Integer> a:g
             ) {
            System.out.println(a);
        }
//        dfs(0);
        bfs(0);
        sc.close();
    }
    static void dfs(int i){
        v[i] = true;
        System.out.print((char)(i+'A')+" ");
        for (int to:g[i]
        ) {
            if (!v[to]){ // i에서 j로 갈 수 있어야 하고 아직 미방문이어야한다.
                dfs(to);
            }
        }
    }
    static void bfs(int i){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        v[i] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print((char)(cur + 'A')+" ");
            for (int to:g[cur]
                 ) {
                if (!v[to]){
                    q.offer(to);
                    v[to] = true;
                }
            }
        }
    }
}
/*
 *       A0
 *      / \
 *     B1 C2
 *    / \ /
 *   D3  E4
 *    \  /
 *     F5ㅡG6
 *
 * */

//bfs
//? ? ? ? ? ? ? : 0->N
//? ? ? ? ? ? ? : N->0
//bfs
//A B C D E F G : 0->N
//A C B E D F G : N->0

//7
//8
//0 1
//0 2
//1 3
//1 4
//2 4
//3 5
//4 5
//5 6

