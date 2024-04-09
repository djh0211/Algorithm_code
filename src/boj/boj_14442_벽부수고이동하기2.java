package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_14442_벽부수고이동하기2 {
    static int n,m,k;
    static int[][] board;
    static boolean[][][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        v = new boolean[n][m][k+1];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j)-'0';
            }
        }
        bfs();


    }
    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,1,0}); // x,y,cnt,dim
        v[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0]==n-1 && cur[1]==m-1) {
                System.out.println(cur[2]);
                return;
            }
            for (int[] dxy:move
                 ) {
                int nx = cur[0]+dxy[0];
                int ny = cur[1]+dxy[1];
                if (0<=nx && nx<n && 0<=ny && ny<m) {
                    if (board[nx][ny]==0 && !v[nx][ny][cur[3]]) {
                        q.offer(new int[]{nx,ny,cur[2]+1,cur[3]});
                        v[nx][ny][cur[3]] = true;
                    }
                    if (board[nx][ny]==1 && cur[3]<k && !v[nx][ny][cur[3]+1]) {
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
