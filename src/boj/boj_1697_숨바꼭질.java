package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_1697_숨바꼭질 {
    static int N,K;
    static HashSet<Integer> v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        v = new HashSet<>(); //[0,100_000]
        System.out.println(search(N));
    }
    static int search(int start){
        ArrayDeque<int[]> q = new ArrayDeque();
        q.offer(new int[]{start,0});
        v.add(start);
        int[] curCnt;
        while (!q.isEmpty()){
            curCnt = q.poll();
            int cur = curCnt[0];
            int cnt = curCnt[1];
            if (cur==K){
                return cnt;
            }
            if (0<=cur*2 && cur*2 <= 100_000 && !v.contains(cur*2)){
                q.offer(new int[]{cur*2, cnt+1});
                v.add(cur*2);
            }
            if (0<=cur-1 && cur-1 <= 100_000 && !v.contains(cur-1)){
                q.offer(new int[]{cur-1, cnt+1});
                v.add(cur-1);
            }
            if (0<=cur+1 && cur+1 <= 100_000 && !v.contains(cur+1)){
                q.offer(new int[]{cur+1, cnt+1});
                v.add(cur+1);
            }
        }
        return -1;
    }
}
