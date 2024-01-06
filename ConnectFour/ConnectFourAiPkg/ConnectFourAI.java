package com.Homail.Games.ConnectFour.ConnectFourAiPkg;
import com.Homail.Games.ConnectFour.ConnectFourLogicPkg.ConnectFourLogic;

public class ConnectFourAI extends ConnectFourLogic{
    public static void main(String[] args){
         ConnectFourAI connectFourAI0 =new ConnectFourAI();
         connectFourAI0.playGame();
         int count=0;
         while (count==0 || count==-1){
             count=connectFourAI0.playAgain();
             if (count==0){
                 ConnectFourAI connectFourAI1=new ConnectFourAI();
                 connectFourAI1.playGame();
             }
         }
         System.out.println("Thanks for playing!");
    }
}
