package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1406_에디터 {
    public static class LinkedList {
        Node head;
        Node tail;
        Node cur = new Node(null);
        int size = 0;

        void moveLeft(){
            if (cur.prev != null){
                // 움직일수있음
                cur.next = cur.prev;
                cur.prev = cur.prev.prev;
            }
        }
        void moveRight(){
            if (cur.next != null){
                cur.prev = cur.next;
                cur.next = cur.next.next;
            }
        }
        void delete(){
            if (cur.prev != null) {
                // 삭제 가능
                Node temp = cur.prev;
                if (temp.prev == null){
                    // 맨 앞 원소 삭제
                    Node next = cur.next;
                    next.prev = null;
                    head = next;
                    cur.prev = null;
                } else if (temp.next == null) {
                    // 마지막 원소 삭제
                    Node prev = temp.prev;
                    prev.next = null;
                    tail = prev;
                    cur.prev = tail;
                } else {
                    // 중간 원소 삭제
                    Node prev = temp.prev;
                    Node next = temp.next;
                    next.prev = prev;
                    prev.next = next;
                    cur.prev = prev;
                }
                size--;
            }
        }


        void insert(String data) {
            Node newNode = new Node(data);
            if (size==0){
                // 비어있음
                head = newNode;
                tail = newNode;
                cur.prev = newNode;
                cur.next = null;
            } else {
                if (cur.prev == null) {
                    // 커서가 맨 앞임
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                    cur.prev = null;
                    cur.next = newNode;
                } else if (cur.next == null){
                    // 커서가 맨 뒤
                    newNode.prev = tail;
                    tail.next = newNode;
                    tail = newNode;
                    cur.prev = newNode;
                    cur.next = null;
                } else {
                    newNode.prev = cur.prev;
                    newNode.prev.next = newNode;
                    newNode.next = cur.next;
                    newNode.next.prev = newNode;
                    cur.prev = newNode;
                }
            }
            size++;
        }
    }
    public static class Node {
        Node prev;
        Node next;
        String data;

        public Node(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        LinkedList list = new LinkedList();

        for (int i = 0; i < s.length(); i++) {
            list.insert(String.valueOf(s.charAt(i)));
        }
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("P")){
                String k = st.nextToken();
                list.insert(k);
            } else if (command.equals("L")){
                list.moveLeft();
            } else if (command.equals("D")){
                list.moveRight();
            } else {
                list.delete();
            }

//            Node first = list.head;
//            while (first != null){
//                System.out.print(first.data);
//                first = first.next;
//            }
//            System.out.println();
//            System.out.println(list.cur.prev+" "+list.cur.next);

        }
        Node first = list.head;
        StringBuilder sb = new StringBuilder();
        while (first != null){
            sb.append(first.data);
//            System.out.print(first.data);
            first = first.next;
        }
        System.out.println(sb.toString());



    }
}
