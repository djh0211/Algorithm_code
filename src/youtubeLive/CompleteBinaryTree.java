package youtubeLive;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
    private Object[] nodes;
    private int lastIndex=0; // 채워진 마지막 노드의 인덱스
    private final int SIZE; // 최대 노드의 개수


    public CompleteBinaryTree(int size) {
        SIZE = size;
        nodes = new Object[size+1];
    }

    public boolean isEmpty(){
        return lastIndex==0;
    }
    public boolean isFull(){
        return lastIndex==SIZE;
    }
    public boolean add(T data){
        if (isFull())
            return false;
        nodes[++lastIndex] = data;
        return true;
    }
    public void bfs(){
        if (isEmpty()) return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<Integer> queue = new ArrayDeque();
        // 탐색 시작의 대상부터 큐에 넣기
        queue.offer(1); // 루트 노드 인덱스 제공
        while (!queue.isEmpty()){
            int current = queue.poll();
            // 탐색대상 방문처리
            System.out.print(nodes[current]+" ");
            // 현재 탐색대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
            if (current*2<=lastIndex) queue.offer(current*2);
            if (current*2+1<=lastIndex) queue.offer(current*2+1);

        }

    }
}
