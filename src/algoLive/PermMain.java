package algoLive;

import java.util.*;
import java.io.*;

public class PermMain {
    static int N=4,R=3,C=0;
    static int[] a={1,2,3,4},b=new int[R];
//    static boolean[] v=new boolean[N];
    static int v = 0;

    public static void main(String[] args) throws Exception{
        C=0;
        perm(0);
        System.out.println(C);
    }

    public static void perm(int cnt){ // cnt 번째 수를 뽑아주세요
        if(cnt == R){ // b[R-1]에 마지막 원소 넣고 +1 되어서 R이 되면.
            System.out.println(Arrays.toString(b)); //케이스 출력
            C++; // 카운트
            return;
        }else{
            for (int i = 0; i < N; i++) {
//                if (v[i]) continue; // 이미 사용한 수이면 pass
                if ((v & (1<<i))!=0) continue; // boolean은 byte로 대체한다.
                // 아니면 방문 처리후 b배열에 추가
//                v[i] = true;
                v = v | (1<<i);
                b[cnt] = a[i];
                perm(cnt + 1);
//                v[i] = false;
                v = v & ~(1<<i);
            }
        }
    }
}
