package boj;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class boj_1244_스위치온오프 {
    public static int switchLen;
    public static int studentLen;

    public static int[] switchArr;
    public static int[] studentGenderArr;
    public static int[] studentNumberArr;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        switchLen = Integer.parseInt(br.readLine()); //스위치 개수
        switchArr = new int[switchLen + 1]; // 스위치 배열(맨앞은 -9999넣어서 안쓰는 인덱스임을 밝힌다.)
        st = new StringTokenizer(br.readLine());
        // 스위치 배열 입력
        for (int i = 1; i <= switchLen; i++) {
            switchArr[i] = Integer.parseInt(st.nextToken());
        }

        studentLen = Integer.parseInt(br.readLine()); // 학생 명수
        studentGenderArr = new int[studentLen]; // 학생의 성별 정보
        studentNumberArr = new int[studentLen]; // 학생이 부여받은 숫자 정보
                                        // 남학생은 1로, 여학생은 2로 표시
        for (int i = 0; i < studentLen; i++) {
            st = new StringTokenizer(br.readLine());
            studentGenderArr[i] = Integer.parseInt(st.nextToken());
            studentNumberArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < studentLen; i++) {
            changeSwitch(studentGenderArr[i], studentNumberArr[i]);
        }

        int cnt = 0;
        for (int i = 1; i < switchArr.length; i++) {
            // 0부터 19까지 20개 출력 후 개행
            if (cnt !=0 && cnt%20 == 0)
                System.out.println();
            System.out.print(switchArr[i]+" ");
            cnt++;
        }
    }
    public static void changeSwitch(int gender, int number){
        // 남자
        if (gender == 1){
            for (int i = number; i <= switchLen; i+=number) {
                invert(i);
            }
        }
        // 여자
        if (gender == 2){
            int windowSize = Math.min(number - 1, switchLen - number); // number 기준 왼쪽 여유분 오른쪽 여유분 중 적은 것
            int diff = 0; // 대칭 검사 이후 스위치를 반전시킬 범위

            // windowSize 0이면 자기 자신만 바꾸면 됨
            if (windowSize < 1){
                invert(number);
            }else{
                for (int i = 1; i <= windowSize; i++) {
                    // 대칭임 추가
                    if (switchArr[number - i] == switchArr[number + i]){
                        diff++;
                    }else{ // 대칭 아님 stop
                        break;
                    }
                }
                // diff 0 이면 자기 자신만 바꾼다.
                if (diff == 0)
                    invert(number);
                else{
                    // 아니면 범위 내 스위치 반전
                    for (int i = number - diff; i <= number + diff ; i++) {
                        invert(i);
                    }
                }
            }
        }
    }
    public static void invert(int trg){
        if (switchArr[trg] == 1) switchArr[trg] = 0;
        else switchArr[trg] = 1;
    }

    
}
