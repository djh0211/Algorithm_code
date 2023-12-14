package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11053_가장긴증가하는부분수열 {
    static int N;
    static int[] arr;
    static List<Integer> C = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();

        C.add(arr[0]);
        for (int i = 1; i < N; i++) {
            int temp = Collections.binarySearch(C,arr[i]);
            if (temp>=0) continue;
            temp = Math.abs(temp) - 1;
            if (C.size()==temp) C.add(arr[i]);
            else C.set(temp, arr[i]);
        }
        System.out.println(C);




    }
}
