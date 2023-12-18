package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_1874_스택수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }
        int idx = 0, seq=1;
        StringBuilder sb = new StringBuilder();
        while (idx < N) {
            if (seq <= list[idx]) {
                // 4까지 삽입
                stack.offer(seq++);
                sb.append("+\n");
            }
            else {
                // 찾는것이 스택안에 있다는 것
                if (!stack.isEmpty()) {
                    if (stack.peekLast()==list[idx]) {
                        stack.pollLast();
                        idx++;
                        sb.append("-\n");
                    } else {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}
