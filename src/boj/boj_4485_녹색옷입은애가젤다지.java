package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485_녹색옷입은애가젤다지 {
    static int N;
    static int[][] board, move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] distance;
    static boolean[][] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc=1;
        label1:while (true){
            N = Integer.parseInt(br.readLine());
            if (N==0) break label1;
            board = new int[N][N];
            distance = new int[N][N];
            v = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
//            System.out.println(Arrays.deepToString(board));

            int sX=0, sY=0, eX=N-1,eY=N-1;
            distance[0][0]=board[0][0];
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2],o2[2]));
            pq.offer(new int[]{sX,sY,distance[0][0]});
            while (!pq.isEmpty()){
                int[] cur = pq.poll();
                int curX = cur[0];
                int curY = cur[1];
                int min = cur[2];
                if (v[curX][curY]) continue;
                v[curX][curY] = true;
                if (curX==eX && curY==eY) break;
                for (int[] dxy:move
                     ) {
                    int nx = curX+dxy[0];
                    int ny = curY+dxy[1];
                    if (0<=nx && nx<N && 0<=ny && ny<N &&
                    !v[nx][ny] && min+board[nx][ny]<distance[nx][ny]){
                        distance[nx][ny] = min+board[nx][ny];
                        pq.offer(new int[]{nx,ny,distance[nx][ny]});
                    }
                }
            }
            sb.append("Problem ").append(tc++).append(": ").append(distance[eX][eY]).append("\n");
        }
        System.out.println(sb.toString());

    }
}
