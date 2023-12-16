package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1926_그림 {
    static int n, m, max=0, res=0;
    static int[][] board;
    static boolean[][] v;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로
        board = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && v[i][j] == false){
                    bfs(i, j);
                }
            }
        }

        System.out.println(res);
        System.out.println(max);





    }
    static void bfs(int i, int j) {
        res++;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j});
        v[i][j] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dxy : move
            ) {
                int nx = cur[0] + dxy[0];
                int ny = cur[1] + dxy[1];
                if (0<=nx && nx<n && 0<=ny && ny<m && !v[nx][ny] && board[nx][ny]==1){
                    cnt++;
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = true;
                }

            }
        }
        max = Math.max(max, cnt);
    }
}
