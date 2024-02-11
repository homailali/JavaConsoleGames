package com.Homail.Games.TicTacToes;
import java.util.Scanner;
public class TicTacToeTwoUser extends TicTacToeLogic {
    public static void main(String[] args) {
       Scanner read=new Scanner(System.in);
       TicTacToeTwoUser ticTacToeTwoUser=new TicTacToeTwoUser();
       ticTacToeTwoUser.playGame();
       String choice;
       do {
           System.out.print("Do you want to play again:");
           choice=read.next();
           if (choice.equalsIgnoreCase("yes")) ticTacToeTwoUser.playGame();
           else if (choice.equalsIgnoreCase("no")) break;
           else System.out.println("Wrong Input");
       } while (true);
        System.out.println("Thanks for playing!");
    }
    void playGame(){
        TicTacToeTwoUser ticTacToeTwoUser=new TicTacToeTwoUser();
        boolean bool=true;
        int count=0;
        while (bool){
            ticTacToeTwoUser.printBoard();
            ticTacToeTwoUser.takeInput(count);
            ticTacToeTwoUser.fillBoard(count);
            bool=ticTacToeTwoUser.checkAllFull();
            bool=ticTacToeTwoUser.checkForOAndX(bool);
            count++;
        }
        ticTacToeTwoUser.printBoard();
        ticTacToeTwoUser.winnerCheck();
    }
}