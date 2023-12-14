package boj;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3055_탈출 {
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static int R,C;
    static char[][] board;
    static int[][] waterMap, v;
    static int[] S,D;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        waterMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j]=='S'){
                    // 고슴도치 초기 위치 담고, 보드에 .으로 바꿔
                    S = new int[]{i,j};
                    board[i][j] = '.';
                }
                if (board[i][j]=='D'){
                    // 고슴도치 초기 위치 담고, 보드에 .으로 바꿔
                    D = new int[]{i,j};
                }
                waterMap[i][j] = 100; //  모든 칸을 일단 -1로
                // 나중에는 0부터 물이차는 날짜를 의미. 즉 -1은 돌이나 굴을 의미하게될것이다.
            }
        }
//        System.out.println(Arrays.deepToString(board));

        getWaterMap();
        bfs();

        if (v[D[0]][D[1]]!=-1){
            System.out.println(v[D[0]][D[1]]);
        } else {
            System.out.println("KAKTUS");
        }

//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(waterMap[i]));
//        }


    }
    // 고슴도치는 v가 미방문(-1)이고 보드가 '. or D'이어야 움직일수있다.
    static void bfs(){
        v = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                v[i][j] = -1;
            }
        }
        int x = S[0];
        int y = S[1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y,0});
        v[x][y] = 0;
        while (!q.isEmpty()){
            int[] cur = q.poll();
            for (int[] dxy:move
                 ) {
                int nx = cur[0]+dxy[0];
                int ny = cur[1]+dxy[1];
                // 이 칸이 왔던 칸이 아니고 갈 수 있는 칸인지 확인
                // 워터맵과의 관계
                if (0<=nx && nx<R && 0<=ny && ny<C &&
                v[nx][ny]==-1){
                    if (board[nx][ny]=='D'){
                        // 도착
                        v[nx][ny]=cur[2]+1;

                    }
                    else if (board[nx][ny]=='.' && cur[2]+1<waterMap[nx][ny]){
                        v[nx][ny]=cur[2]+1;
                        q.offer(new int[]{nx,ny,cur[2]+1});
                    }
                }
            }
        }

    }

    // 따라서 watermap이 -1이면 초기화가 안됨을 의미
    // 물이 차는 조건은, watermap이 -1이고 board가 '.'인 경우이다.
    static int getWaterMap(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j]=='*'){
                    // 초기 물 담자
                    waterMap[i][j] = 0;
                    q.offer(new int[]{i,j}); // x,y,물이차는날짜
                }
            }
        }
        if (q.isEmpty()){
            return 0;
        }
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] dxy:move
                 ) {
                int nx = x+dxy[0];
                int ny = y+dxy[1];
                // 새로운 칸이 물이 찰 수 있는 칸인지 확인
                if (0<=nx && nx<R && 0<=ny && ny<C &&
                waterMap[nx][ny]==100 && board[nx][ny]=='.'){
                    // 그러면 확장 후 워터맵갱신
                    waterMap[nx][ny] = waterMap[x][y]+1;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
        return 1;
    }
}