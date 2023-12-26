package programmers;

import java.util.*;
import java.io.*;
class prg_양과늑대 {
    static int result = 0;
    static boolean[] v;
    static List<Integer>[] g;
    static int n;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        n = info.length;
        v = new boolean[1<<n];
        g = new List[n];
        for (int i=0; i<n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] e:edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        // System.out.println(Arrays.toString(g));
        dfs(1, info);
        answer = result;

        return answer;
    }
    static void dfs(int cur, int[] info) {
        // 현재 상태 체크
        int sheep=0, wolf=0;
        for (int i=0; i<n; i++) {
            if ( ((cur>>i)&1) == 1 ) {
                if (info[i]==1) {
                    wolf++;
                } else {
                    sheep++;
                }
            }
        }
        // 갱신
        if (sheep > wolf) {
            result = Math.max(result,sheep);
        } else return;

        // 이후 경로 탐색
        for (int i=0; i<n; i++) {
            if ( ((cur>>i)&1) == 1 ) {
                // 이미 다녀온애들에서 확장하자
                for (int e:g[i]) {
                    int newCur = (cur|(1<<e));
                    if (!v[newCur]) {
                        v[newCur] = true;
                        dfs(newCur, info);
                    }
                }
            }
        }
    }
}