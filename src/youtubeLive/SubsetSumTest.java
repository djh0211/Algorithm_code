package youtubeLive;

import java.util.Scanner;

public class SubsetSumTest {
    static int N, TARGET, input[];
    static boolean isSelected[], flag;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        TARGET = sc.nextInt();
        input = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }
        generateSubset(0, 0, 0);

    }
    private static void generateSubset(int cnt, int sum,
                                       int selectedCount){
        if (cnt==N) {
            int temp = 0;
            if (selectedCount > 0 && sum == TARGET) {
                for (int i = 0; i < N; i++) {
                    if (isSelected[i]) {
                        System.out.print(input[i] + "\t");
                    }
                }
                System.out.println();
            }
            return;
        }
        isSelected[cnt] = true;
        generateSubset(cnt+1, sum + input[cnt], selectedCount+1);
        isSelected[cnt] = false;
        generateSubset(cnt+1, sum, selectedCount);

    }
}
