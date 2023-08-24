package boj;
import java.util.*;
import java.io.*;

/*
문제 풀이 단계
1. edge를 구하는 과정
2. MST 구하는 과정(Kruskal/Prim/PrimPQ)

1. edge를 구하는 과정
-----------------------------
1-1 필요 핵심 자료구조
    -> land : HashMap<String : "x y"형태, Integer : 섬의 번호>
    -> landArr : ArrayList<int[] : {x,y,섬의 번호}>
    -> edges : ArrayList<int[] : {다리 길이,from 섬의 번호,to 섬의 번호}>
1-2 진행과정
    1. for i for j 돌며 해당 위치가 미방문한 땅(바다x)인 경우 사방 탐색(bfs) 하며 시작 위치와 같은 섬을 land와 landArr에 담는다.
    2. 그렇게 담긴 landArr(바다가 아닌 모든 땅이 담긴 배열)을 for-each로 돌며 위로 쭉-, 아래로 쭉-, 좌우로 쭉- 이동하며 다리 성립조건을 만족하는 다리들을 edge에 담는다.

2. MST 구하는 과정(Kruskal/Prim/PrimPQ)
-----------------------------
2-1 Kruskal
edge 배열을 weight 오름차순 정렬 하고 하나씩 담으면서 사이클 검사
2-2 Prim/PrimPQ
edge 배열을 진출 차수 인접 행렬의 형태로 변경(최대 10*10배열이라 공간복잡도 크게 영향 없을 듯)
이후 해당 알고리즘 진행
 */



public class boj_17472_다리만들기2 {
    static int n,m;
    static int [][] board; // 지도 배열
    static boolean [][] visit;
    static int [][] move = {{0,1},{1,0},{0,-1},{-1,0}}; // 사방탐색 방향키
    static HashMap<String, Integer> land = new HashMap<>();
    static ArrayList<int[]> landArr = new ArrayList<>();
    static ArrayList<int[]> edges;

    static int[] parents;

    public static void main(String[] args) throws Exception{
        // 입출력 관련
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

//        1. for i for j 돌며 해당 위치가 미방문한 땅(바다x)인 경우 사방 탐색(bfs) 하며 시작 위치와 같은 섬을 land와 landArr에 담는다.
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
                    land.put(i+" "+j,landNum); // 클래스로 만들고 isEqual과 hashCode 오버라이딩해서 x좌표와 y좌표가 같으면 같은 객체로 인식하게 해주어야한다.
                    landArr.add(new int[]{i,j,landNum});
                    while (!q.isEmpty()){
                        xy = q.pollFirst();
                        int x = xy[0];
                        int y = xy[1];
                        for (int[] d:move
                        ) {
                            int nx = x+d[0];
                            int ny = y+d[1];
                            // 배열을 벗어나지 않았고 아직 미탐색이고 바다가 아닌 땅이라면 -> 같은 번호의 땅이라는 것
                            if (0<=nx && nx<n && 0<=ny && ny<m  && !visit[nx][ny] && board[nx][ny]==1){
                                q.offerLast(new int[]{nx,ny});
                                visit[nx][ny] = true;
                                land.put(nx+" "+ny,landNum);
                                landArr.add(new int[]{nx,ny,landNum});
                            }
                        }
                    }
                    landNum++; // 다음 섬의 번호. 모든 for문이 끝나면 총 섬의 개수와 같은 값을 갖게 된다.
                }
            }
        }

//        2. 그렇게 담긴 landArr(바다가 아닌 모든 땅이 담긴 배열)을 for-each로 돌며 위로 쭉-, 아래로 쭉-, 좌우로 쭉- 이동하며 다리 성립조건을 만족하는 다리들을 edge에 담는다.
        edges = new ArrayList<>();
        for (int[] l:landArr
        ) {
            int x = l[0]; // 땅의 x좌표
            int y = l[1]; // 땅의 y좌표
            int curLand = l[2]; // 땅의 섬 번호
//            위로 쭉-, 아래로 쭉-, 좌우로 쭉- 이동
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

//        2-1 Kruskal
//           edge 배열을 weight 오름차순 정렬 하고 하나씩 담으면서 사이클 검사
        Collections.sort(edges, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        // make-set
        parents = new int[landNum];
        for (int i = 0; i < landNum; i++) {
            parents[i] = i;
        }
        // union-find
        int ans = 0;
        int cnt = 0;
        for (int[] e:edges
             ) {
            if (union(e[1],e[2])){
                // no cycle
                ans += e[0];
                if (++cnt == landNum - 1){
                    break;
                }
            }
        }
        if (cnt == landNum - 1){
            System.out.println(ans);
        }else {
            System.out.println(-1);
        }

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
