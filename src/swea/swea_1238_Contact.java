package swea;

import java.io.*;
import java.util.*;

public class swea_1238_Contact {
    static HashMap<Integer, ArrayList<Integer>> g;
    static HashMap<Integer, Integer> v;
    static int N,start;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream(new File("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\swea_1238_Contact.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            g = new HashMap<>();
            v = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            try {
                while (true){
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    if (!g.containsKey(from)){
                        g.put(from, new ArrayList<>());
                        g.get(from).add(to);
                    }else {
                        g.get(from).add(to);
                    }
                    v.put(from,0);
                    v.put(to,0);
                }
            } catch (Exception e){
                // nothing
            }
            // bfs
            bfs();
            int max = 0;
            for (int k:v.keySet()
                 ) {
                if (max < v.get(k))
                    max = Math.max(v.get(k), max);
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int k:v.keySet()
            ) {
                if (v.get(k) == max){
                    list.add(k);
                }
            }
            sb.append("#").append(tc+1).append(" ").append(list.stream().max((o1,o2)->Integer.compare(o1,o2)).get()).append("\n");
        }
        System.out.println(sb.toString());
    }
    static void bfs(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        v.put(start, 0);
        while (!q.isEmpty()){
            int cur = q.poll(); // 현재 노드
            if (g.containsKey(cur)){
                // 진출 간선이 하나라도 있는 경우에는
                for (Integer nextNode:g.get(cur)
                     ) {
                    // 다음 노드가 미방문이면 +1 방문
                    if (v.get(nextNode)==0){
                        v.put(nextNode, v.get(cur)+1);
                        q.offer(nextNode);
                    }
                }
            }
        }
    }
}
