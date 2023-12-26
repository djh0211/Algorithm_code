package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17298_오큰수 {
    static int[] NGE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        NGE = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int A = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                // 비어있으면 넣어버려
                stack.offer(new int[]{A,i});
                continue;
            }
            // 비어있지 않으면 이번에 들어가는 것과 스택을 비교
            while (!stack.isEmpty() && stack.peekLast()[0] < A) {
                NGE[stack.peekLast()[1]] = A;
                stack.pollLast();
            }
            stack.offer(new int[]{A,i});
        }
        while (!stack.isEmpty()) {
            int[] cur = stack.pollLast();
            NGE[cur[1]] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NGE.length; i++) {
            sb.append(NGE[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
