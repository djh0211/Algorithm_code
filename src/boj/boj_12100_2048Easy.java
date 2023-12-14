package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_12100_2048Easy {
    static int N, res=0;
    static List<Integer> line = new ArrayList<>();
    static List<Integer> temp = new ArrayList<>();
//    static int[][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][]  board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        slide(2, board, new int[N][N]);
        play(0, board);
//        int[][] boards = new int[N][N];
//        slide(3, board, boards);
//        System.out.println();
//        slide(2, boards, board);

        System.out.println(res);
        
    }
    static void play(int cnt, int[][] board){
        if(cnt==5){
//            res++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    res = Math.max(res, board[i][j]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] newBoard = new int[N][N];
            slide(i, board, newBoard);
            play(cnt+1, newBoard);
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(newBoard[i]));
//        }
    }
    static void slide(int arrow, int[][] board, int[][] newBoard){
        // 상
        if(arrow==0){
            for (int j = 0; j < N; j++) {
                line.clear();
                temp.clear();
                boolean status = false;
                for (int i = 0; i < N; i++) {
                    if (board[i][j]!=0){
                        temp.add(board[i][j]);
                    }
                }
//                System.out.println(temp);
                for (int i = 0; i < temp.size(); i++) {
                    if (status){
                        status = false;
                        continue;
                    }
                    if(i==temp.size()-1){
                        // 마지막 애
                        line.add(temp.get(i));
                    } else {
                        if (temp.get(i).equals(temp.get(i+1))){
                            // 다음 수와 같다면 *2한 수 넣고, 한 칸 뒤로
                            line.add(temp.get(i)*2);
//                            line.add(board[i][j]*2);
                            status = true;
                        } else {
                            line.add(temp.get(i));
                        }
                    }
                }
                for (int i = 0; i < line.size(); i++) {
                    newBoard[i][j] = line.get(i);
                }
            }
        }

        // 우
        if(arrow==1){
            for (int i = 0; i < N; i++) {
                line.clear();
                temp.clear();
                boolean status = false;
                for (int j = N-1; j >= 0; j--) {
                    if (board[i][j]!=0) temp.add(board[i][j]);
                }
                for (int j = 0; j < temp.size(); j++) {
                    if (status){
                        status = false;
                        continue;
                    }
                    if(j==temp.size()-1){
                        // 마지막 애
                        line.add(temp.get(j));
                    } else {
                        if (temp.get(j).equals(temp.get(j+1))){
                            // 다음 수와 같다면 *2한 수 넣고, 한 칸 뒤로
                            line.add(temp.get(j)*2);
                            status = true;
                        } else {
                            line.add(temp.get(j));
                        }
                    }
                }
                for (int j = 0; j < line.size(); j++) {
                    newBoard[i][N-1-j] = line.get(j);
                }
            }
        }

        // 하
        if(arrow==2){
            for (int j = 0; j < N; j++) {
                line.clear();
                temp.clear();
                boolean status = false;
                for (int i = N-1; i >= 0; i--) {
                    if (board[i][j]!=0){
                        temp.add(board[i][j]);
                    }
                }
//                System.out.println(temp);
                for (int i = 0; i < temp.size(); i++) {
                    if (status){
                        status = false;
                        continue;
                    }
                    if(i==temp.size()-1){
                        // 마지막 애
                        line.add(temp.get(i));
                    } else {
                        if (temp.get(i).equals(temp.get(i+1))){
                            // 다음 수와 같다면 *2한 수 넣고, 한 칸 뒤로
                            line.add(temp.get(i)*2);
//                            line.add(board[i][j]*2);
                            status = true;
                        } else {
                            line.add(temp.get(i));
                        }
                    }
                }
                for (int i = 0; i < line.size(); i++) {
                    newBoard[N-1-i][j] = line.get(i);
                }
            }
        }


        // 좌
        if(arrow==3){
            for (int i = 0; i < N; i++) {
                line.clear();
                temp.clear();
                boolean status = false;
                for (int j = 0; j < N; j++) {
                    if (board[i][j]!=0) temp.add(board[i][j]);
                }
                for (int j = 0; j < temp.size(); j++) {
                    if (status){
                        status = false;
                        continue;
                    }
                    if(j==temp.size()-1){
                        // 마지막 애
                        line.add(temp.get(j));
                    } else {
                        if (temp.get(j).equals(temp.get(j+1))){
                            // 다음 수와 같다면 *2한 수 넣고, 한 칸 뒤로
                            line.add(temp.get(j)*2);
                            status = true;
                        } else {
                            line.add(temp.get(j));
                        }
                    }
                }
                for (int j = 0; j < line.size(); j++) {
                    newBoard[i][j] = line.get(j);
                }
            }
        }


//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(newBoard[i]));
//        }
    }
}
