package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_10799_쇠막대기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayDeque stack = new ArrayDeque<String>();
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            String token = String.valueOf(line.charAt(i));
//            System.out.println(token);
            if ( token.equals("(") ){
                stack.offerLast("(");
//                System.out.println("( 삽입");
//                System.out.println("삽입후 "+stack);
            } else {
                if (String.valueOf(line.charAt(i-1)).equals("(")) {
                    // () 연속 등장 -> 레이저
                    stack.pollLast();
                    sum += stack.size();
//                    System.out.println("레이저");
//                    System.out.println("동작후 "+stack+" "+sum);
                } else {
                    // ) 떨어져서 등장 -> ( 하나 닫아야 함
                    stack.pollLast();
                    sum += 1;
//                    System.out.println("조각의 끝");
//                    System.out.println("동작후 "+stack);
                }
            }
//            System.out.println();
        }
        System.out.println(sum);
    }
}
