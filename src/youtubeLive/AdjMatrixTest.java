package youtubeLive;

import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        int[][] adjMatrix = new int[V][V];
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjMatrix[to][from] = adjMatrix[from][to] = 1;
        }

        for (int[] is:adjMatrix
             ) {
            System.out.println(Arrays.toString(is));
        }
    }
}
