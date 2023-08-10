package boj;

import java.io.*;
import java.util.*;

public class boj_16926_배열돌리기1 {
    static int N, M, R;
    static int[] orgArr;
    static int[] newArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        orgArr = new int[N*M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                orgArr[i * M + j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < R; r++) {
            newArr = new int[N*M];
            // do rotate
            rotate();
            // then copy it
            copyNewArrToOrgArr();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(orgArr[i*M+j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }


    }
    // org를 회전시켜 new에 저장 후, new를 다시 org에 복사, 이후 new는 재 초기화되어 사용된다.
    static void copyNewArrToOrgArr(){
        for (int i = 0; i < N*M; i++) {
            orgArr[i] = newArr[i];
        }
    }
    static void rotate(){
        HashSet cand1 = new HashSet<>();
        HashSet cand2 = new HashSet<>();
        for (int i = 0; i < N-1; i++) {
            cand1.add(i*M);
        }
        for (int i = 2; i <= N; i++) {
            cand2.add(i*M - 1);
        }
        for (int idx = 0; idx < N*M; idx++) {
            if (cand1.contains(idx)){
                newArr[idx + M] = orgArr[idx];
            } else if (cand2.contains(idx)) {
                newArr[idx - M] = orgArr[idx];
            } else if (idx == N*M-1) {
                newArr[0] = orgArr[idx];
            }
            else {
                if (idx < N/2*M){
                    newArr[idx-1] = orgArr[idx];
                }else{
                    newArr[idx+1] = orgArr[idx];
                }
            }
        }
    }
}
