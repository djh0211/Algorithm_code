package algoLive;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class boj_15649_n과m {
    public static int n,m;
    public static int [] a;
    public static boolean [] v;
    public static int [] b;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n];
        b = new int[m];
        v = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }

        perm(0);

    }
    public static void perm(int cnt){
        if (cnt == m){
            for (int i:b
                 ) {
                System.out.print(i+" ");
            }
            System.out.println();
            return;
        }else {
            for (int i = 0; i < n; i++) {
                // 이미 넣어버렸네?
                if (v[i]) continue;
                // 그게 아니면
                b[cnt] = a[i];
                v[i] = true;
                perm(cnt + 1);
                v[i] = false;
            }
        }
    }
}
