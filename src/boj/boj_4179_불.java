package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_4179_불 {
    static int R, C;
    static int[] cursor;
    static int[][] board;
    static int[][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        v = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                v[i][j] = -1;
                char c = line.charAt(j);
                if (c=='J') {
                    cursor = new int[]{i,j};
                    board[i][j] = -9;
                }
                if (c=='F') {
                    board[i][j] = 0;
                }
                if (c=='#') {
                    board[i][j] = -10;
                }
                if (c=='.') {
                    board[i][j] = -9;
                }
            }
        }
        dfs();

//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{cursor[0],cursor[1],0});
        v[cursor[0]][cursor[1]] = 0;

//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(v[i]));
//        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
//            System.out.println(cur[2]);
            if (cur[0]==0 || cur[0]==R-1 || cur[1]==0 || cur[1]==C-1) {
                System.out.println(cur[2]+1);
                return;
            }
            for (int[] dxy:move
                 ) {
                int nx = cur[0]+dxy[0];
                int ny = cur[1]+dxy[1];
                if (0<=nx && nx<R && 0<=ny && ny<C && v[nx][ny]==-1 && board[nx][ny]!=-10 && (board[nx][ny]==-9 || cur[2]+1<board[nx][ny])) {
                    v[nx][ny] = cur[2]+1;
                    q.offer(new int[]{nx,ny,cur[2]+1});
                }
            }
        }
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(v[i]));
//        }
        System.out.println("IMPOSSIBLE");
    }
    // 1일차부터 시작
    static void dfs(){
        ArrayDeque<int[]> list = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j]==0){
                    list.offer(new int[]{i,j,0});
                }
            }
        }
        while (!list.isEmpty()) {
            int[] cur = list.poll();
            for (int[] dxy:move
                 ) {
                int nx = cur[0]+dxy[0];
                int ny = cur[1]+dxy[1];
                if (0<=nx && nx<R && 0<=ny && ny<C && board[nx][ny]==-9) {
                    board[nx][ny] = cur[2]+1;
                    list.offer(new int[]{nx,ny,cur[2]+1});
                }
            }
        }
    }
}
