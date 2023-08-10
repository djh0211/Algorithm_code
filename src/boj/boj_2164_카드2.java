package boj;

import java.util.*;
import java.io.*;

public class boj_2164_카드2 {
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N ; i++) {
            q.offer(i);
        }
        int polled = 0;
        while (true){
            if (exitCheck()){
                break;
            }
            q.pollFirst();
            if (exitCheck()){
                break;
            }
            polled = q.pollFirst();
            q.offerLast(polled);
        }
        System.out.println(q.pollFirst());
    }
    static boolean exitCheck(){
        return q.size()==1?true:false;
    }
}
