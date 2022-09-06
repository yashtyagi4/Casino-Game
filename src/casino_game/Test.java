//Yash Tyagi

package casino_game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {//Yash Tyagi

    public static void main(String[] args) throws IllegalArgumentException {

        boolean check = true, check2 = true, check3 = true;
        Scanner sc = new Scanner(System.in);
        double balance, bankroll, bet, updatedBankroll, amount;
        double input;
        int r = 0, p = 0;

        while (check2 = true) {

            try {
                System.out.printf("\nEnter your Bank Balance: $");
                balance = sc.nextDouble();
                try {
                    if (balance <= 0) {
                        throw new IllegalArgumentException("\nEnter only Positive Bank Balance!\n");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    continue;
                }

                //----------------------------------------------
                do {

                    System.out.printf("\nEnter your Bankroll to start with: $");
                    bankroll = sc.nextDouble();
                    try {
                        if (bankroll <= 0) {
                            throw new IllegalArgumentException("\nEnter only Positive Bankroll!\n");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        r = 1;
                        continue;
                    }

                    if (bankroll <= balance && bankroll > 0) {
                        r = 0;
                    }

                    try {
                        if (bankroll > balance) {
                            throw new IllegalArgumentException("\nYou can't have a Bankroll more than your Bank balance. Try Again!\n");

                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        r = 1;
                    }

                } while (r == 1);

                updatedBankroll = bankroll;

                //-----------------------------------------------------
                do {
                    do {

                        System.out.printf("\n\nWhat you wanna play ?\n-Enter 1 to play Craps Game\n-Enter 2 to play Poker Game\n-Enter 0 to Exit\n");
                        input = sc.nextDouble();

                        if (input == 0) {
                            break;
                        }
                        try {
                            if (input != 1 && input != 2 && input != 0) {
                                throw new IllegalArgumentException("Enter a valid Integer Number!");//Enter 1 to play Craps Game\n-Enter 2 to play Poker Game\n-Enter 0 to Exit\n");

                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getMessage());
                            continue;
                        }
                    } while (input != 0 && input != 1 && input != 2);
                    //--------------------------------------------------------
                    if (input != 0) {
                        do {
                            amount = 0;
                            System.out.printf("\nEnter the amount you want to Bet in this game: ");
                            bet = sc.nextDouble();

                            try {
                                if (bet > updatedBankroll) {
                                    throw new IllegalArgumentException("\nYou can't bet more than your Current Bankroll. Try Again!\n");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                                p = 1;
                                continue;
                            }

                            try {
                                if (bet <= 0) {
                                    throw new IllegalArgumentException("\nEnter only Positive Bet!\n");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println(e.getMessage());
                                continue;
                            }

                            if (input == 2) {
                                DeckOfCards myDeckOfCards = new DeckOfCards();
                                myDeckOfCards.shuffle();
                                Card[] hand = new Card[5];

                                System.out.printf("\n\nCards in Hand:\n");
                                for (int i = 0; i < 5; i++) {
                                    hand[i] = myDeckOfCards.dealCard(); // get next card

                                    System.out.println(hand[i]);
                                }

                                System.out.println("\nHand contains:");
                                int couples = myDeckOfCards.pairs(hand);
                                int triples = myDeckOfCards.threeOfAKind(hand);
                                myDeckOfCards.fourOfAKind(hand);
                                myDeckOfCards.flush(hand);
                                myDeckOfCards.fullHouse(hand);
                                if (myDeckOfCards.bet() == 1) {

                                    updatedBankroll += bet;
                                    amount += bet;
                                    System.out.printf("Yippee! You won $%.2f in this Poker game!\nUpdated Current Bankroll = $%.2f", Math.abs(amount), updatedBankroll);
                                    break;
                                }
                                if (myDeckOfCards.bet() == 0) {

                                    updatedBankroll -= bet;
                                    amount -= bet;
                                    System.out.printf("Nothing \nOh No! You lost $%.2f in this Poker game!\nUpdated Current Bankroll = $%.2f", Math.abs(amount), updatedBankroll);
                                    break;
                                }
                            }

                            if (input == 1) {
                                System.out.println("\n\n");

                                if (Craps.CrapTest() == 1)//WON
                                {
                                    updatedBankroll += bet;
                                    amount += bet;
                                    System.out.printf("Yippee! You won $%.2f in this Crap game!\nUpdated Current Bankroll = $%.2f", Math.abs(amount), updatedBankroll);
                                    break;
                                } else//LOSE
                                {
                                    updatedBankroll -= bet;
                                    amount -= bet;
                                    System.out.printf("\nOh No! You lost $%.2f in this Crap game!\nUpdated Current Bankroll = $%.2f", Math.abs(amount), updatedBankroll);
                                    break;
                                }
                            }
                            if (updatedBankroll == 0) {

                                break;
                            }

                        } while (check == true);
                    }
                    if (updatedBankroll == 0) {
                        System.out.println("\nYou lost all your Bankroll!\nYou can't play anymore!");
                        break;
                    }
                    if (input == 0) {

                        break;
                    }
                } while (check3 == true);

                if (updatedBankroll >= bankroll) {
                    System.out.printf("\n\n You won total of $%.2f in Casino!\nCurrent Money in Bank = $%.2f\n", Math.abs(updatedBankroll - bankroll), balance - bankroll + updatedBankroll);
                    break;
                }
                if (updatedBankroll < bankroll) {
                    System.out.printf("\n\n You lost total of $%.2f in Casino!\nCurrent Money in Bank = $%.2f\n", Math.abs(updatedBankroll - bankroll), balance - bankroll + updatedBankroll);
                    break;
                }
                if (input == 0 && updatedBankroll == 0) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.printf("\nERROR INPUT! YOU CAN ONLY INPUT NUMERICAL VALUE!\nTRY APPLICATION AGAIN!\n\n\n");
                sc.nextLine();

            }

        }
        //------------------------------
        System.out.println("\n\n\nThank you for using the Casino application.");
        System.out.println("Current date and time is " + java.time.LocalDateTime.now().toString());
        System.exit(0);
    }
}