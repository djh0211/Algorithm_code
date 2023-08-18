package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17135_캐슬디펜스 {
    static int N,M,D,killCount, result;
    static int[] b;
    static int[][] move = {{0,-1},{-1,0},{0,1}};
    static int[][] board,playgroud;
    static HashSet<String> targets;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1){
                    killCount++;
                }
            }
        }
        b = new int[3];
        comb(0,0);
        System.out.println(result);

    }
    static void enemyMove(){
        for (int i = N-1; i > 0 ; i--) {
            for (int j = 0; j < M; j++) {
                playgroud[i][j] = playgroud[i-1][j];
            }
        }
        for (int j = 0; j < M; j++) {
            playgroud[0][j] = 0;
        }
    }

    static void play(){
        setPlaygroud(); // 이 조합으로 시도하기 이전 초기화 필요
        killCount = 0; // 죽인 적의 수
        targets = new HashSet<>(); // 그 턴에 죽일 적들 위치. 매 턴마다 초기화 필요.
        StringTokenizer st;
        int x,y;
        label1:while(true){
            targets = new HashSet<>(); // 그 스테이지에 죽일 적들 위치
            for (int col:b
            ) {
                selectBFS(N,col);
            }
            for (String s:targets
                 ) {
                st = new StringTokenizer(s);
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                playgroud[x][y] = 0;
                killCount++;
            }
            enemyMove();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (playgroud[i][j]==1){
                        continue label1;
                    }
                }
            }
            break;

        }
        result = Math.max(result,killCount);


    }
    static void selectBFS(int i, int j){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i-1,j,1}); // 궁수 바로 위
        int[] xyd; // x,y,궁수와의거리
        int nx,ny;
        while (!q.isEmpty()){
            xyd = q.poll();
            if (playgroud[xyd[0]][xyd[1]] == 1){
                // 찾았다 제일 처음 적 난 너를 쏘겠어.
                targets.add(xyd[0]+" "+xyd[1]);
                return;
            }
            for (int[] dxy:move
                 ) {
                nx = xyd[0] + dxy[0];
                ny = xyd[1] + dxy[1];
                if (0<=nx && nx<N && 0<=ny && ny<M && xyd[2]+1<=D){
                    q.offer(new int[]{nx,ny,xyd[2]+1});
                }
            }
        }
    }
    static void setPlaygroud(){
        playgroud = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                playgroud[i][j] = board[i][j];
            }
        }
    }
    static void comb(int cnt, int start){
        if (cnt == 3){
            play();
            return;
        }
        for (int i = start; i < M; i++) {
            b[cnt] = i;
            comb(cnt+1, i+1);
        }
    }
}
