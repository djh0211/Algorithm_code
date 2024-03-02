package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_9944_nm보드완주하기 {
    static int n,m;
    static char[][] board;
    static boolean[][] v;
    static int result;
    static int emptycnt = 0;
    static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String line="";
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        String input="";
        while ((input = br.readLine() )!= null) {
            st = new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            board = new char[n][m];
            v = new boolean[n][m];
            emptycnt = 0;
            result = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String tmp = st.nextToken();
                for (int j = 0; j < m; j++) {
                    board[i][j] = tmp.charAt(j);
                    if (board[i][j] == '.') {
                        emptycnt++;
                    }
                }
            }
            System.out.println(Arrays.deepToString(board));
            sb.append("Case ").append(tc++).append(": ");
//            if (emptycnt==0) {
//                sb.append(0).append("\n");
//                System.out.println(0);
//                continue;
//            }
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (board[i][j]=='.') {
//                        v[i][j] = true;
//                        for (int k = 0; k < 4; k++) {
//                            go(i,j,k,1,1);
//                        }
//                        v[i][j] = false;
//                    }
//                }
//            }
            if (result!=Integer.MAX_VALUE) {
                sb.append(result).append("\n");

            } else {
                sb.append(-1).append("\n");
            }
            System.out.println(result);
        }
        System.out.println(sb.toString());

    }
    static void go(int x, int y, int dm, int cnt, int change) {
        if (cnt == emptycnt) {
            result = Math.min(result, change);
            return;
        }
        int nx = x + move[dm][0];
        int ny = x + move[dm][1];
        if (haveToChange(nx,ny)) {
            // 새로운 칸이 이동 불가인 경우 방향 전환
            for (int i = 0; i < 4; i++) {
                if (i==dm) continue;
                nx = x + move[i][0];
                ny = y + move[i][1];

                if (!haveToChange(nx,ny)) {
                    // 바꾼 칸이 이동 가능하면 이동하자
                    v[nx][ny] = true;
                    go(nx, ny, i, cnt+1, change+1);
                    v[nx][ny] = false;
                }
            }
        } else {
            // 새로운 칸이 이동 가능하므로 그냥 이동하면 된다.
            v[nx][ny] = true;
            go(nx, ny, dm, cnt+1, change);
            v[nx][ny] = false;
        }

    }
    static boolean haveToChange(int x, int y) {
        if (x<0 || n<=x || y<0 || m<=y || v[x][y] || board[x][y]=='*') {
            return true;
        }
        return false;
    }
}
