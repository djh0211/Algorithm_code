package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_6198_옥상정원꾸미기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<int[]> stack = new ArrayDeque();
        long sum = 0;
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            int cnt = 1;
            while ( !stack.isEmpty() && stack.peekLast()[0]<=a ) {
                sum += stack.peekLast()[1];
                if (!stack.isEmpty() && stack.peekLast()[0] == a) cnt+=stack.peekLast()[1];
                stack.pollLast();
            }
            if (!stack.isEmpty()) sum++;
            stack.offer(new int[]{a,cnt});
        }
        System.out.println(sum);
    }
}
