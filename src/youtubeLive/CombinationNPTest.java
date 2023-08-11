package youtubeLive;

import java.util.Arrays;
import java.util.Scanner;

public class CombinationNPTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int R = sc.nextInt();
        int[] input = new int[N];
        int[] p = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        int cnt = 0;
        while (++cnt<=R) p[cnt] = 1; // 1 1 1 0 0 0...
        System.out.println(Arrays.toString(input));

        do {
            for (int i = 0; i < N; i++) {
                if(p[i]==0) continue;
                System.out.print(input[i] + " ");
            }
            System.out.println();
        }while (np(p));

    }
    private static boolean np(int[] p){ // p : 다음 순열을 원하는 기존 순열의 배열
        // 1. 꼭대기 찾기
        int N = p.length;
        int i = N-1;
        while (i>0 && p[i-1]>=p[i]){
            --i;
        }
        // 1.1 가장 큰 수열의 형태
        if (i==0) return false;

        // 2 꼭대기 직전(i-1)위치에 교환할 한 단계 큰 수 찾기
        int j = N -1;
        while (p[i-1] >= p[j]){ // 스와핑할 수가 무조건 존재하기 때문에 j의 범위를 while에 넣지 않아도 된다.
            --j;
        }

        // 3 i-1 과 j 간의 swap
        swap(p, i-1, j);

        // 4 꼭대기자리부터 맨 뒤까지의 수를 오름차순 형태로 바꾼다.
        int k = N-1;
        while (i<k){
            swap(p,i++,k--);
        }
        return true;

    }
    private static void swap(int[] p, int a, int b){
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }
}
