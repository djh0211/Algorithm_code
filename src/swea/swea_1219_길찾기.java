package swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class swea_1219_길찾기 {
    static int N;
    static boolean[] v;
    // 인접 리스트
    static List[] g;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream(new File("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1219_길찾기.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            tc = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            g = new List[100];
            v = new boolean[100];
            for (int i = 0; i < 100; i++) {
                g[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                g[from].add(to);
            }
            int res = bfs(0);
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int bfs(int start){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v[start] = true;
        int cur;
        while (!q.isEmpty()){
            cur = q.poll();
            if (cur == 99){
                return 1;
            }
            for (Object to:g[cur]
                 ) {
                if (!v[(int)to]){
                    q.offer((int)to);
                    v[(int)to] = true;
                }
            }
        }
        return 0;
    }
}
