package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_5430_AC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        l1:for (int i = 0; i < n; i++) {
            String command = br.readLine();
            int m = Integer.parseInt(br.readLine());
            dq.clear();
            String line = br.readLine();
            String temp = "";
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c=='[') {
                    continue;
                }
                if (c==',' || c==']') {
                    if (!temp.equals("")){
                        dq.offer(Integer.parseInt(temp));
                    }
                    temp = "";
                    continue;
                }
                temp += String.valueOf(c);
            }
//            System.out.println(dq);
            boolean mode = true; // 순방향
            for (int j = 0; j < command.length(); j++) {
                char c = command.charAt(j);
                if (c=='R') {
                    mode = !mode;
                }
                else {
                    if (dq.isEmpty()) {
                        sb.append("error\n");
                        continue l1;
                    } else {
                        if (mode) {
                            dq.poll();
                        } else {
                            dq.pollLast();
                        }
                    }
                }
            }
            sb.append("[");
            if (mode) {
                while (!dq.isEmpty()) {
                    if (dq.size()!=1){
                        sb.append(dq.poll()).append(",");
                    }
                    else {
                        sb.append(dq.poll());
                    }
                }
            } else if (!mode) {
                while (!dq.isEmpty()) {
                    if (dq.size()!=1){
                        sb.append(dq.pollLast()).append(",");
                    }
                    else {
                        sb.append(dq.pollLast());
                    }
                }
            }
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }
}
