package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_16234_인구이동 {
    static int N,L,R;
    static int[][] board;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static boolean[][] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int day = 0;
        while (true){
            v = new boolean[N][N];
            int flag = 0;
            // bfs 진행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j]){
                        flag += bfs(i,j);
                    }
                }
            }
            // 이동 없으면 끝
            if (flag==0){
                break;
            }
            // 이동 있으면 +1
            day++;
        }
        System.out.println(day);


    }
    static int bfs(int i, int j){
        HashSet<String> un = new HashSet<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j});
        un.add(i+" "+j);
        v[i][j] = true;
        int[] xy;
        int nx,ny;
        while (!q.isEmpty()){
            xy = q.poll();
            for (int[] dxy:move
            ) {
                nx = xy[0]+dxy[0];
                ny = xy[1]+dxy[1];
                if (0<=nx && nx<N && 0<=ny && ny<N && !v[nx][ny]){
                    int diff = Math.abs(board[nx][ny] - board[xy[0]][xy[1]]);
                    if (L<=diff && diff<=R){
                        q.offer(new int[]{nx,ny});
                        v[nx][ny] = true;
                        un.add(nx+" "+ny);
                    }
                }
            }
        }

        if (un.size() == 1){
            return 0;
        }
        int sum = 0;
        StringTokenizer st;
        for (String XY:un
             ) {
            st = new StringTokenizer(XY);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sum += board[x][y];
        }
        sum = sum/un.size();
        for (String XY:un
        ) {
            st = new StringTokenizer(XY);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = sum;
        }
        return 1;

    }
}
