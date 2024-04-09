package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_1600_말이되고픈원숭이 {
    static int k,w,h;
    static int[][] board;
    static boolean[][][] v;
    static int[][] hmove = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        v = new boolean[h][w][k+1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

    }
    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0,0}); // x,y,cnt,hmcnt
        v[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] cur =q.poll();
            if (cur[0]==h-1 && cur[1]==w-1) {
                System.out.println(cur[2]);
                return;
            }
            for (int[] dxy:move
                 ) {
                int nx = cur[0] + dxy[0];
                int ny = cur[1] + dxy[1];
                if (0<=nx && nx<h && 0<=ny && ny<w && board[nx][ny]==0 && !v[nx][ny][cur[3]]) {
                    q.offer(new int[]{nx,ny,cur[2]+1,cur[3]});
                    v[nx][ny][cur[3]] = true;
                }
            }
            if (cur[3]<k) {
                for (int[] dxy:hmove
                ) {
                    int nx = cur[0] + dxy[0];
                    int ny = cur[1] + dxy[1];
                    if (0<=nx && nx<h && 0<=ny && ny<w && board[nx][ny]==0 && !v[nx][ny][cur[3]+1]) {
                        q.offer(new int[]{nx,ny,cur[2]+1,cur[3]+1});
                        v[nx][ny][cur[3]+1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
        return;
    }
}
