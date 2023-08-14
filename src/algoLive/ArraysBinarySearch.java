package algoLive;

import java.util.Arrays;

public class ArraysBinarySearch {
    public static void main(String[] args) throws Exception{
        int[] ia={1,3,5,7,9};

        System.out.println();
        System.out.println(Arrays.binarySearch(ia,-9));      //-1
        System.out.println(Arrays.binarySearch(ia,-1));      //-1
        System.out.println(Arrays.binarySearch(ia,0));       //-1

        System.out.println(Arrays.binarySearch(ia,1)); //0
        System.out.println(Arrays.binarySearch(ia,2));      //-2
        System.out.println(Arrays.binarySearch(ia,4));      //-3
        System.out.println(Arrays.binarySearch(ia,6));      //-4
        System.out.println(Arrays.binarySearch(ia,8));      //-5
        System.out.println(Arrays.binarySearch(ia,10));     //-6
        System.out.println(Arrays.binarySearch(ia,20));     //-6
        System.out.println();
        // 정상적인 탐색 : 양수 인덱스
        // 못찾으면 음수값 리턴
            // 해당 인덱스에 있어야 되는데 없더라
    }
}
