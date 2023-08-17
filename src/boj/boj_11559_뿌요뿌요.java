package boj;

import java.io.*;
import java.util.*;

public class boj_11559_뿌요뿌요 {
    static char[][] board;
    static boolean [][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        // 여기까지 입출력
        int cnt = 0; // 답
        while (true){
            list = new ArrayList<>(); // 한 시점에서 터질 수 있는 모든 뿌요들을 담은 리스트. 비어있으면 터질 수 있는것이 없으므로 종료 조건
            v = new boolean[12][6]; // 방문 여부 배열. 매 시점마다 board가 바뀌기 때문에 같이 초기화 필수
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j]!='.'){
                        bfs(i,j,board[i][j]); // 뿌요가 있으면 탐사
                    }
                }
            }
            if (list.isEmpty()){ // 비어있으면 터질 수 있는것이 없으므로 종료 조건
                break;
            }
            for (int[] xy:list // 비어있지 않으면 뿌요를 없애고 그 자리를 '.'으로 바꾼다.
                 ) {
                board[xy[0]][xy[1]] = '.';
            }
            clear(); // 뿌요 터트렸으니 바닥으로 밀어야된다.
            cnt++; // 연쇄작용 +1
        }
        System.out.println(cnt); // 답 출력
    }
    static void bfs(int i,int j, char label){
        ArrayList<int[]> tempList = new ArrayList<>(); // 모여있는 같은 글자 뿌요가 4개이상이어야지 터뜨릴 수 있으므로 큐에 넣을 때 여기에 같이 넣는다.
        tempList.add(new int[]{i,j}); // 초기 진입 뿌요 삽입
        ArrayDeque<int[]> q = new ArrayDeque<>(); // bfs 용 큐
        q.offer(new int[]{i,j}); // bfs 용 큐에 초기 진입 뿌요 삽입
        v[i][j] = true; // 방문 처리
        int[] xy;
        int nx,ny;
        while (!q.isEmpty()){
            xy = q.poll();
            for (int[] dxy:move
                 ) {
                nx = xy[0] + dxy[0];
                ny = xy[1] + dxy[1];
                if (0<=nx && nx<12 && 0<=ny && ny<6){ // 배열 범위 내
                    if (board[nx][ny]==label && !v[nx][ny]){ // 미탐사 + 같은 글자
                        v[nx][ny] = true; // 방문처리
                        q.offer(new int[]{nx,ny}); // 큐에 넣는다.
                        tempList.add(new int[]{nx,ny}); // tempList에 넣는다.
                    }
                }
            }
        }
        if (tempList.size() >= 4){ // 사이즈가 4이상이면 터뜨릴 수 있으니 전역변수 list에 추가한다.
            for (int[] temp:tempList
                 ) {
                list.add(new int[]{temp[0],temp[1]});
            }
        }
        // 아닌 경우는 걍 생략
    }
    static void clear(){ // 맨아랫줄이 비지 않게 정리하자
        StringBuilder sb;
        for (int i = 0; i < 6; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < 12; j++) {
                if (board[j][i]!='.'){
                    sb.append(board[j][i]); // 한 열에 뿌요들만 문자열로 구성
                }
            }
            int len = sb.toString().length();
            for (int j = 0; j < 12 - len; j++) { // 위에 부분은 '.'으로 넣고
                board[j][i] = '.';
            }
            for (int j = 0; j < len; j++) { // 아래쪽에 뿌요들을 모아넣자
                board[12 - len + j][i] = sb.toString().charAt(j);
            }
        }
    }
}
