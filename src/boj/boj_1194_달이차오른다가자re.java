package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_1194_달이차오른다가자re {
    static int N,M,sx,sy;
    static char[][] board;
    static boolean[][][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int result = -1;
    static class Node {
        int x;
        int y;
        int d;
        int key;

        public Node(int x, int y, int d, int key) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.key = key;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    ", key=" + key +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        v = new boolean[N][M][(1<<6)];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='0'){
                    sx = i;
                    sy = j;
                    board[i][j] = '.';
                }
            }
        }
        bfs();
        System.out.println(result);


    }
    static void bfs(){
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(sx,sy,0,0));
        v[sx][sy][0] = true;
        while (!q.isEmpty()){
            Node cur = q.poll();
            if (board[cur.x][cur.y]=='1'){
                // 도착
                result = cur.d;
                return;
            }
            for (int[] dxy:move
                 ) {
                int nx = cur.x+dxy[0];
                int ny = cur.y+dxy[1];
                // 보드 안
                if (0<=nx && nx<N && 0<=ny && ny<M && !v[nx][ny][cur.key]){
                    // 이동 가능
                    if (board[nx][ny]=='.' || board[nx][ny]=='1'){
                        q.add(new Node(nx,ny,cur.d+1, cur.key));
                        v[nx][ny][cur.key] = true;
                    } // 키일 경우
                    else if ('a'<=board[nx][ny] && board[nx][ny]<='f') {
                        int k = cur.key|(1<<(board[nx][ny]-'a'));
                        if (!v[nx][ny][k]){
                            v[nx][ny][k] = true;
                            q.add(new Node(nx,ny,cur.d+1, k));
                        }
                    } // 문일 경우
                    else if ('A'<=board[nx][ny] && board[nx][ny]<='F') {
                        if ((cur.key&(1<<(board[nx][ny]-'A')))!=0) {
                            // 진입가능
                            if (!v[nx][ny][cur.key]) {
                                v[nx][ny][cur.key] = true;
                                q.add(new Node(nx, ny, cur.d + 1, cur.key));
                            }
                        }
                    }
                }
            }
        }
    }
}
