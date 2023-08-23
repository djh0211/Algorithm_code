package youtubeLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTPrimTest {
    static class Edge implements Comparable<Edge>{
        int from,to,weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
//            return this.weight - o.weight;
            return Integer.compare(this.weight, o.weight);
        }
    }
    static Edge[] edgeList;
    static int V,E;
    static int[] parents;

    static void make(){
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    static int find(int i){
        if (parents[i]==i){
            return i;
        }
        return parents[i] = find(parents[i]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot){
            // cycle
            return false;
        }
        parents[bRoot] = aRoot; // level 관리 필요(밸런스 트리를 위해)
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from,to,weight);
        }

        // 가중치 오름차순 정렬
        Arrays.sort(edgeList);

        // v개의 정점으로 make set 작업
        make();

        int result = 0;
        int count = 0;

        for (Edge e:edgeList
        ) {
            if (union(e.from, e.to)){
                // true 면 노 사이클
                result += e.weight;
                if (++count == V-1){
                    // stop
                    break;
                }
            }
        }
        System.out.println(result);


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