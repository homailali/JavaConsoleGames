package com.Homail.Games.Hangman;
import java.util.Scanner;
public class HangManMain {
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        playGame();
        do {
            System.out.print("Do you want to play again:");
            String choice=read.next();
            if (choice.equalsIgnoreCase("no")) break;
            else if (choice.equalsIgnoreCase("yes")) playGame();
            else System.out.println("Wrong Input");
        } while (true);
        System.out.println("Thanks for playing!");
    }
    static void playGame(){
        boolean boolOfHangMan=true;
        boolean boolOfWrongGuess;
        HangManClass hangMan=new HangManClass();
        hangMan.selectAWord();
        while (boolOfHangMan){
            hangMan.printHangMan();
            boolOfWrongGuess=hangMan.guessWord();
            hangMan.buildHangman(boolOfWrongGuess);
            boolOfHangMan=hangMan.isFullyBuild();
            boolOfHangMan=hangMan.isFullGuessed(boolOfHangMan);
        }
        hangMan.endOfGame();
    }
}