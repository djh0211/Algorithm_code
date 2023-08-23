package boj;
import java.util.*;
import java.io.*;

public class boj_17472_다리만들기2 {
    static int n,m;
    static int [][] board;
    static boolean [][] visit;
    static int[][] g;
    static int [][] move = {{0,1},{1,0},{0,-1},{-1,0}};
    static HashMap<String, Integer> land = new HashMap<>();
    static ArrayList<int[]> landArr = new ArrayList<>();
    static ArrayList<int[]> edges;

    static int[] parents;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visit = new boolean[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int landNum = 0;
        ArrayDeque<int[]> q;
        int[] xy;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 탐색하여 섬을 구분 짓는다.
                if (board[i][j]==1 && visit[i][j]==false){
                    q = new ArrayDeque<>();
                    q.offerLast(new int[]{i,j});
                    visit[i][j] = true;
                    land.put(i+" "+j,landNum); // 배열 넣으면 못 찾음
                    landArr.add(new int[]{i,j,landNum});
                    while (!q.isEmpty()){
                        xy = q.pollFirst();
                        int x = xy[0];
                        int y = xy[1];
                        for (int[] d:move
                        ) {
                            int nx = x+d[0];
                            int ny = y+d[1];
                            // 배열울 벗어나지 않았고 아직 미탐색이고 바다가 아닌 땅이라면
                            if (0<=nx && nx<n && 0<=ny && ny<m  && !visit[nx][ny] && board[nx][ny]==1){
                                q.offerLast(new int[]{nx,ny});
                                visit[nx][ny] = true;
                                land.put(nx+" "+ny,landNum);
                                landArr.add(new int[]{nx,ny,landNum});
                            }
                        }
                    }
                    landNum++;
                }
            }
        }

        // 다리 제작
        edges = new ArrayList<>();
        for (int[] l:landArr
        ) {
            int x = l[0];
            int y = l[1];
            int curLand = l[2];
            for (int[] d:move
            ) {
                int dist = 0;
                int nx = x + d[0];
                int ny = y + d[1];
                while (true){
                    // 배열 범위 내에 있으면
                    if (0<=nx && nx<n && 0<=ny && ny<m){
                        int toLand = land.getOrDefault(nx+" "+ny,-1); // 새로 향하는 곳이 땅이면 땅번호, 아니면 -1
                        if (curLand==toLand)
                            break; // 이동했는데도 같은 땅이면, 가장자리가 아닌것, 할 필요 없다.
                        // 이동한 곳이 바다면 다리 길이를 하나 늘린다.
                        if (toLand == -1){
                            nx+=d[0];
                            ny+=d[1];
                            dist++;
                            continue;
                        }
                        // 이동한 곳이 다른 섬인데, 다리 길이가 1이면 유효하지 않은 다리이다.
                        if (dist < 2)
                            break;
                        // 여기까지 왔으면 유효한 다리이다.
                        edges.add(new int[]{dist,curLand,toLand});
                        break;
                    }
                    else break;
                }
            }
        }
        Collections.sort(edges, ((Comparator<int[]>) (o1, o2) -> {
            return Integer.compare(o1[0], o2[0]);
        }));

//        for (int[] a:edges
//             ) {
//            System.out.println(Arrays.toString(a));
//        }
//
//        parents = new int[landNum];
//        for (int i = 0; i < landNum; i++) {
//            parents[i] = i;
//        }
//        int ans = 0;
//        int cnt = 0;
//        for (int[] e:edges
//             ) {
//            if (union(e[1],e[2])){
//                // no cycle
//                ans += e[0];
//                if (++cnt == landNum - 1){
//                    break;
//                }
//            }
//        }
//        if (cnt == landNum - 1){
//            System.out.println(ans);
//        }else {
//            System.out.println(-1);
//        }

        // Prim Algorithm
        g = new int[landNum][landNum]; // 진출 차수 그래프
        int[] minEdge = new int[landNum];
        for (int i = 0; i < landNum; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }
        boolean[] v = new boolean[landNum];
        for (int[] e:edges
             ) {
            g[e[1]][e[2]] = e[0];
        }
//        for (int[] row:g
//             ) {
//            System.out.println(Arrays.toString(row));
//        }


    }
    static int find(int i){
        if (parents[i]==i){
            return i;
        }
        return parents[i] = find(parents[i]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot){
            // cycle
            return false;
        }
        parents[bRoot] = aRoot; // level 관리 필요(밸런스 트리를 위해)
        return true;
    }
}
