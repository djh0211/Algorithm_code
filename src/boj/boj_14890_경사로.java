package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_14890_경사로 {
    static int N,L,lenL;
    static int[][] board, move={{-1,0},{0,1},{1,0},{0,-1}};
    static List<Road> list = new ArrayList<>();
    static class Road {
        int x;
        int y;
        int dx;
        int dy;

        @Override
        public String toString() {
            return "Road{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dx=" + dx +
                    ", dy=" + dy +
                    '}';
        }

        public Road(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findAllRoad();
        for (Road r:list
             ) {
            System.out.println(r);
        }
        lenL = list.size();



    }
    static void doJob(int cnt){
        
    }
    static void findAllRoad(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이 칸에서 4방향으로 확인
                for (int[] dxy:move
                     ) {
                    // 경사로 머리 위가 나보다 1커야함
                    int headX = i-dxy[0];
                    int headY = j-dxy[1];
                    if (0<= headX && headX<N && 0<=headY && headY<N &&
                    board[headX][headY]==board[i][j]+1){
                        // 그렇다면 길이검사를 해봅시다.
                        int baseH = board[i][j];
                        int nx = i;
                        int ny = j;
                        boolean status=true;
                        for (int k = 0; k < L-1; k++) {
                            nx+=dxy[0];
                            ny+=dxy[1];
                            if (0<=nx && nx<N && 0<=ny && ny<N){
                                if (board[nx][ny]!=baseH){
                                    // 높이가 달라도 못놔
                                    status=false;
                                    break;
                                }
                            } else {
                                // 보드 밖이면 못 놔
                                status=false;
                                break;
                            }
                        }
                        // 통과했으면 놓을 수 있어.
                        if (status) list.add(new Road(i,j,dxy[0],dxy[1]));
                    }
                }
            }
        }
    }
}
