package swea;

import java.io.*;
import java.util.*;

class swea_7733_치즈도둑
{
    static int[][] cheese;
    static boolean[][] visited;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());
            int max = Integer.MIN_VALUE;
            int cnt = 0;
            cheese = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    cheese[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            for(int day=0; day<=100; day++) {
                cnt=0;
                visited = new boolean[N][N];
                eat(cheese, day);

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(visited[i][j]==false && cheese[i][j]!=0) {
                            cnt++;
//                            dfs(i, j);
                            bfs(i,j);
                        }
                    }
                }

                if(max<cnt)
                    max=cnt;
            }
            System.out.println("#"+test_case+" "+max);
        }
    }

    public static void eat(int[][] cheese, int day) {
        for(int i=0; i<cheese.length; i++) {
            for(int j=0; j<cheese.length; j++) {
                if(cheese[i][j]==day) {
                    cheese[i][j]=0;
                    visited[i][j]=true;
                }
            }
        }
    }
    public static void bfs(int X, int Y) {
        int []xy;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offerLast(new int[]{X, Y}); //큐에 넣고
        visited[X][Y] = true; // 방문처리
        while (!q.isEmpty()){
            xy = q.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                if(nx>=0 && nx<cheese.length && ny<cheese.length && ny>=0 && !visited[nx][ny] && cheese[nx][ny]!=0){
                    q.offerLast(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
    public static void dfs(int X, int Y) {
        int originX=X;
        int originY=Y;
        int x=0;
        int y=0;

        visited[originX][originY]=true;

        for(int i=0; i<4; i++) {
            x=originX+dx[i];
            y=originY+dy[i];

            if(x>=0 && x<cheese.length && y<cheese.length && y>=0 && !visited[x][y] && cheese[x][y]!=0)
                dfs(x, y);
        }
    }
}