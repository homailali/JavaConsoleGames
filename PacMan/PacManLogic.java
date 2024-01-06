package com.Homail.Games.PacMan;
import java.util.Random;
import java.util.Scanner;

public class PacManLogic {
    void printBoard(String[][] board){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    void initializeBoard(String[][] board,String[] boardSymbols){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                if (i==0 || i==board.length-1 || j==0 || j==board[i].length-1) {
                    board[i][j]=boardSymbols[0];
                } else {
                    board[i][j]=boardSymbols[1];
                }
            }
        }
    }
    boolean checkBoard(String[][] board,boolean bool,String[] boardSymbols,int[] allMarkedBlack,String[] oldValue){
        allMarkedBlack[0]=0;
        if (bool) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j].equals(boardSymbols[1])) {
                        allMarkedBlack[0] += 1;
                        return bool;
                    }
                }
            }
        }
        for (int i = 0; i < oldValue.length; i++) {
            if (oldValue[i].equals(boardSymbols[1])) {
                allMarkedBlack[0] += 1;
                return bool;
            }
        }
        bool=false;
        return bool;
    }
    boolean movement(String[][] board,int[] position,boolean bool,int[] score,String[] boardSymbols){
        Scanner read=new Scanner(System.in);
        System.out.print("Enter move:");
        char move=read.next().charAt(0);
        move=Character.toLowerCase(move);
        if (move=='w') {
            position[0]-=1;
            if (board[position[0]][position[1]].equals(boardSymbols[1])){
                score[0]+=1;
            }
            if (board[position[0]][position[1]].equals(boardSymbols[3])){
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            } else if (!board[position[0]][position[1]].equals(boardSymbols[0])){
                board[position[0]+1][position[1]]=boardSymbols[2];
                board[position[0]][position[1]]=boardSymbols[4];
            } else {
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            }
        } else if (move=='s') {
            position[0]+=1;
            if (board[position[0]][position[1]].equals(boardSymbols[1])){
                score[0]+=1;
            }
            if (board[position[0]][position[1]].equals(boardSymbols[3])){
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            } else if (!board[position[0]][position[1]].equals(boardSymbols[0])){
                board[position[0]-1][position[1]]=boardSymbols[2];
                board[position[0]][position[1]]=boardSymbols[4];
            } else {
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            }
        } else if (move=='d'){
            position[1]+=1;
            if (board[position[0]][position[1]].equals(boardSymbols[1])){
                score[0]+=1;
            }
            if (board[position[0]][position[1]].equals(boardSymbols[3])){
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            } else if (!board[position[0]][position[1]].equals(boardSymbols[0])){
                board[position[0]][position[1]-1]=boardSymbols[2];
                board[position[0]][position[1]]=boardSymbols[4];
            } else {
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            }
        } else if (move=='a'){
            position[1]-=1;
            if (board[position[0]][position[1]].equals(boardSymbols[1])){
                score[0]+=1;
            }
            if (board[position[0]][position[1]].equals(boardSymbols[3])){
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            } else if (!board[position[0]][position[1]].equals(boardSymbols[0])){
                board[position[0]][position[1]+1]=boardSymbols[2];
                board[position[0]][position[1]]=boardSymbols[4];
            } else {
                board[position[0]][position[1]]=boardSymbols[5];
                bool=false;
                return bool;
            }
        } else {
            System.out.println("Wrong move");
        }
        return bool;}
    boolean enemyMovement(String[][] board,int[] enemyPositions,String[] boardSymbols,String[] oldValue,boolean bool,int oldValueIndex){
        if (bool) {
            Random random = new Random();
            short count=0;
            int enemyCount;
            int minus1To1One;
            int minus1To1Two;
            while (true) {
                enemyCount = random.nextInt(2);
                minus1To1One = random.nextInt(2) * 2 - 1;
                minus1To1Two = random.nextInt(2) * 2 - 1;
                if (board[enemyPositions[0] + minus1To1One][enemyPositions[1]].equals(boardSymbols[4]) && enemyCount == 0) {
                    enemyPositions[0] += minus1To1One;
                    board[enemyPositions[0]][enemyPositions[1]] = boardSymbols[5];
                    bool = false;
                    return bool;
                } else if (board[enemyPositions[0] + minus1To1One][enemyPositions[1]].equals(boardSymbols[1]) || board[enemyPositions[0] + minus1To1One][enemyPositions[1]].equals(boardSymbols[2]) && enemyCount == 0) {
                    board[enemyPositions[0]][enemyPositions[1]] = oldValue[oldValueIndex];
                    enemyPositions[0] += minus1To1One;
                    oldValue[oldValueIndex] = board[enemyPositions[0]][enemyPositions[1]];
                    board[enemyPositions[0]][enemyPositions[1]] = boardSymbols[3];
                    break;
                } else if (board[enemyPositions[0]][enemyPositions[1] + minus1To1Two].equals(boardSymbols[4]) && enemyCount == 1) {
                    enemyPositions[1] += minus1To1Two;
                    board[enemyPositions[0]][enemyPositions[1]] = boardSymbols[5];
                    bool = false;
                    return bool;
                } else if (board[enemyPositions[0]][enemyPositions[1] + minus1To1Two].equals(boardSymbols[1]) || board[enemyPositions[0]][enemyPositions[1] + minus1To1Two].equals(boardSymbols[2]) && enemyCount == 1) {
                    board[enemyPositions[0]][enemyPositions[1]] = oldValue[oldValueIndex];
                    enemyPositions[1] += minus1To1Two;
                    oldValue[oldValueIndex] = board[enemyPositions[0]][enemyPositions[1]];
                    board[enemyPositions[0]][enemyPositions[1]] = boardSymbols[3];
                    break;
                }
                count++;
                if (count==Short.MAX_VALUE) break;
            }
        }
        return bool;
    }
    void boardObstacles(String[][] board,int[] position,int[] enemy1Posi,int[] enemy2Posi,int[] enemy3Posi,int[] enemy4Posi,int[] enemy5Posi,int[] enemy6Posi,int[] enemy7Posi,int[] enemy8Posi,int[] enemy9Posi,int[] enemy10Posi,String[] boardSymbols) {
        Random random=new Random();
        int row;
        int column;
        board[enemy1Posi[0]][enemy1Posi[1]]=boardSymbols[3];
        board[enemy2Posi[0]][enemy2Posi[1]]=boardSymbols[3];
        board[enemy3Posi[0]][enemy3Posi[1]]=boardSymbols[3];
        board[enemy4Posi[0]][enemy4Posi[1]]=boardSymbols[3];
        board[enemy5Posi[0]][enemy5Posi[1]]=boardSymbols[3];
        board[enemy6Posi[0]][enemy6Posi[1]]=boardSymbols[3];
        board[enemy7Posi[0]][enemy7Posi[1]]=boardSymbols[3];
        board[enemy8Posi[0]][enemy8Posi[1]]=boardSymbols[3];
        board[enemy9Posi[0]][enemy9Posi[1]]=boardSymbols[3];
        board[enemy10Posi[0]][enemy10Posi[1]]=boardSymbols[3];
        // Start of Multiple Row One Column //
        for (int i=board.length-9;i<board.length-4;i++){
            for (int j=board[i].length-5;j<board[i].length-4;j++){
                board[i][j]=boardSymbols[0];
            }
        }
        for (int i=board.length-9;i<board.length-4;i++){
            for (int j=board[i].length-17;j<board[i].length-16;j++){
                board[i][j]=boardSymbols[0];
            }
        }
        // End of Multiple Row One Column //

        // Start of Multiple Column One Row //
        for (int i=board.length-10;i<board.length-9;i++){
            for (int j=board[i].length-15;j<board[i].length-6;j++){
                board[i][j]=boardSymbols[0];
            }
        }
        for (int i=board.length-4;i<board.length-3;i++){
            for (int j=board[i].length-15;j<board[i].length-6;j++){
                board[i][j]=boardSymbols[0];
            }
        }
        // End of Multiple Column One Row //
        do {
            row=random.nextInt(board.length);
            column= random.nextInt(board[0].length);
        } while (!(board[row][column].equals(boardSymbols[1])));
        position[0]=row;
        position[1]=column;
        board[position[0]][position[1]]= boardSymbols[4];
    }
    boolean allEnemySettings(String[][] board,int[] enemy1Posi,int[] enemy2Posi,int[] enemy3Posi,int[] enemy4Posi,int[] enemy5Posi,int[] enemy6Posi,int[] enemy7Posi,int[] enemy8Posi,int[] enemy9Posi,int[] enemy10Posi,boolean bool,String[] boardSymbols,String[] oldValue){
        if (bool) {
            bool=enemyMovement(board,enemy1Posi,boardSymbols,oldValue,bool,0);
            bool=enemyMovement(board,enemy2Posi,boardSymbols,oldValue,bool,1);
            bool=enemyMovement(board,enemy3Posi,boardSymbols,oldValue,bool,2);
            bool=enemyMovement(board,enemy4Posi,boardSymbols,oldValue,bool,3);
            bool=enemyMovement(board,enemy5Posi,boardSymbols,oldValue,bool,4);
            bool=enemyMovement(board,enemy6Posi,boardSymbols,oldValue,bool,5);
            bool=enemyMovement(board,enemy7Posi,boardSymbols,oldValue,bool,6);
            bool=enemyMovement(board,enemy8Posi,boardSymbols,oldValue,bool,7);
            bool=enemyMovement(board,enemy9Posi,boardSymbols,oldValue,bool,8);
            bool=enemyMovement(board,enemy10Posi,boardSymbols,oldValue,bool,9);
        }
        return bool;
    }
}
