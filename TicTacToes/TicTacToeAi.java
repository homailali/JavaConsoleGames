package com.Homail.Games.TicTacToes;
import java.util.Random;
import java.util.Scanner;
public class TicTacToeAi extends TicTacToeMainClass {
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        TicTacToeAi ticTacToeAi=new TicTacToeAi();
        ticTacToeAi.playGame();
        String choice;
        do {
            System.out.print("Do you want to play again:");
            choice=read.next();
            if (choice.equalsIgnoreCase("yes")) ticTacToeAi.playGame();
            else if (choice.equalsIgnoreCase("no")) break;
            else System.out.println("Wrong Input");
        } while (true);
        System.out.println("Thanks for playing!");
    }
    Random random=new Random();
    Scanner read=new Scanner(System.in);
    void aiMove(int count){
        int row;
        int column;
        if (count%2==0){
            do {
                row=random.nextInt(3);
                column=random.nextInt(3);

            } while (super.BOARD[row][column]!=' ');
            super.BOARD[row][column]='O';
            System.out.println("Player O Move!");
        }
    }
    void playerMove(int count){
        int row;
        int column;
        if (count%2!=0){
            do {
                System.out.print("Player X row:");
                row=read.nextInt();
                System.out.print("Player X column:");
                column=read.nextInt();
            } while ((row>2 || column>2) || super.BOARD[row][column]!=' ');
            super.BOARD[row][column]='X';
        }
    }
    void playGame(){
        TicTacToeAi ticTacToeAi=new TicTacToeAi();
        boolean bool=true;
        int count=0;
        while (bool){
            ticTacToeAi.aiMove(count);
            ticTacToeAi.playerMove(count);
            ticTacToeAi.printBoard();
            bool=ticTacToeAi.checkAllFull();
            bool=ticTacToeAi.checkForOAndX(bool);
            count++;
        }
        ticTacToeAi.winnerCheck();
    }
}