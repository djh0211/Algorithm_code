package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9466_텀프로젝트 {
    static int n;
    static int[] outs;
    static int[] ins;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ins = new int[n+1];
            outs = new int[n+1];
            for (int j = 1; j <= n; j++) {
                int t = Integer.parseInt(st.nextToken());
                ins[t]++;
                outs[j] = t;
            }

            List<Integer> list = topSort();
            sb.append(list.size()).append("\n");
        }
        System.out.println(sb.toString());
    }
    static List<Integer> topSort() {
        List<Integer> list = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (ins[i]==0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            list.add(cur);
            if (--ins[outs[cur]]==0) {
                q.offer(outs[cur]);
            }
        }
        return list;
    }
}
