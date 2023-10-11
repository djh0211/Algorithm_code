package swea;

import java.io.*;
import java.util.*;
// 구현문제
public class swea_5656_벽돌깨기 {
    static int n,w,h,answer;
    static int[]dx= {1,-1,0,0};
    static int[]dy= {0,0,1,-1};
    static void dfs(int depth,int[][]arr) {
        //이미 답이 0으로 된 경우
        if(answer==0)return;
        //n번 반복하지 않더라도 현재 벽돌이 0인 경우
        if(getCnt(arr)==0) {
            answer=0;
            return;
        }
        //n번 반복한 경우
        if(n==depth) {
            answer=Math.min(answer, getCnt(arr));
            return;
        }
        //0~w-1번째까지 하나하나 부숨
        for(int i=0;i<w;i++) {
            boolean[][]visited=new boolean[h][w];
            int[][]temp=copy(arr);
            boolean flag=false;
            //위에서부터 벽돌을 찾아서 그것을 부숨
            for(int j=0;j<h;j++) {
                if(arr[j][i]>0) {
                    remove(arr, j, i, temp, visited);
                    down(temp);
                    //한번만 부수도록
                    flag=true;
                    break;
                }
            }
            //부순 경우만 다음 진행, 부수지 못 했으면 불필요한 같은 과정을 반복하게 되므로 할 필요 X
            if(flag) {
                dfs(depth+1, temp);
            }
        }
    }
    //벽돌을 아래로 이동
    static void down(int[][]arr) {
        //한 열씩 스택에 담아 pop한다
        Stack<Integer>stack=new Stack<Integer>();
        for(int j=0;j<w;j++) {
            for(int i=0;i<h;i++) {
                if(arr[i][j]>0)stack.push(arr[i][j]);
                arr[i][j]=0;
            }
            int index=h-1;
            while(!stack.empty()) {
                arr[index--][j]=stack.pop();
            }
        }
    }
    //벽돌을 제거, temp배열에서 제거한 결과를 저장하고, arr에서는 뭘 제거할 지 찾음
    static void remove(int[][]arr,int x,int y,int[][]temp,boolean[][]visited) {
        for(int i=0;i<4;i++) {
            for(int k=0;k<arr[x][y];k++) {
                int nx=x+dx[i]*k;
                int ny=y+dy[i]*k;
                if(nx>=0&&nx<h&&ny>=0&&ny<w&&!visited[nx][ny]) {
                    visited[nx][ny]=true;
                    temp[nx][ny]--;
                    if(arr[nx][ny]>0){
                        temp[nx][ny]=0;
                        remove(arr, nx, ny,temp,visited);
                    }
                }
            }
        }
    }
    //벽돌의 수 리턴
    static int getCnt(int[][]arr) {
        int result=0;
        for(int[]a:arr) {
            for(int i:a) {
                if(i>0)result++;
            }
        }
        return result;
    }
    //배열 복사
    static int[][]copy(int[][]arr){
        int[][]temp=new int[h][w];
        for(int i=0;i<h;i++)System.arraycopy(arr[i], 0, temp[i], 0, w);
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T =Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            answer=Integer.MAX_VALUE;
            st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            w=Integer.parseInt(st.nextToken());
            h=Integer.parseInt(st.nextToken());
            int[][]map=new int[h][w];
            for(int i=0;i<h;i++) {
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<w;j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            //============입력=============
            dfs(0, map);
            sb.append("#"+t+" "+answer+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}