package algoLive;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Kruskal_0828 {
    static int v,e;
    static int[][] g;
    static int[] parents;

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        g = new int[e][3];
        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            g[i] = new int[]{from,to,weight};
        }
        System.out.println(Arrays.deepToString(g));
        Arrays.sort(g,(o1,o2)->Integer.compare(o1[2],o2[2]));

        parents = new int[v];
        for (int i = 0; i < v; i++) {
            parents[i] = i;
        }
        int cnt = 0;
        int result=0;
        for (int[] e:g
             ) {
            if (union(e[0],e[1])){
                // no cycle
                result += e[2];
                if (++cnt == v-1){
                    break;
                }
            }
        }
        System.out.println(result);
    }
    static boolean union(int a, int b){
        int root_a = find(a);
        int root_b = find(b);
        if (root_a == root_b)
            // cycle
            return false;
        parents[root_b] = root_a;
        return true;
    }
    static int find(int a){
        if (parents[a]==a){
            return a;
        }
        return parents[a]=find(parents[a]);
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
 */