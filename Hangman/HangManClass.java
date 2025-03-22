package com.Homail.Games.Hangman;
import java.util.Scanner;
import java.util.Random;
public class HangManClass {
    // changing something if it isn't up to date yep it isn't currently
    Scanner read=new Scanner(System.in);
    Random random=new Random();
    int trackOfHangManBuild=0;
    static String originalCopy;
    static final int[] hangManIndexesCenter1={1,9};
    static final int[] hangManIndexesCenter2={3,9};
    static final int[] hangManIndexesCenter3={4,9};
    static final int[] hangManIndexesHead={2,9};
    static final int[] hangManIndexesLeftHand={3,8};
    static final int[] hangManIndexesRightHand={3,10};
    static final int[] hangManIndexesLeftLeg={5,8};
    static final int[] hangManIndexesRightLeg={5,10};
    static final char[] manSymbols={'|','O','/','\\'};
    static final String[][] listOfWords={
            {"Lion","Zebra","fox","cheetah"},
            {"apple","orange","mango","banana"},
            {"mercedes","tesla","ferrari","bugatti"}
    };
    String guessHint;
    StringBuilder wordToGuess =new StringBuilder();
    StringBuilder originalWord=new StringBuilder();
    char[][] board={
            {'-','-','-','-','-','-','-','-','-','-'},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            {'|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
    };
    void selectAWord(){
        int row=random.nextInt(3);
        int column=random.nextInt(4);
        switch (row){
            case 0 -> guessHint="Animal";
            case 1 -> guessHint="Fruit";
            case 2 -> guessHint="Car";
        }
        originalWord=new StringBuilder(listOfWords[row][column]);
        originalWord=new StringBuilder(String.valueOf(originalWord).toLowerCase());
        wordToGuess.append("_".repeat(originalWord.length()));
        originalCopy=originalWord.toString();
    }
    void printHangMan(){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    boolean guessWord(){
        int count=0;
        System.out.println("Word("+wordToGuess.length()+" letters):"+this.wordToGuess);
        System.out.print("Guess("+this.guessHint+"):");
        char ch=read.next().charAt(0);
        ch=Character.toLowerCase(ch);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (ch == originalWord.charAt(i)) {
                wordToGuess.setCharAt(i, ch);
                originalWord.setCharAt(i, ' ');
                count++;
            }
        }
        return count==0;
    }
    void buildHangman(boolean bool){
        if (bool){
            switch (trackOfHangManBuild){
                case 0 -> board[hangManIndexesCenter1[0]][hangManIndexesCenter1[1]]=manSymbols[0];
                case 1 -> board[hangManIndexesHead[0]][hangManIndexesHead[1]]=manSymbols[1];
                case 2 -> board[hangManIndexesLeftHand[0]][hangManIndexesLeftHand[1]]=manSymbols[2];
                case 3 -> board[hangManIndexesCenter2[0]][hangManIndexesCenter2[1]]=manSymbols[0];
                case 4 -> board[hangManIndexesRightHand[0]][hangManIndexesRightHand[1]]=manSymbols[3];
                case 5 -> board[hangManIndexesCenter3[0]][hangManIndexesCenter3[1]]=manSymbols[0];
                case 6 -> board[hangManIndexesLeftLeg[0]][hangManIndexesLeftLeg[1]]=manSymbols[2];
                case 7 -> board[hangManIndexesRightLeg[0]][hangManIndexesRightLeg[1]]=manSymbols[3];
            }
            trackOfHangManBuild+=1;
        }
    }
    boolean isFullyBuild(){
        return trackOfHangManBuild!=8;
    }
    boolean isFullGuessed(boolean bool){
        if (bool) {
            return wordToGuess.toString().contains("_");
        }
        return  bool;
    }
    void endOfGame(){
        printHangMan();
        System.out.println("Guess: "+wordToGuess);
        if (wordToGuess.toString().contains("_")){
            System.out.println("You have lost the word was:"+this.originalCopy);
        }
        else System.out.println("You have won!");
    }
}