package com.Homail.Games.TicTacToes;
import java.util.Scanner;
public class TicTacToeLogic {
    Scanner read = new Scanner(System.in);
    int row;
    int column;
    int winnerCheck;
    String player;
    public final char[][] BOARD = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    public void printBoard() {
        System.out.println(BOARD[0][0] + " | " + BOARD[0][1] + " | " + BOARD[0][2]);
        System.out.println("---------");
        System.out.println(BOARD[1][0] + " | " + BOARD[1][1] + " | " + BOARD[1][2]);
        System.out.println("---------");
        System.out.println(BOARD[2][0] + " | " + BOARD[2][1] + " | " + BOARD[2][2]);
    }
    public void winnerCheck() {
        switch (winnerCheck) {
            case 0 -> System.out.println("Match has been drawn!");
            case 1 -> System.out.println("Player X has won!");
            case 2 -> System.out.println("Player O has won!");
        }
    }
    public void takeInput(int count) {
        this.player = count % 2 == 0 ? "X" : "O";
        System.out.print("Player " + player + " row:");
        row = read.nextInt();
        System.out.print("Player " + player + " column:");
        column = read.nextInt();
        if (row > 2 || column > 2 || BOARD[row][column]!=' ') {
            if (row > 2 || column >2) System.out.println("Wrong input");
            else System.out.println("Space already occupied");
            do {
                System.out.print("Player " + player + " row:");
                row = read.nextInt();
                System.out.print("Player " + player + " column:");
                column = read.nextInt();
                if (row > 2 || column > 2) System.out.println("Wrong input");
                else if (BOARD[row][column] != ' ') System.out.println("Space already occupied");
                else break;
            } while (true);
        }
    }
    public void fillBoard(int count) {
        BOARD[row][column] = count % 2 == 0 ? 'X' : 'O';
    }
    public boolean checkAllFull() {
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[i].length; j++) {
                if (BOARD[i][j] == ' ') return true;
            }
        }
        return false;
    }
    public boolean checkForOAndX(boolean bool) {
        char X = 'X';
        char O = 'O';
        if (bool) {
            bool = checkingForOAndX(X, bool, 1);
            if (bool) bool = checkingForOAndX(O, bool, 2);
        }
        return bool;
    }
    public boolean checkingForOAndX(char xorO, boolean bool, int winnerValue) {
        if (bool) {
            boolean isEqual;
            for (int i = 0; i < BOARD.length; i++) {
                isEqual = true;
                for (int j = 0; j < BOARD[i].length; j++) {
                    if (BOARD[i][j] != xorO) isEqual = false;
                }
                if (isEqual) {
                    winnerCheck = winnerValue;
                    bool = false;
                    return bool;
                }
            }
            for (int i = 0; i < BOARD.length; i++) {
                isEqual = true;
                for (int j = 0; j < BOARD[i].length; j++) {
                    if (BOARD[j][i] != xorO) isEqual = false;
                }
                if (isEqual) {
                    winnerCheck = winnerValue;
                    bool = false;
                    return bool;
                }
            }
            if (BOARD[0][0] == xorO && BOARD[1][1] == xorO && BOARD[2][2] == xorO) {
                winnerCheck = winnerValue;
                bool = false;
                return bool;
            } else if (BOARD[0][2] == xorO && BOARD[1][1] == xorO && BOARD[2][0] == xorO) {
                winnerCheck = winnerValue;
                bool = false;
                return bool;
            }
        }
        return bool;
    }
}