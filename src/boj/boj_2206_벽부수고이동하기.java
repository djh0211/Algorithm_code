package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_2206_벽부수고이동하기 {
    static int n,m;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] board;
    static int[][][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        v = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs());

    }
    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0}); // x,y
        v[0][0][0] = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0]==n-1 && cur[1]==m-1) {
                return v[cur[0]][cur[1]][cur[2]];
            }
            for (int[] dxy:move
                 ) {
                int nx = cur[0] + dxy[0];
                int ny = cur[1] + dxy[1];
                int d = cur[2];
                // 새로운칸이 빈칸인 경우 진출
                if (0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]==0 && v[nx][ny][d]==0) {
                    q.offer(new int[]{nx,ny,d});
                    v[nx][ny][d] = v[cur[0]][cur[1]][d]+1;
                    continue;
                }
                // 막힌 칸이고 아직 안 부셨으면 진출
                if (0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]==1 && d==0 && v[nx][ny][d]==0) {
                    q.offer(new int[]{nx,ny,d+1});
                    v[nx][ny][d+1] = v[cur[0]][cur[1]][d]+1;
                }
            }
        }
        return -1;
    }
}
