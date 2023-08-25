package swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_2382_미생물격리 {
    static int[][] move = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
    static int[][] board;
    static Virus[] vList;
    static class Virus{
        int x;
        int y;
        int numVirus; // 미생물 수
        int head; // 방향

        public Virus(int x, int y, int numVirus, int head) {
            this.x = x;
            this.y = y;
            this.numVirus = numVirus;
            this.head = head;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "x=" + x +
                    ", y=" + y +
                    ", numVirus=" + numVirus +
                    ", head=" + head +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream(new File("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_2382_미생물격리.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = 0;
                }
            }
            vList = new Virus[K + 1]; // 0은 안씁니다.
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int numVirus = Integer.parseInt(st.nextToken());
                int head = Integer.parseInt(st.nextToken()) - 1;
                board[x][y] = i; // 군집넘버
                vList[i] = new Virus(x,y,numVirus, head);
            }
//            for (int i = 1; i < vList.length; i++) {
//                System.out.println(vList[i]);
//            }



            break;
        }

    }
    static void move(int clusterNum){
//        int head = vList[clusterNum].head;
//        int nx = vList[clusterNum].x + i;
//        int ny = vList[clusterNum].y + j;
//        int  = vList[clusterNum].numVirus;

    }
}
