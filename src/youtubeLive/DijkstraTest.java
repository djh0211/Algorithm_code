package youtubeLive;

import java.util.*;
import java.io.*;

public class DijkstraTest {
    static class Node{
        int vertex,weight;
        Node next;


        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int V = Integer.parseInt(st.nextToken()); // 정점개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        st = new StringTokenizer(br.readLine().trim());
        int start = Integer.parseInt(st.nextToken()); // 시작점 인덱스
        int end = Integer.parseInt(st.nextToken()); // 도착점 인덱스

        Node[] adjList = new Node[V];
        int[] distance = new int[V];
        boolean[] v = new boolean[V];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to,weight,adjList[from]);
        }

        final int INF = Integer.MAX_VALUE;
        Arrays.fill(distance,INF);

        distance[start] = 0;
        int min = 0, stopOver = 0;
        for (int i = 0; i < V; ++i) { // 모든 정점을 다 처리할때까지 반복
            // 미방문 정점 중에 출발지에서 가장 가까운 점을 찾는다.
            stopOver = -1;
            min = INF;
            for (int j = 0; j < V; j++) {
                if (!v[j] && distance[j]<min){
                    stopOver = j;
                    min = distance[j];
                }
            }
            if (stopOver == -1) break;
            v[stopOver] = true;
            // 상황에 따른 처리 : 경유지가 문제의 도착지면 더 이상 안해도 될때에 추가(출발지에서 모든 노드로의 최단거리를 구할때에는 하지 말것 . 마지막 노드에서 break하기)
            if (stopOver == end) break;

            // 경유지를 이용해서 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
            for (Node temp = adjList[stopOver]; temp != null ; temp = temp.next) {
                if (!v[temp.vertex] &&
                        distance[temp.vertex] > min+temp.weight){
                    distance[temp.vertex] = min+ temp.weight;
                }
            }
        }
        System.out.println(distance[end]!=INF ? distance[end] : "impossible");
    }
}
