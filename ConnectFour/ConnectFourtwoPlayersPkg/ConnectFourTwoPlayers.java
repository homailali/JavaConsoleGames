package com.Homail.Games.ConnectFour.ConnectFourtwoPlayersPkg;
import com.Homail.Games.ConnectFour.ConnectFourLogicPkg.ConnectFourLogic;
public class ConnectFourTwoPlayers extends ConnectFourLogic {
    public static void main(String[] args) {
        ConnectFourTwoPlayers connectFourTwoPlayers0=new ConnectFourTwoPlayers();
        connectFourTwoPlayers0.playGame();
        int count=0;
        while (count==0 || count==-1){
            count=connectFourTwoPlayers0.playAgain();
            if (count==0){
                ConnectFourTwoPlayers connectFourTwoPlayers1=new ConnectFourTwoPlayers();
                connectFourTwoPlayers1.playGame();
            }
        }
        System.out.println("Thanks for Playing!");
    }
    @Override
    protected void takeInput(){
        class ClassForTakingInput{
            final String PLAYER;
            ClassForTakingInput(){
                PLAYER=count%2==0?BOARD_STYLE[0]:BOARD_STYLE[1];

            }
            void takeInput(){
                boolean bool=false;
                do {
                    System.out.print("Player "+PLAYER+" enter column:");
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
                BOARD[row][column]= PLAYER;
            }
        }
        ClassForTakingInput classForTakingInput=new ClassForTakingInput();
        classForTakingInput.takeInput();
        count++;
    }
}