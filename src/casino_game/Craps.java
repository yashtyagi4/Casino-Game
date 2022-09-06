//Yash Tyagi

package casino_game;

import java.security.SecureRandom;

public class Craps {

    private static final SecureRandom randomNumbers = new SecureRandom();

    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;

    public static int CrapTest() {
        int myPoint = 0;
        int gameStatus;
        //Status gameStatus; 

        int sumOfDice = rollDice();

        switch (sumOfDice) {
            case SEVEN:
            case YO_LEVEN:
                gameStatus = 1;//WON
                //gameStatus =1;
                break;
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus = 2;//LOST
                break;
            default:
                gameStatus = 3;//CONTINUE
                myPoint = sumOfDice;
                System.out.printf("Point is %d%n", myPoint);
                break;
        }

        while (gameStatus == 3)//CONTINUE
        {
            sumOfDice = rollDice();

            if (sumOfDice == myPoint) {
                gameStatus = 1;//WON

            } else {
                if (sumOfDice == SEVEN) {
                    gameStatus = 2;//LOST
                }
            }
        }

        return gameStatus;
    }

    public static int rollDice() {

        int die1 = 1 + randomNumbers.nextInt(6);
        int die2 = 1 + randomNumbers.nextInt(6);

        int sum = die1 + die2;

        System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);

        return sum;
    }
}