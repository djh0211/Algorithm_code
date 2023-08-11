package programmers;

import java.util.*;
import java.io.*;

class 두큐합같게만들기 {

    static long totalSum, goal;
    static long getSum(int[] arr){
        long temp = 0L;
        for (int i=0;i<arr.length; i++)
            temp += arr[i];
        return temp;
    }
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        // 목표 값 설정
        totalSum = getSum(queue1) + getSum(queue2);
        goal = totalSum/2;
        // 자료구조 큐로 변경
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0L;
        long sum2 = 0L;
        for(int i=0;i<queue1.length;i++){
            q1.offer(queue1[i]);
            sum1 += queue1[i];
        }
        for(int i=0;i<queue2.length;i++){
            q2.offer(queue2[i]);
            sum2 += queue2[i];
        }

        int result = 0;
        while (sum1 != sum2){
            // 이 이상 한다해서 뭐가 나오지 않음.
            if(result > (queue1.length + queue2.length) * 2){
                result = -1;
                break;
            }
            if (sum1 > sum2){
                int polled = q1.poll();
                q2.offer(polled);
                sum1 -= polled;
                sum2 += polled;
            }
            else if (sum1 < sum2){
                int polled = q2.poll();
                q1.offer(polled);
                sum2 -= polled;
                sum1 += polled;
            }
            else{
                break;
            }
            result++;
        }
        answer = result;

        return answer;
    }
}