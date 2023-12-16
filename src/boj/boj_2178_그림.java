package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2178_그림 {
    static int n,m;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static boolean[][] v;
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(Arrays.deepToString(board));

        System.out.println(bfs());


    }
    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,1});
        v[0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0]==n-1 && cur[1]==m-1) {
                return cur[2];
            }
            for (int[] dxy : move
                 ) {
                int nx = cur[0] + dxy[0];
                int ny = cur[1] + dxy[1];
                if (0<=nx && nx<n && 0<=ny && ny<m && board[nx][ny]==1 && !v[nx][ny]){
                    q.offer(new int[]{nx,ny,cur[2]+1});
                    v[nx][ny] = true;
                }
            }
        }
        return 0;
    }
}
