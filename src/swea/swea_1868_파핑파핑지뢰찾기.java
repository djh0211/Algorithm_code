package swea;
import java.util.*;
import java.io.*;
public class swea_1868_파핑파핑지뢰찾기 {
    static int N;
    static char[][] board;
    static int[][] bombMap;

    static int[][] mv = {{-1,-1},{-1,0},{-1,1},
                        {0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1868_파핑파핑지뢰찾기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];
            bombMap = new int[N][N];
            // init board
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                board[i] = st.nextToken().toCharArray();
            }
            getBombMap(); // 해당 칸 주변에 몇개의 폭탄이 있는지 보여주는 지도.


            int cnt = 0;
            // 결국 0인 칸은 어디를 눌러도 연쇄적으로 방문하게 된다.
            // 8방에 지뢰없는 칸 먼저 클릭. -> 다른 칸 열리게 유도 가능
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 주변에 지뢰가 없고 현 위치가 지뢰가 아니라면
                    if(bombMap[i][j] == 0 && board[i][j] != '*') {
                        click_bfs(i, j);
//                        click_dfs(i, j);
                        cnt++;
                    }
                }
            }

            // 이제 주변에 폭탄이 있는 칸을 누르자. -> 연쇄작용이 없다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(bombMap[i][j] > 0 && board[i][j] != '*') {
                        cnt++;
                    }
                }
            }
            sb.append("#").append(t+1).append(" ").append(cnt).append("\n");

        }
        System.out.println(sb.toString());;


    }
    static void click_dfs(int i, int j){
        bombMap[i][j] = -1; // 방문 체크
        for (int[] dxy:mv
        ) {
            int nx = i + dxy[0];
            int ny = j + dxy[1];
            // 범위 내 + 아직 탐색 전인 칸이고 + 그 칸이 폭탄이 아니어야해
            if (0<=nx && nx<N && 0<=ny && ny<N && bombMap[nx][ny]!=-1 && board[nx][ny]=='.'){
                // 거기다가 그 칸도 주변에 폭탄이 없는 칸이네? -> 다시 주변칸으로 확대 가능
                if (bombMap[nx][ny]==0)
                    click_dfs(nx, ny);
            }

        }

    }
    static void click_bfs(int i, int j){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i,j}); // 클릭한 지점 큐 인
        bombMap[i][j] = -1; // 방문 체크
        int[] xy;
        while (!q.isEmpty()){
            xy = q.poll();
            // 8방 탐색
            for (int[] dxy:mv
                 ) {
                int nx = xy[0] + dxy[0];
                int ny = xy[1] + dxy[1];
                // 범위 내 + 아직 탐색 전인 칸이고 + 그 칸이 폭탄이 아니어야해
                if (0<=nx && nx<N && 0<=ny && ny<N && bombMap[nx][ny]!=-1 && board[nx][ny]=='.'){
                    // 거기다가 그 칸도 주변에 폭탄이 없는 칸이네? -> 다시 주변칸으로 확대 가능
                    if (bombMap[nx][ny]==0)
                        q.offer(new int[]{nx,ny});
                    bombMap[nx][ny] = -1; // 방문 체크
                }

            }
        }

    }

    static int showBombNum(int i, int j){
        int nx,ny;
        int cnt = 0;
        for (int[] dxy:mv
             ) {
            nx = dxy[0] + i;
            ny = dxy[1] + j;
            if (0<=nx && nx<N && 0<=ny && ny<N){
                // 주위 칸이 폭탄일 때
                if (board[nx][ny] == '*'){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    static void getBombMap(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 폭탄이 아니어서 클릭이 가능하면
                if (board[i][j] == '.'){
                    bombMap[i][j] = showBombNum(i,j);

                }
            }
        }
    }
}
