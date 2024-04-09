package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_13913_숨바꼭질4 {
    static int n,k;
    static int l = 100002;
    static int[] v = new int[l];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n==k) {
            System.out.printf("0\n%d",n);
            return;
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{n,0});
        v[n] = n;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int cnt = cur[1];
            if (x==k) {
                int now = k;
                StringBuilder sb = new StringBuilder();
                sb.append(cnt).append("\n");
                int[] route = new int[cnt+1];
                for (int i = cnt; i >=0 ; i--) {
                    route[i] = now;
                    now = v[now];
                }
                for (int i = 0; i <= cnt; i++) {
                    sb.append(route[i]).append(" ");
                }
                System.out.println(sb.toString());
                return;
            }
            if (0<=x-1 && x-1<l && v[x-1]==0) {
                int nx = x-1;
                q.offer(new int[]{nx,cnt+1});
                v[nx] = x;
            }
            if (0<=x+1 && x+1<l && v[x+1]==0) {
                int nx = x+1;
                q.offer(new int[]{nx,cnt+1});
                v[nx] = x;
            }
            if (0<=2*x && 2*x<l && v[2*x]==0) {
                int nx = 2*x;
                q.offer(new int[]{nx,cnt+1});
                v[nx] = x;
            }
        }
    }
}
