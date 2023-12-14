package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14719_빗물 {
    static int H,W,g,gIdx;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        g = Arrays.stream(arr).max().getAsInt();
        for (int i = 0; i < W; i++) {
            if (arr[i]==g) {
                gIdx = i;
                break;
            }
        }
        int lg = arr[0];
        int sum = 0;
        for (int i = 0; i < gIdx; i++) {
            if (lg<arr[i]) {
                lg = arr[i];
            } else {
                sum+=(lg-arr[i]);
            }
        }
        int rg = arr[W-1];
        for (int i = W-1; i > gIdx; i--) {
            if (rg<arr[i]) {
                rg = arr[i];
            } else {
                sum+=(rg-arr[i]);
            }
        }
        System.out.println(sum);

    }
}
