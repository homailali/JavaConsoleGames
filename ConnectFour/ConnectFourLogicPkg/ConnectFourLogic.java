/* CopyRight all right reserved 20/12/2023  author:Homail ali hassan a dedicated java developer
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*/
package com.Homail.Games.ConnectFour.ConnectFourLogicPkg;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class ConnectFourLogic {
    // FIELDS
    protected int row;
    protected int count;
    protected int column;
    protected int checkForDrawOnlyOnce;
    protected Random random=new Random();
    protected Scanner read=new Scanner(System.in);
    protected final String[][] BOARD = new String[10][10];
    protected final String[] BOARD_STYLE ={"🔵","🔴","⚪","🚧"};
    private int winnerInt;
    private String WinnerDirections;
    private final int[] WINNER_POSITIONS_END=new int[2];
    private final int[] WINNER_POSITIONS_START=new int[2];
    private final String[] COUNTING_STYLE = {"0\uFE0F⃣","1\uFE0F⃣","2\uFE0F⃣","3\uFE0F⃣","4\uFE0F⃣","5\uFE0F⃣","6\uFE0F⃣","7\uFE0F⃣","8\uFE0F⃣","9\uFE0F⃣"};
    /*
        {"XX","OO","__",""};
        󠀠󠀠{"🔵","🔴","⚪","🚧"};
        {"0","1","2","3","4","5","6","7","8","9"};
        {"0\uFE0F⃣","1\uFE0F⃣","2\uFE0F⃣","3\uFE0F⃣","4\uFE0F⃣","5\uFE0F⃣","6\uFE0F⃣","7\uFE0F⃣","8\uFE0F⃣","9\uFE0F⃣"};
     */
    // CONSTRUCTOR
    protected ConnectFourLogic() {
        initializeBoard();
        winnerInt=2;
    }
    // METHODS
    private void fromAI() {
        do {
            boolean bool=false;
            column=random.nextInt(10);
            for (int i=BOARD.length-1;i>=0;i--){
                if (BOARD[i][column].equals(BOARD_STYLE[2])){
                    row=i;
                    bool=true;
                    break;
                }
            }
            if (bool) {
                BOARD[row][column]=BOARD_STYLE[1];
                break;
            }
        } while (true);
        BOARD[row][column]=BOARD_STYLE[1];
        System.out.println("Ai move column:"+column);
    }
    private void fromUser() {
        boolean bool=false;
        do {
            System.out.print("Player "+BOARD_STYLE[0]+" enter column:");
            if (read.hasNextInt()) column= read.nextInt();
            else {
                read.next();
                System.out.println("Input must be an Integer");
                continue;
            }
            if (column>=10) {
                System.out.println("Input should be below 10");
                continue;
            }
            for (int i=BOARD.length-1;i>=0;i--){
                if (BOARD[i][column].equals(BOARD_STYLE[2])){
                    row=i;
                    bool=true;
                    break;
                }
            }
            if (bool) break;
            else System.out.println("Column "+column+" filled!");
        } while (true);
        BOARD[row][column] = BOARD_STYLE[0];
    }
    private void initializeBoard() {
        for (String[] strings : BOARD) {
            Arrays.fill(strings, BOARD_STYLE[1]);
            Arrays.fill(strings, BOARD_STYLE[2]);
        }
    }
    private void boardFirstLinePrint() {
        System.out.print(BOARD_STYLE[0].equals("🔵") ? " " : "  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(COUNTING_STYLE[i]);
            System.out.print(BOARD_STYLE[0].equals("🔵") ? " " : "  ");
        }
        System.out.println();
    }
    private boolean passPlayersOneByOne(boolean passedBool,String player){
        if (passedBool) {
            boolean bool = true;
            for (int i = 0; i < BOARD.length; i++) {
                for (int j = 0; j < BOARD[i].length; j++) {
                    if (BOARD[i][j].equals(player)) {
                        bool = checkingIfConnectedDraw(i, j, player);
                        if (!bool) break;
                    }
                }
                if (!bool) break;
            }
            return bool;
        }
        if (winnerInt==2) winnerInt=1;
        return false;
    }
    private boolean checkingIfConnectedDraw(int row, int column,String player) {
        boolean bool;
        class CheckWinnersInAllDirections{
            boolean checkForDraw(){
                if (checkForDrawOnlyOnce==0) {
                    for (String[] stringRow : BOARD) {
                        for (String stringCol : stringRow) {
                            if (stringCol.equals(BOARD_STYLE[2])) {
                                checkForDrawOnlyOnce++;
                                return true;
                            }
                        }
                    }
                    winnerInt = 0;
                    return false;
                }
                return true;
            }
            boolean checkRowWiseDown(int row,int column,boolean bool,String player){
                if (bool) {
                    WINNER_POSITIONS_START[0]=row;
                    WINNER_POSITIONS_START[1]=column;
                    int rowEndValue = row + 3;
                    if (rowEndValue > 9) return true;
                    for (int i = row; i <= rowEndValue; i++) {
                        if (!BOARD[i][column].equals(player)) return true;
                    }
                    WinnerDirections="Column";
                    WINNER_POSITIONS_END[0]=rowEndValue;
                    WINNER_POSITIONS_END[1]=column;
                    return false;
                }
                return false;
            }
            boolean checkColWiseRight(int row,int column,boolean bool,String player){
                if (bool) {
                    WINNER_POSITIONS_START[0]=row;
                    WINNER_POSITIONS_START[1]=column;
                    int colEndValue = column + 3;
                    if (colEndValue > 9) return true;
                    for (int i = column; i <= colEndValue; i++) {
                        if (!BOARD[row][i].equals(player)) return true;
                    }
                    WinnerDirections="Row";
                    WINNER_POSITIONS_END[0]=row;
                    WINNER_POSITIONS_END[1]=colEndValue;
                    return false;
                }
                return false;
            }
            boolean checkDiagonallyLeft(int row,int column,boolean bool,String player){
                if (bool){
                    WINNER_POSITIONS_START[0]=row;
                    WINNER_POSITIONS_START[1]=column;
                    int diagonalEndValueRow=row+3;
                    int diagonalEndValueColumn=column-3;
                    if (diagonalEndValueRow>9 || diagonalEndValueColumn<0) return true;
                    for (int i=row;i<=diagonalEndValueRow;i++,column--){
                        if (!BOARD[i][column].equals(player)) return true;
                    }
                    WinnerDirections="Diagonally_Left";
                    WINNER_POSITIONS_END[0]=diagonalEndValueRow;
                    WINNER_POSITIONS_END[1]=diagonalEndValueColumn;
                    return false;
                }
                return false;
            }
            boolean checkDiagonallyRight(int row,int column,boolean bool,String player){
                if (bool) {
                    WINNER_POSITIONS_START[0]=row;
                    WINNER_POSITIONS_START[1]=column;
                    int diagonalEndValueRow = row + 3;
                    int diagonalEndValueColumn = column + 3;
                    if (diagonalEndValueRow > 9 || diagonalEndValueColumn > 9) return true;
                    for (int i = row; i <= diagonalEndValueRow; i++, column++) {
                        if (!BOARD[i][column].equals(player)) return true;
                    }
                    WinnerDirections="Diagonally_Right";
                    WINNER_POSITIONS_END[0]=diagonalEndValueRow;
                    WINNER_POSITIONS_END[1]=diagonalEndValueColumn;
                    return false;
                }
                return false;
            }
        }
        CheckWinnersInAllDirections checkWinnersInAllDirections=new CheckWinnersInAllDirections();
        bool=checkWinnersInAllDirections.checkForDraw();
        bool=checkWinnersInAllDirections.checkRowWiseDown(row,column,bool,player);
        bool=checkWinnersInAllDirections.checkColWiseRight(row,column,bool,player);
        bool=checkWinnersInAllDirections.checkDiagonallyLeft(row,column,bool,player);
        bool=checkWinnersInAllDirections.checkDiagonallyRight(row,column,bool,player);
        return bool;
    }
    protected int playAgain(){
        System.out.print("Do you want to play again:");
        String choice=read.next();
        if (choice.equalsIgnoreCase("yes")) return 0;
        else if (choice.equalsIgnoreCase("no")) return 1;
        else System.out.println("Wrong input");
        return -1;
    }
    protected void playGame(){
        boolean bool=true;
        while (bool){
            this.printBoard();
            this.takeInput();
            bool=this.checkIfConnectedOrDraw();
        }
        this.printBoard();
        this.checkTheWinner();
    }
    protected void takeInput() {
        if (count % 2 == 0) fromUser();
        else fromAI();
        count++;
    }
    protected void printBoard() {
        int count = 0;
        for (String[] stringRow : BOARD) {
            if (count == 0) boardFirstLinePrint();
            for (String stringCol : stringRow) {
                System.out.print("|" + stringCol);
            }
            System.out.print("|");
            if (count==9 && BOARD_STYLE[0]!="XX"){
                System.out.println();
                System.out.print(" ");
                for (int i=0;i<14;i++){
                    System.out.print(BOARD_STYLE[3]);
                }
                System.out.println();
            } else {
                System.out.println();
                count++;
            }
        }
    }
    protected void checkTheWinner(){
        switch (winnerInt){
            case 0 -> System.out.println("Match has been Drawn");
            case 1 -> System.out.println("Player "+BOARD_STYLE[0]+" has Won!");
            case 2 -> System.out.println("Player "+BOARD_STYLE[1]+" has Won!");
        }
        if (winnerInt!=0) {
            System.out.println("Direction:"+WinnerDirections);
            System.out.println("Starting column:" + WINNER_POSITIONS_START[1]);
            System.out.println("Ending column:" + WINNER_POSITIONS_END[1]);
        }
    }
    protected boolean checkIfConnectedOrDraw(){
        boolean bool=true;
        checkForDrawOnlyOnce=0;
        bool=passPlayersOneByOne(bool,BOARD_STYLE[0]);
        bool=passPlayersOneByOne(bool,BOARD_STYLE[1]);
        return bool;
    }
}