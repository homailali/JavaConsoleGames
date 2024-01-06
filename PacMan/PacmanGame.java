package com.Homail.Games.PacMan;
import java.util.Arrays;
import java.util.Scanner;

public class PacmanGame {
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        PacManLogic pacManLogic = new PacManLogic();
        boolean bool=true;
        int moves=0;
        int[] score=new int[1];
        int[] position=new int[2];
        int[] enemy1Position={1,2};
        int[] enemy2Position={1,18};
        int[] enemy3Position={6,2};
        int[] enemy4Position={6,18};
        int[] enemy5Position={11,2};
        int[] enemy6Position={11,18};
        int[] enemy7Position={1,10};
        int[] enemy8position={11,10};
        int[] enemy9Position={5,10};
        int[] enemy10Position={7,10};
        int[] allMarkedBlack=new int[1];
        String[][] board=new String[13][21];
        String[] oldValue=new String[10];
        String[] boardSymbols={"#","."," ","b","a","x"};
        // {"#","."," ","b","a","x"} //
        // {"🧨","\uD83D\uDFEB","⬛","🐱‍","🦸‍♂️","❌"} //
        Arrays.fill(oldValue,boardSymbols[1]);
        pacManLogic.initializeBoard(board,boardSymbols);
        pacManLogic.boardObstacles(board,position,enemy1Position,enemy2Position,enemy3Position,enemy4Position,enemy5Position,enemy6Position,enemy7Position,enemy8position,enemy9Position,enemy10Position,boardSymbols);
        pacManLogic.printBoard(board);
        while (bool){
            bool=pacManLogic.movement(board,position,bool,score,boardSymbols);
            bool=pacManLogic.checkBoard(board,bool,boardSymbols,allMarkedBlack,oldValue);
            bool=pacManLogic.allEnemySettings(board,enemy1Position,enemy2Position,enemy3Position,enemy4Position,enemy5Position,enemy6Position,enemy7Position,enemy8position,enemy9Position,enemy10Position,bool,boardSymbols,oldValue);
            pacManLogic.printBoard(board);
            moves++;
        }
        System.out.println("Scores:"+score[0]);
        System.out.println("Moves:"+moves);
        if (allMarkedBlack[0]==0) System.out.println("Congratulations you have won the game");
    }
}