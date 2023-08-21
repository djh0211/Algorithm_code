package boj;
import java.util.*;
import java.io.*;
public class boj_16236_아기상어 {
    static int[][] board;
    static int[] sharkXY;
    static int[][] move = {{-1,0},{0,-1},{0,1},{1,0}};
    static boolean[][] v;
    static int N,M,sharkSize=2,eatCnt=0,day;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 9){
                    // 상어 위치
                    sharkXY = new int[]{i,j};
                    board[i][j] = 0;
                }
            }
        }
        day = 0;
        System.out.println("먹고 난 이후: day="+day+" eatCnt="+eatCnt+" sharksize="+sharkSize);
        for (int[] a:board
        ) {
            System.out.println(Arrays.toString(a));
        }
        while (true){
            v = new boolean[N][N];
            // eat
            if(!eat()){
                // 먹은게 없어. 바로 종료
                break;
            }
        }
        System.out.println(day);

    }
    static boolean eat(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> cands = new ArrayList<>();
        int startDay = day;
        q.offer(new int[]{sharkXY[0],sharkXY[1],startDay});
        v[sharkXY[0]][sharkXY[1]] = true;
        int firstDay = Integer.MAX_VALUE;
        while (!q.isEmpty()){
            int[] xyDay = q.poll();
            if (board[xyDay[0]][xyDay[1]] != 0 && board[xyDay[0]][xyDay[1]] < sharkSize){
                if (firstDay==Integer.MAX_VALUE){
                    // 먹을 수 있는 조건 & 아직 몰라.
                    cands.add(new int[]{xyDay[0], xyDay[1],xyDay[2]});
                    firstDay = xyDay[2];
                }else

                // 먹을 수 있는 조건
                board[xyDay[0]][xyDay[1]] = 0;
                sharkXY[0] = xyDay[0];
                sharkXY[1] = xyDay[1];
                eatCnt++; // 냠냠
                day = xyDay[2]; // 생존 일수 갱신
                if (eatCnt==sharkSize){
                    // level up
                    eatCnt = 0;
                    sharkSize++;
                }
                System.out.println("먹고 난 이후: day="+day+" eatCnt="+eatCnt+" sharksize="+sharkSize);
                for (int[] a:board
                ) {
                    System.out.println(Arrays.toString(a));
                }
                return true;
            }
            for (int[] dxy:move
                 ) {
                int nx = xyDay[0] + dxy[0];
                int ny = xyDay[1] + dxy[1];
                if (0<=nx && nx<N && 0<=ny && ny<N && !v[nx][ny] && board[nx][ny]<=sharkSize){
                    // 지나는 갈 수 있습니다.
                    q.offer(new int[]{nx,ny,xyDay[2]+1});
                    v[nx][ny] = true;
                }
            }
        }
        return false;
    }
}
