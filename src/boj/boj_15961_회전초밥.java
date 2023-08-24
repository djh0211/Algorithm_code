package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_15961_회전초밥 {
    static int N,d,k,c;
    static ArrayDeque<Integer> cand1 = new ArrayDeque<>();
    static ArrayDeque<Integer> cand2 = new ArrayDeque<>();
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static HashMap<Integer, Integer> dic = new HashMap<>();
    static int result=0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            int dish = Integer.parseInt(br.readLine());
            cand1.offer(dish);
            cand2.offer(dish);
            if (i < k){
                q.offer(dish);
                if (!dic.containsKey(dish)){
                    dic.put(dish,1);
                }else {
                    dic.put(dish, dic.get(dish)+1);
                }
            }
        }
        while (!cand2.isEmpty()){
            cand1.offerLast(cand2.pollFirst());
        }
        for (int i = 0; i < k; i++) {
            cand1.pollFirst();
        }

        // init
        result = dic.size();
        if (!dic.containsKey(c)) result++;

        for (int i = 0; i < N; i++) {
            // do
            int polled = q.pollFirst();
            if (dic.containsKey(polled)){ // 있네?
                if (dic.get(polled) == 1){
                    // 빼버려
                    dic.remove(polled);
                }else{
                    dic.put(polled, dic.get(polled)-1);
                }
            }
            int offered = cand1.pollFirst();
            q.offerLast(offered);
            if (!dic.containsKey(offered)){
                dic.put(offered,1);
            }else {
                dic.put(offered, dic.get(offered)+1);
            }
            int localResult = dic.size();
            if (!dic.containsKey(c)) localResult++;

            result = Math.max(result,localResult);
        }
        System.out.println(result);
    }
}
