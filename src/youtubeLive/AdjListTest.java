package youtubeLive;

import java.util.Arrays;
import java.util.Scanner;
// 무향 그래프
public class AdjListTest {
    static class Node{
        int vertext;
        Node next;

        public Node(int vertext, Node next) {
            this.vertext = vertext;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertext=" + vertext +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        Node adjList[] = new Node[V];

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        for (Node node:adjList // node : 각 정점의 인접리스트의 헤드
        ) {
            System.out.println(node);
        }
    }
}
