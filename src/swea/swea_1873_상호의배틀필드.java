package swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1873_상호의배틀필드 {
    static char[][] board;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}}; // 위0 오른쪽1 아래2 왼쪽3
    static int N, tHead, H, W;
    static int[] tankXY;
    static String command;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(new File("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1873_상호의배틀필드")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            board = new char[H][W];
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
            N = Integer.parseInt(br.readLine());
            command = br.readLine();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (board[i][j] == '^'){
                        tHead = 0;
                        tankXY = new int[]{i,j};
                    }
                    if (board[i][j] == '>'){
                        tHead = 1;
                        tankXY = new int[]{i,j};
                    }
                    if (board[i][j] == 'v'){
                        tHead = 2;
                        tankXY = new int[]{i,j};
                    }
                    if (board[i][j] == '<'){
                        tHead = 3;
                        tankXY = new int[]{i,j};
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                char c = command.charAt(i);
                // 방향키
                if (c=='U' || c=='D' || c=='L' || c=='R'){
                    go(c);
                }
                else if (c=='S'){
                    shoot();
                }
            }
            sb.append("#").append(tc+1).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void go(char c){
        if (c=='U'){
            int nx = tankXY[0] + move[0][0];
            int ny = tankXY[1] + move[0][1];
            tHead = 0;
            if (0<=nx && nx<H && 0<=ny && ny<W && board[nx][ny]=='.'){
                board[tankXY[0]][tankXY[1]] = '.';
                tankXY[0] = nx;
                tankXY[1] = ny;
            }
            board[tankXY[0]][tankXY[1]] = '^';
        }
        else if (c=='R'){
            int nx = tankXY[0] + move[1][0];
            int ny = tankXY[1] + move[1][1];
            tHead = 1;
            if (0<=nx && nx<H && 0<=ny && ny<W && board[nx][ny]=='.'){
                board[tankXY[0]][tankXY[1]] = '.';
                tankXY[0] = nx;
                tankXY[1] = ny;
            }
            board[tankXY[0]][tankXY[1]] = '>';
        }
        else if (c=='D'){
            int nx = tankXY[0] + move[2][0];
            int ny = tankXY[1] + move[2][1];
            tHead = 2;
            if (0<=nx && nx<H && 0<=ny && ny<W && board[nx][ny]=='.'){
                board[tankXY[0]][tankXY[1]] = '.';
                tankXY[0] = nx;
                tankXY[1] = ny;
            }
            board[tankXY[0]][tankXY[1]] = 'v';
        }
        else if (c=='L'){
            int nx = tankXY[0] + move[3][0];
            int ny = tankXY[1] + move[3][1];
            tHead = 3;
            if (0<=nx && nx<H && 0<=ny && ny<W && board[nx][ny]=='.'){
                board[tankXY[0]][tankXY[1]] = '.';
                tankXY[0] = nx;
                tankXY[1] = ny;
            }
            board[tankXY[0]][tankXY[1]] = '<';
        }
    }
    static void shoot(){
        int dx = move[tHead][0];
        int dy = move[tHead][1];
        int nx = tankXY[0] + dx;
        int ny = tankXY[1] + dy;
        while (true){
            if (0<=nx && nx<H && 0<=ny && ny<W){ // 일단 범위 내
                if (board[nx][ny] == '*'){ // 벽돌 벽 부시고 소멸
                    board[nx][ny] = '.';
                    return;
                } else if (board[nx][ny] == '#') { // 강철 벽, 바로 소멸
                    return;
                }else { // 평지 혹은 물
                    nx += dx;
                    ny += dy;
                }
            }else { // 범위 밖, 소멸
                return;
            }
        }
    }
}
