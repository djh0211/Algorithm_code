package boj;

import java.io.*;
import java.util.*;

public class boj_9012_괄호 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque stack = new ArrayDeque<Character>();
        StringBuilder sb = new StringBuilder();
        L1:for (int i = 0; i < N; i++) {
            String line =  br.readLine();
            stack.clear();
            for (int j = 0; j < line.length(); j++) {
                String c = String.valueOf(line.charAt(j));
                if (c.equals("(")){
                    // ( 는 그냥 계속 넣어
                    stack.offerLast(c);
                } else {
                    // ) 는 뺄 수 있는 ( 가 있어야 함. 그렇지 않으면 탈락
                    if (!stack.isEmpty() && stack.peekLast().equals("(")){
                        // 뺄 수 있음
                        stack.pollLast();
                    } else {
                        sb.append("NO\n");
                        continue L1;
                    }
                }
            }
            if (!stack.isEmpty()) sb.append("NO\n");
            else sb.append("YES\n");
        }
        System.out.println(sb.toString());
    }
}
