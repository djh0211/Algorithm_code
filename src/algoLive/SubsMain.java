package algoLive;

import java.util.Arrays;

public class SubsMain {
    static int N=4,C=0;
    static int[] a={1,2,3,4};
    static boolean[] v=new boolean[N];
    public static void main(String[] args) throws Exception{
//        subs(0, "");
//        System.out.println(C);
        if (Arrays.equals(new int[]{1,2,3}, new int[]{1,2,3}))
            System.out.println("yesr");
    }
    static void subs(int cnt, String str){
        if(cnt==N){
            System.out.println(Arrays.toString(v)); C++;
            return;
        }
        v[cnt] = true;
        subs(cnt + 1, str+" "+a[cnt]+" ");
        v[cnt] = false;
        subs(cnt + 1, str + " .");
    }
}
