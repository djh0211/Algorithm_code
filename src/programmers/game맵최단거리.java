package programmers;
import java.io.*;
import java.util.*;
public class game맵최단거리 {
    static int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
    static int[][] v;
    static int[][] mv = {{-1,0},{0,1},{1,0},{0,-1}};
    static int N,M;
    static void bfs(int i, int j){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j});
        v[i][j] = 1;
        int[] xy;
        int x,y;
        int nx,ny;
        while (!q.isEmpty()){
            xy = q.poll();
            x = xy[0];
            y = xy[1];
            for (int[] dxy:mv
                 ) {
                nx = x+dxy[0];
                ny = y+dxy[1];
                // 범위내, 벽이 아니고, 미방문
                if (0<=nx && nx<N && 0<=ny && ny<M && maps[nx][ny]!=0 && v[nx][ny]==0){
                    q.offer(new int[]{nx,ny});
                    v[nx][ny] = v[x][y]+1;
                }
            }
        }

    }
    public static void main(String[] args) throws Exception {
        N = maps.length;
        M = maps[0].length;
        v = new int[N][N];
        bfs(0,0);
        if (v[N-1][M-1]!=0){
            System.out.println(v[N-1][M-1]);
        }else{
            System.out.println(-1);
        }
    }
}
