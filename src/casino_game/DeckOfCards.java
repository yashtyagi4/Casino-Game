//Yash Tyagi

package casino_game;

import java.util.Random;

public class DeckOfCards
{

    private static final String[] faces
            = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private static final String[] suits
            = {"Hearts", "Diamonds", "Clubs", "Spades"};

    private static final Random randomNumbers = new Random();
    private static final int NUMBER_OF_CARDS = 52;
    private Card[] deck;
    private int currentCard;
    int check = 0;
    int won = 0;

    public DeckOfCards() {
        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;
        won = 0;

        for (int count = 0; count < deck.length; count++) {
            deck[count]
                    = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() {
        currentCard = 0;

        for (int first = 0; first < deck.length; first++) {
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    public Card dealCard() {
        // determine whether cards remain to be dealt
        if (currentCard < deck.length) {
            return deck[currentCard++]; // return current Card in array
        } else {
            return null; // return null to indicate that all cards were dealt
        }
    }

    private int[] totalHand(Card hand[]) {
        int[] numbers = new int[faces.length]; // store number of face

        // initialize all elements of numbers to zero
        for (int i = 0; i < 13; i++) {
            numbers[i] = 0;
        }
        // compare each card in the hand to each element in the faces array
        for (int h = 0; h < hand.length; h++) {
            for (int f = 0; f < 13; f++) {
                if (hand[h].getFace() == faces[f]) {
                    ++numbers[f]; //increment number of faces if equal
                }
            }

        }

        return numbers;

    }

    private int[] totalHand2(Card hand[]) {
        int[] numbers2 = new int[suits.length];
        for (int i = 0; i < 4; i++) {
            numbers2[i] = 0;
        }
        for (int h = 0; h < hand.length; h++) {
            for (int g = 0; g < 4; g++) {
                if (hand[h].getSuit() == suits[g]) {
                    ++numbers2[g];
                }
            }
        }
        return numbers2;
    }

    public int pairs(Card hand[]) {
        int couples = 0;
        int[] numbers = totalHand(hand);
        int aa = 0;
        int bb = 0;

        for (int k = 0; k < numbers.length; k++) {
            if (numbers[k] == 2) {
                System.out.printf("Pair of %s's\n", faces[k]);
                ++couples;

                if (couples == 1) {
                    aa = k;
                    won = 1;
                }
                if (couples == 2) {
                    bb = k;
                    break;
                }

            }

        }
        if (couples == 2) {
            System.out.printf("Two Pairs, Each of %s's & %s's \n", faces[aa], faces[bb]);
        }

        return couples;
    }

    public int threeOfAKind(Card hand[]) {
        int[] numbers = totalHand(hand);
        int triples = 0;
        for (int k = 0; k < numbers.length; k++) {
            if (numbers[k] == 3) {
                System.out.printf("Three Of A Kind of %s's\n", faces[k]);
                triples++;
                won = 1;
                break;
            }
        }
        return triples;
    }

    public void fourOfAKind(Card hand[]) {
        int[] numbers = totalHand(hand);
        for (int k = 0; k < numbers.length; k++) {
            if (numbers[k] == 4) {
                System.out.printf("Four Of A Kind of %s's\n", faces[k]);
                won = 1;
                break;

            }
        }
    }

    public void flush(Card hand[]) {
        //int[] numbers = totalHand(hand);
        int[] numbers2 = totalHand2(hand);
        for (int k = 0; k < numbers2.length; k++) {
            if (numbers2[k] == 5) {
                System.out.printf("Flush of %s's\n", suits[k]);
                won = 1;
                break;
            }
        }

    }

    public void fullHouse(Card hand[]) {
        int[] numbers = totalHand(hand);
        boolean zz = false;
        for (int k = 0; k < numbers.length; k++) {
            if (numbers[k] == 3) {

                for (int p = 0; p < numbers.length; p++) {
                    if (numbers[k] == 2) {
                        System.out.printf("Housefull !!");
                        zz = true;
                        break;
                    }
                }
            }
            if (zz == true) {
                won = 1;
            }
            break;
        }
    }

    public int bet() {
        return won;
    }

}