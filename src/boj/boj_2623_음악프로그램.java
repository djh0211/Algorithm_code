package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2623_음악프로그램 {
    static int N,M;
    static int[] in;
    static HashSet<Integer> set = new HashSet<>();
    static ArrayList<Integer> list = new ArrayList<>();
    static List<Integer>[] g;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N+1];
        g = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < Integer.parseInt(st.nextToken()); j++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
            for (int j = 0; j < temp.size()-1; j++) {
                int from = temp.get(j);
                int to = temp.get(j+1);
                in[to]++;
                g[from].add(to);
            }
        }
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(g));

        top();
        for (int i = 1; i <= N; i++) {
            if (!set.contains(i)){
                list.add(i);
            }
        }
        for (int a:list
             ) {
            System.out.println(a);
        }

    }
    static void top(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in[i]==0){
                q.offer(i);
            }
        }
        while (!q.isEmpty()){
            int cur = q.poll();
            set.add(cur);
            list.add(cur);
            for (int to:g[cur]
                 ) {
                if(--in[to]==0){
                    q.offer(to);
                };
            }
        }
    }
}
