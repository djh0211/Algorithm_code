package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_12891_DNA비밀번호 {
    static int lenP, lenS;
    static char [] S;
    static HashMap<Character, Integer> counter = new HashMap<>();
    static StringBuilder sb;
    static int result;
    static int [] thresArray = new int[4];
    static int [][] subSum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        lenS = Integer.parseInt(st.nextToken());
        lenP = Integer.parseInt(st.nextToken());
        S = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            thresArray[i] = Integer.parseInt(st.nextToken());
        }
        counter.put('A', 0);
        counter.put('C', 0);
        counter.put('G', 0);
        counter.put('T', 0);
        subSum = new int[lenS + 1][4];
        for (int i = 0; i < lenS; i++) {
            if (S[i] == 'A'){
                subSum[i+1][0] = subSum[i][0]+1;
                subSum[i+1][1] = subSum[i][1];
                subSum[i+1][2] = subSum[i][2];
                subSum[i+1][3] = subSum[i][3];
            }if (S[i] == 'C'){
                subSum[i+1][1] = subSum[i][1]+1;
                subSum[i+1][0] = subSum[i][0];
                subSum[i+1][2] = subSum[i][2];
                subSum[i+1][3] = subSum[i][3];
            }if (S[i] == 'G'){
                subSum[i+1][2] = subSum[i][2]+1;
                subSum[i+1][0] = subSum[i][0];
                subSum[i+1][1] = subSum[i][1];
                subSum[i+1][3] = subSum[i][3];
            }if (S[i] == 'T'){
                subSum[i+1][3] = subSum[i][3]+1;
                subSum[i+1][0] = subSum[i][0];
                subSum[i+1][1] = subSum[i][1];
                subSum[i+1][2] = subSum[i][2];
            }
        }
//        for (int[] a:subSum
//             ) {
//            System.out.println(Arrays.toString(a));
//        }
        subString(1);

        System.out.println(result);




    }
    static void subString(int from){ // from 부터 lenP개 가져온 부분 문자열이 조건을 만족하는지 고려
                                 // from 매개변수
        int [] temp = new int[4];
        if (from + lenP == lenS + 2){
            return;
        }
        for (int i = 0; i < 4; i++) {
            temp[i] = subSum[from + lenP - 1][i] - subSum[from - 1][i];
        }

        if (temp[0] >= thresArray[0]
            && temp[1] >= thresArray[1]
            && temp[2] >= thresArray[2]
            && temp[3] >= thresArray[3]){
            result++;
        }
        subString(from + 1);


    }
}
