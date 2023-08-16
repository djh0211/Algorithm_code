package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_6987_월드컵 {
    static int[][] schedule;
    static int c;
    static boolean flag;
    static int[] b = new int[2];
    static int[][] board;
    static int[][] result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        schedule = new int[15][2];
        getSchedule(0,0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            flag = false;
            st = new StringTokenizer(br.readLine());
            board = new int[6][3];
            result = new int[6][3];
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    result[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            if (match(0)){
                sb.append("1 ");
            }else sb.append("0 ");
//            break;
        }
        System.out.println(sb.toString());

    }
    static boolean match(int matchIdx){
        // 기저 조건
        if (matchIdx==15){
            if (isEqual()){ // 결과 동일, 이후 진행 무의미
                return true;
            }
            return false;
        }
        // 가지 치기
        // 해당 인덱스의 매치에 관련된 나라만 검사해보자 일단
        if (!check(matchIdx)){
            return false;
        }
        // 유도 조건
        int a = schedule[matchIdx][0];
        int b = schedule[matchIdx][1];
        // a가 b를 이긴 경우
        board[a][0]++; board[b][2]++;
        if(match(matchIdx+1)){
            return true;
        }
        board[a][0]--; board[b][2]--;
        // b가 a를 이긴 경우
        board[b][0]++; board[a][2]++;
        if (match(matchIdx+1)){
            return true;
        }
        board[b][0]--; board[a][2]--;
        // a가 b를 이긴 경우
        board[a][1]++; board[b][1]++;
        if (match(matchIdx+1)){
            return true;
        }
        board[a][1]--; board[b][1]--;
        return false;
    }
    static boolean check(int matchIdx){
        int a = schedule[matchIdx][0];
        int b = schedule[matchIdx][1];

        for (int i = 0; i < 3; i++) {
            if (result[a][i] < board[a][i]){ // 이미 초과
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (result[b][i] < board[b][i]){ // 이미 초과
                return false;
            }
        }
        return true;
    }
    static boolean isEqual(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != result[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    static void getSchedule(int cnt, int start){
        if (cnt==2){
            schedule[c][0] = b[0];
            schedule[c][1] = b[1];
            c++;
            return;
        }
        for (int i = start; i < 6; i++) {
            b[cnt] = i;
            getSchedule(cnt+1, i+1);
        }
    }
}
