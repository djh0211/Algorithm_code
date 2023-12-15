package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_2504_괄호의값 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayDeque stack = new ArrayDeque<String>();

        int num=1, sum=0;
        boolean isBroken = false;
        for (int i = 0; i < line.length(); i++) {
            String token = String.valueOf(line.charAt(i));
            if ( token.equals("(") ) {
                num *= 2;
                stack.offerLast("(");
            } else if ( token.equals("[") ) {
                num *= 3;
                stack.offerLast("[");
            } else if ( token.equals(")") ) {
                if (stack.isEmpty() || !stack.peekLast().equals("(") ) {
                    isBroken = true;
                    break;
                }
                if ( String.valueOf(line.charAt(i-1)).equals("(") ) sum += num;
                stack.pollLast();
                num /= 2;
            } else {
                if (stack.isEmpty() || !stack.peekLast().equals("[") ) {
                    isBroken = true;
                    break;
                }
                if ( String.valueOf(line.charAt(i-1)).equals("[") ) sum += num;
                stack.pollLast();
                num /= 3;
            }
        }
        if (isBroken) System.out.println(0);
        else {
            if (stack.isEmpty()) {
                System.out.println(sum);
            } else System.out.println(0);
        }
    }
}
