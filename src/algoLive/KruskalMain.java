package algoLive;

import java.util.Arrays;
import java.util.Scanner;

public class KruskalMain {
    static int N;
    static int[][] g;
    static int[] p;
    static void make(){
        p = new int[N];
        for (int i = 0; i < N; i++) p[i] = i;
    }
    static int find(int a){
        if (p[a]==a) return a;
        return p[a]=find(p[a]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;
        p[bRoot] = aRoot;
        return true;

    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int E = sc.nextInt();
        g = new int[E][3];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            g[i] = new int[]{from,to,weight};
        }
        Arrays.sort(g,(o1,o2)->Integer.compare(o1[2],o2[2]));
        make();
        int result = 0;
        int cnt = 0;
        for (int[] edge:g
             ) {
            if (union(edge[0],edge[1])){
                result += edge[2];
                if (++cnt==N-1) break;
            }
        }
        System.out.println(result);
        sc.close();
    }
}
/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

output==>10

7 11
0 1 32
0 2 31
0 5 60
0 6 51
1 2 21
2 4 46
2 6 25
3 4 34
3 5 18
4 5 40
4 6 51

output==>175
*/