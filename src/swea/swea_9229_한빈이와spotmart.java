package swea;
import java.util.*;
import java.io.*;
public class swea_9229_한빈이와spotmart {
    static int N,M;
    static int max = Integer.MIN_VALUE;
    static int[] arr;
    static int[] b;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_9229_한빈이와spotmart.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            max = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            b = new int[2];
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0);
            if (max < 0)
                max = -1;
            sb.append("#").append(t+1+" ").append(max).append("\n");
//            System.out.println();
        }
        System.out.println(sb.toString());
    }
    static void comb(int cnt, int start){
        if (cnt == 2){
//            System.out.println(Arrays.toString(b));
            if (Arrays.stream(b).sum() <= M){
                max = Math.max(Arrays.stream(b).sum(),max);
            }
            return;
        }
        for (int i = start; i < N; i++) {
            b[cnt] = arr[i];
            comb(cnt + 1, i + 1);
        }
    }
}
