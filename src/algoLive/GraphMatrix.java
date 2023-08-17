package algoLive;

import java.util.ArrayDeque;
import java.util.Scanner;

public class GraphMatrix {
    static int[][] g;
    static int N;
    static boolean[] v;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int E = sc.nextInt();
        g = new int[N][N];
        v = new boolean[N];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            g[from][to] = g[to][from] = 1;
        }
//        dfs(0);
        bfs(0);
        sc.close();
    }
    static void dfs(int i){
        v[i] = true;
        System.out.print((char)(i+'A')+" ");
        for (int j = 0; j < N; j++) {
            if (g[i][j]!=0 && !v[j]){ // i에서 j로 갈 수 있어야 하고 아직 미방문이어야한다.
                dfs(j);
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
            for (int j = 0; j < N; j++) {
                if (g[cur][j]!=0 && !v[j]){
                    q.offer(j);
                    v[j] = true;
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

