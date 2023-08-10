package boj;

import java.util.*;
import java.io.*;

public class boj_17472_다리만들기2 {
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int [] dx = {0,1,0,-1};
    static int [] dy = {1,0,-1,0};
    static HashMap <int [],Integer> land;
    static ArrayList<int[]> landArr;
    static ArrayList<int[]> edges = new ArrayList<>();
    public class NxNy{
        int nx;
        int ny;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NxNy nxNy = (NxNy) o;
            return nx == nxNy.nx && ny == nxNy.ny;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nx, ny);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int [N][M];
        visited = new boolean [N][M];
        land = new HashMap<>(); // 위치 - 섬 번호
        landArr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for (int[] a:board
//             ) {
//            System.out.println(Arrays.toString(a));
//        }
        int landNum = 0;
        int [] temp;
        ArrayDeque<int[]> q;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !visited[i][j]){
                    q = new ArrayDeque<>();
                    q.offerLast(new int[]{i,j});
                    visited[i][j] = true;
                    land.put(new int[]{i,j}, landNum);
                    landArr.add(new int[]{i,j,landNum});
                    int nx = 0;
                    int ny = 0;
                    while (!q.isEmpty()){
                        int [] xy = q.pollFirst();
                        for (int k = 0; k < 4; k++) {
                            nx = xy[0] + dx[k];
                            ny = xy[1] + dy[k];
                            // 범위안에 있고, 미방문이고, 섬이면
                            if (0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny] && board[nx][ny] == 1){
                                q.offerLast(new int []{nx, ny});
                                visited[nx][ny] = true;
                                land.put(new int[]{nx,ny},landNum);
                                landArr.add(new int[]{nx,ny,landNum});
                            }
                        }
                    }
                    landNum++;
                }
            }
        }

        // 다리 제작

        int toLandNum = 0;
        // land = {x,y,curLandNum}
        for (int i = 0 ; i<land.size() ; i++) {
            int x = landArr.get(i)[0];
            int y = landArr.get(i)[1];
            int curLandNum = landArr.get(i)[2];
            for (int j = 0; j < 4; j++) {
                int dist = 0;
                int nx = x + dx[j];
                int ny = y + dy[j];
                while (true){
                    // 범위 안에 있을때
                    if (0<=nx && nx<N && 0<=ny && ny<N){
                        toLandNum = land.getOrDefault(new int[] {nx,ny}, -1);
                        // 같은 넘버의 섬이면 현재 위치가 섬의 테두리가 아님. 할 필요 없음
                        if (curLandNum == toLandNum){
                            break;
                        }
                        // 바다 위이면, 다리를 하나 놓읍시다. 다리 길이 + 1 하고 같은 방향으로 1 전진
                        if (toLandNum == -1){
                            nx += dx[j];
                            ny += dy[j];
                            dist++;
                            continue;
                        }
                        // 다른 넘버의 섬인데 다리 길이가 1 이하야. 이 다리 무효야.
                        if (dist < 2){
                            break;
                        }
                        // 여기에 도달한거면 다리 길이도 충분하고 다리를 설치하며 와보니 다른 넘버의 섬이야
                        // 즉 유효한 다리이다.
                        edges.add(new int[]{dist, curLandNum, toLandNum});
                        break;
                    }
                    // 범위 안에도 아니면
                    else{
                        break;
                    }
                }
            }
        }
        System.out.println("hihi"+edges);

        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]){
                    if (o1[1] == o2[1]){
                        return Integer.compare(o1[2], o2[2]);
                    }
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });
        for (int[] e:edges
        ) {
            System.out.println(Arrays.toString(e));
        }


    }
}
