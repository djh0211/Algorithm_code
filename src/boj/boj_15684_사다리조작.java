package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_15684_사다리조작 {
    static int N,M,H,result=4;
    static HashMap<Integer, ArrayList<Integer>> laddersDic = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (laddersDic.containsKey(a)){
                laddersDic.get(a).add(b);
            }else {
                ArrayList<Integer> tempAL = new ArrayList<>();
                tempAL.add(b);
                laddersDic.put(a,tempAL);
            }
        }
//        for (int k:laddersDic.keySet()
//             ) {
//            System.out.println(laddersDic.get(k));
//        }

        putNewLadder(1, 0);
        System.out.println(result);

    }
    static void putNewLadder(int h, int cnt){
        // 기저
        if (cnt>3){
            return;
        }
        if (h>H+1){
            return;
        }
        if (allRight()){
            // 모든게 다 제대로일 때
            result = Math.min(result, cnt);
            return;
        }
        // 유도
        if (!laddersDic.containsKey(h)){
            // 그 h에 놓인 선이 없다. 즉 어디에든 놓을 수 있다.
            for (int i = 1; i <= N-1; i++) {

            }
        }
        else { // 아니면 무언가는 놓여있음.
            ArrayList<Integer> ladders = laddersDic.get(h);
            for (int i = 1; i <= N-1; i++) {
                // 1 2 3 4 .. N-1까지만 보면된다.
                if (!ladders.contains(i) && !ladders.contains(i-1) && !ladders.contains(i-1)){
                    // 양 옆과 내 자리에 없을때만 놓을 수 있어.
                    laddersDic.get(h).add(i); //추가
                    putNewLadder(h+1, cnt+1);
                    laddersDic.get(h).remove(Integer.valueOf(i)); //다시 제거
                }
            }
        }


    }
    static boolean allRight(){
        for (int i = 1; i <= N; i++) {
            if (getDest(i)!=i) return false;
        }
        return true;
    }
    static int getDest(int src){
        int now = src;
        for (int i = 1; i <= H; i++) {
            // laddersDic에 now 혹은 now-1 이 있어야 한다.
            // now에 있으면 now+1 로 가고 now-1에 있으면 now-1로 간다.
            if (laddersDic.containsKey(i)){
                if (laddersDic.get(i).contains(now)){
                    // 이동할 수 있다면 오른쪽으로 한 칸 이동
                    now++;
                    continue;
                }
            }
            if (laddersDic.containsKey(i)){
                if (laddersDic.get(i).contains(now-1)){
                    // 이동할 수 있다면 오른쪽으로 한 칸 이동
                    now--;
                }
            }
        }
        return now;
    }
}
