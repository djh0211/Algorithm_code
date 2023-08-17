package programmers;

import java.util.*;
import java.io.*;

class 타겟넘버 {
    static int[] b;
    static int target,N,result;
    static void perm(int cnt, int[]numbers){
        if (cnt==N){
            int sum = 0;
            for (int i=0; i<N; i++){
                sum += b[i]*numbers[i];
            }
            if(sum == target){
                result++;
            }
            return;
        }
        b[cnt] = -1;
        perm(cnt+1,numbers);
        b[cnt] = 1;
        perm(cnt+1,numbers);

    }
    public int solution(int[] numbers, int target) {
        this.target = target;
        N = numbers.length;
        b = new int[N];
        perm(0, numbers);
        int answer = result;
        return answer;
    }
}
