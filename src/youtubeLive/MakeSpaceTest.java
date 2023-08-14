package youtubeLive;

import java.io.FileInputStream;
import java.util.Scanner;

public class MakeSpaceTest {
    static int[][] spaces;
    static int white,green;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("C:\\SSAFY\\homework_djh0211\\homework_djh0211\\res\\live11_14_dist\\공간만들기_input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        spaces = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                spaces[i][j] = sc.nextInt();
            }
        }
        makeSpace(0,0,N);
        System.out.println(green +" "+white);
    }
    static void makeSpace(int sr, int sc, int size){ // 영역의 최상단 영역의 크기
        int sum = 0;
        for (int r = sr; r < sr+size; r++) {
            for (int c = sc; c < sc+size; c++) {
                sum += spaces[r][c];
            }
        }
        if (sum == 0){ // 모두가 하얀색, size==1 자연 포함 즉 기저조건
            white++;
        } else if (sum != size*size) { // 모든 칸이 같은 색이 아님
            int half = size/2;
            makeSpace(sr,sc,half);
            makeSpace(sr,sc+half,half);
            makeSpace(sr+half,sc,half);
            makeSpace(sr+half,sc+half,half);
        }else { // 모든 칸이 초록색, size==1 자연 포함 즉 기저조건
            green++;
        }
    }
}
