package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17472_다리만들기2_re {
    static int N,M;
    static int[][] board;
    static int[] p;
    static List<int[]> edges = new ArrayList<>();
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    static List<Land> landList = new ArrayList<>();
    static HashMap<String, Integer> landDic = new HashMap<>();
    static class Land {
        int x;
        int y;
        int landNum;

        public Land(int x, int y, int landNum) {
            this.x = x;
            this.y = y;
            this.landNum = landNum;
        }

        @Override
        public String toString() {
            return "Land{" +
                    "x=" + x +
                    ", y=" + y +
                    ", landNum=" + landNum +
                    '}';
        }

    }
    static int find(int a){
        if (p[a]==a){
            return a;
        }
        return p[a]=find(p[a]);
    }
    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if (pa==pb) return false;
        p[a] = pb;
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        Boolean[][] v = new Boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                v[i][j] = false;
            }
        }
//        System.out.println(Arrays.deepToString(board));

        int landCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==1 && !v[i][j]){
                    // 땅이고 아직 미방문이면 bfs해
                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i,j});
                    landList.add(new Land(i,j,landCnt));
                    landDic.put(i+" "+j, landCnt);
                    v[i][j] = true;
                    while (!q.isEmpty()){
                        int[] cur = q.poll();
                        for (int[] dxy:move
                             ) {
                            int nx = cur[0]+dxy[0];
                            int ny = cur[1]+dxy[1];
                            if (0<=nx && nx<N && 0<=ny && ny<M
                            && !v[nx][ny] && board[nx][ny]==1){
                                landList.add(new Land(nx,ny,landCnt));
                                landDic.put(nx+" "+ny, landCnt);
                                v[nx][ny] = true;
                                q.offer(new int[]{nx, ny});
                            }
                        }
                    }
                    landCnt++;
                }
            }
        }

//        for (Land l:landList
//             ) {
//            System.out.println(l);
//        }
//        System.out.println(landDic);

        for (Land curLand:landList
             ) {
            for (int[] dxy:move
                 ) {
                int nx = curLand.x+dxy[0];
                int ny = curLand.y+dxy[1];
                int bLen = 0;
                while (true){
                    // 일단 지도안인지부터
                    if (0<=nx && nx<N && 0<=ny && ny<M){
                        // 새로 이동한 칸이 바다라면 다리길이+1
                        if (!landDic.containsKey(nx+" "+ny)){
                            bLen++;
                            nx+= dxy[0];
                            ny+= dxy[1];
                            continue;
                        }
                        // 새로 이동한 칸이 같은 대륙이면 stop
                        if (curLand.landNum == landDic.get(nx+" "+ny)){
                            break;
                        }
                        // 이것도 아니라면 다른 대륙이라는 것
                        // 근데 다리길이가 2도 안되네?
                        if (bLen<2){
                            break;
                        }
                        // 2 넘으면 다리 등록
                        edges.add(new int[]{curLand.landNum,
                                landDic.get(nx+" "+ny),
                                bLen});
                        break;
                    } else {
                        // 아니면 종료
                        break;
                    }
                }
            }
        }

        Collections.sort(edges, (o1,o2)->Integer.compare(o1[2], o2[2]));

        p = new int[landCnt];
        for (int i = 0; i < landCnt; i++) {
            p[i] = i;
        }
        int result = 0;
        int cnt = 0;
        for (int[] e:edges
             ) {
            if (union(e[0], e[1])){
                // 가능
                result += e[2];
                if (++cnt==landCnt-1){
                    // 종료
                    break;
                }

            }
        }
        if (cnt==landCnt-1) System.out.println(result);
        else System.out.println(-1);


//        for (int[] l:edges
//             ) {
//            System.out.println(Arrays.toString(l));
//        }


    }
}
