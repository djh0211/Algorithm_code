package boj;

import youtubeLive.DijkstraTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1916_최소비용구하기 {
    static int N,M,start,end;
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;
    static class Node {
        int vertex, weight;
        Node next;


        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        Node[] adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to,weight,adjList[from]);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        if (start==end){
            System.out.println(0);
            return;
        }
        boolean[] v = new boolean[N+1];
        distance = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            distance[i] = INF;
        }
        distance[start] = 0;

        distance[start] = 0;
        int min = 0, stopOver = 0;
        for (int i = 1; i < N+1; ++i) { // 모든 정점을 다 처리할때까지 반복
            // 미방문 정점 중에 출발지에서 가장 가까운 점을 찾는다.
            stopOver = -1;
            min = INF;
            for (int j = 1; j < N+1; j++) {
                if (!v[j] && distance[j]<min){
                    stopOver = j;
                    min = distance[j];
                }
            }
            if (stopOver == -1) continue;
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
        System.out.println(distance[end]);
    }
}
