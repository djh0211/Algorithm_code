package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_9205_맥주마시면서걸어가기 {
    static int N;
    static int[] home,stage,bb;
    static int[][] nodes;
    static int[][] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            nodes = new int[N+2][];
            g = new int[N+2][N+2];
            for (int i = 0; i < N+2; i++) {
                for (int j = 0; j < N+2; j++) {
                    if (i==j) g[i][j]=0;
                    else g[i][j]=200;
                }
            }
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            home = new int[]{a,b};
            bb = new int[2];
            nodes[0] = new int[]{a,b};

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                nodes[i] = new int[]{a,b};
            }

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            stage = new int[]{a,b};
            nodes[N+1] = new int[]{a,b};


//            System.out.println(Arrays.toString(home));
//            System.out.println(Arrays.deepToString(nodes));
//            System.out.println(Arrays.toString(stage));

            getEdges(0,0);
//            System.out.println();
//            for (int i = 0; i < N+2; i++) {
//                System.out.println(Arrays.toString(g[i]));
//            }
//            System.out.println();

            for (int k = 0; k < N+2; k++) {
                for (int i = 0; i < N+2; i++) {
                    if (k==i) continue;
                    for (int j = 0; j < N+2; j++) {
                        if (i==j || k==j) continue;
                        g[i][j] = Math.min(g[i][k]+g[k][j], g[i][j]);
                    }
                }
            }

            if (g[0][N+1]!=200) sb.append("happy\n");
            else sb.append("sad\n");



            // todo


//            break;
        }
        System.out.println(sb.toString());
    }
    static void getEdges(int cnt, int start){
        if (cnt==2){
//            System.out.println(Arrays.toString(bb));
            int d = Math.abs(nodes[bb[0]][0]-nodes[bb[1]][0]) + Math.abs(nodes[bb[0]][1]-nodes[bb[1]][1]);
            if (d<=1000) {
                g[bb[0]][bb[1]] = g[bb[1]][bb[0]] = 1;
            }
            return;
        }
        for (int i = start; i < nodes.length; i++) {
            bb[cnt] = i;
            getEdges(cnt+1, i+1);
        }
    }
}
