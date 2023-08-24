package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
2. MST 구하는 과정(Kruskal/Prim/PrimPQ)
        -----------------------------
        2-1 Kruskal
        edge 배열을 weight 오름차순 정렬 하고 하나씩 담으면서 사이클 검사
        2-2 Prim/PrimPQ
        edge 배열을 진출 차수 인접 행렬의 형태로 변경(최대 10*10배열이라 공간복잡도 크게 영향 없을 듯)
        이후 해당 알고리즘 진행
*/
public class boj_17472_다리만들기2_Prim {
    static int n,m;
    static int [][] board; // 지도 배열
    static boolean [][] visit;
    static int[][] g;
    static int [][] move = {{0,1},{1,0},{0,-1},{-1,0}}; // 사방탐색 방향키
    static HashMap<String, Integer> land = new HashMap<>();
    static ArrayList<int[]> landArr = new ArrayList<>();
    static ArrayList<int[]> edges;


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

//        2-2 Prim/PrimPQ
//        edge 배열을 진출 차수 인접 행렬의 형태로 변경(최대 10*10배열이라 공간복잡도 크게 영향 없을 듯)
//        이후 해당 알고리즘 진행
        Collections.sort(edges, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        // Prim Algorithm
        g = new int[landNum][landNum]; // 진출 차수 그래프
        boolean[] v = new boolean[landNum];
        int[] minEdge = new int[landNum];
        for (int i = 0; i < landNum; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }
        // edges -> 진출차수 인접행렬화
        for (int[] e:edges
             ) {
            // from 과 to가 같지만 가중치가 다른 경우들이 존재. 따라서 minWeight를 저장
            if (g[e[1]][e[2]] == 0){
                g[e[1]][e[2]] = e[0];
            }else {
                g[e[1]][e[2]] = Math.min(g[e[1]][e[2]], e[0]);
            }
        }
        int result = 0;
        int cnt = 0;
        minEdge[0]=0; // 초기 시작 노드
        for (int i = 0; i < landNum; i++) {
            int minVertex = -1;
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < landNum; j++) {
                if (!v[j] && min>minEdge[j]){ // 아직 해당 노드 미탐색 + 해당 시점에서 갈 수 있는 최소거리 노드를 찾는 과정
                    minVertex=j; // for j 가 다 돌면 해당 변수는 가장 가까운 노드를 담는다.
                    min=minEdge[j]; // 다 돌면 해당 변수는 가장 가까운 노드까지의 가중치를 담는다.
                }
            }
            if (minVertex == -1){ // 만들어진 다리가 애초에 없거나, 위의 for문을 돌면서 갱신이 한번도 안되면 minVertex는 -1
                // ArrayIndexOutOfBoundsException 방지
                System.out.println(-1);
                return;
            }
            v[minVertex]=true; // 방문!
            result+=min; // 가중치 더하기!
            if (cnt++==landNum-1) break; // N-1개의 간선 -> MST
            for (int j = 0; j < landNum; j++) { // 다음 시점을 위한 세팅. 방금 추가된 노드에서 진출하는 간선 중에 이미 세팅된 minEdge보다 더 작은 값이 있으면 추가.
                if (!v[j] && g[minVertex][j]!=0 && minEdge[j]>g[minVertex][j]){
                    minEdge[j] = g[minVertex][j];
                }
            }
        }
        // cnt++이기 때문에 
        if (cnt==landNum)
            System.out.println(result);
        else System.out.println(-1);
    }
}
