package youtubeLive;

import java.util.*;
import java.io.*;

public class DiceTest {
    static int N, numbers[];
    static boolean v[];
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        // 입력으로 주사위 던지는 횟수, 주사위 던지기 모드(1,2,3,4)
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 주사위 던지는 횟수 (0<N<7)
        int M = sc.nextInt(); // 주사위 던지기 모드
        numbers = new int[N]; // 던져진 주사위 수들
        v = new boolean[6 + 1]; // 주사위 눈 방문 여부(0은 안쓰고 1~6)
        switch (M){
            case 1: // 중복 순열
                dice1(0);
                bw.write(sb.toString());
                bw.flush();
                break;
            case 2: // 순열
                dice2(0);
                bw.write(sb.toString());
                bw.flush();
                break;
            case 3: // 중복 조합
                dice3(0, 1);
                bw.write(sb.toString());
                bw.flush();
                break;
            case 4: // 조합
                dice4(0, 1);
                bw.write(sb.toString());
                bw.flush();
                break;

        }
    }
    private static void dice1(int cnt){ // cnt+1번째(0번째부터 시작하니까.) 주사위 던지기, cnt: 기존까지 던져진 주사위 횟수
        // 기본 조건
        if (cnt == N){
            sb.append(Arrays.toString(numbers)).append("\n");
            return;
        }

        // 한번 던질때 가능한 상황에 대한 시도(1,2,3,4,5,6 중 가능)
        for (int i = 1; i <= 6; i++) {
            // 기존 주사위의 눈과 중복되는지 검사
            numbers[cnt] = i;
            dice1(cnt + 1);
        }
    }
    private static void dice2(int cnt){ // cnt+1번째(0번째부터 시작하니까.) 주사위 던지기, cnt: 기존까지 던져진 주사위 횟수
        // 기본 조건
        if (cnt == N){
            sb.append(Arrays.toString(numbers)).append("\n");
            return;
        }

        // 한번 던질때 가능한 상황에 대한 시도(1,2,3,4,5,6 중 가능)
        for (int i = 1; i <= 6; i++) {
            // 기존 주사위의 눈과 중복되는지 검사
            if (v[i]) continue;
            numbers[cnt] = i;
            v[i] = true;
            dice2(cnt + 1);
            v[i] = false;
        }
    }
    private static void dice3(int cnt, int start){ // cnt+1번째(0번째부터 시작하니까.) 주사위 던지기, cnt: 기존까지 던져진 주사위 횟수
        // 기본 조건
        if (cnt == N){
            sb.append(Arrays.toString(numbers)).append("\n");
            return;
        }

        // 한번 던질때 가능한 상황에 대한 시도(1,2,3,4,5,6 중 가능)
        for (int i = start; i <= 6; i++) {
            numbers[cnt] = i;
            dice3(cnt + 1, i);
        }
    }
    private static void dice4(int cnt, int start){ // cnt+1번째(0번째부터 시작하니까.) 주사위 던지기, cnt: 기존까지 던져진 주사위 횟수
        // 기본 조건
        if (cnt == N){
            sb.append(Arrays.toString(numbers)).append("\n");
            return;
        }

        // 한번 던질때 가능한 상황에 대한 시도(1,2,3,4,5,6 중 가능)
        for (int i = start; i <= 6; i++) { // 여기서 중복이 이미 걸러짐
            numbers[cnt] = i;
            dice4(cnt + 1, i + 1);
        }
    }
}
