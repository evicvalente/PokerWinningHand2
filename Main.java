/**
 * This project models a simplified game of Poker and directly compares hands.
 *
 * @author: Emma Valente
 */

package proj3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulation of the game
 */
public class Main {

    public static void main(String[] args) {
        simulate();
    }

    static void simulate() {
        Scanner input = new Scanner(System.in);
        var score = 0;
        var deck = new Deck();
        deck.shuffle();
        var hand1 = new PokerHand(new ArrayList<Card>());
        var hand2 = new PokerHand(new ArrayList<Card>());
        while (deck.size() >= 10) {
            hand1.clear();
            hand2.clear();
            for (int i = 0; i < 5; i++) {
                hand1.addCard(deck.deal());
                hand2.addCard(deck.deal());
            }
            System.out.println("Hand 1: " + hand1);
            System.out.println("Hand 2: " + hand2);
            System.out.println("Enter the Hand Number which is worth more [Enter 0 if equal]: ");
            var line = input.nextLine();
            var n = Integer.parseInt(line);
            var cmp = hand1.compareTo(hand2);
            if (cmp < 0 && n != 2 || cmp > 0 && n != 1 || cmp == 0 && n != 0) {
                break;
            }
            System.out.println("Good job!");
            score++;
        }
        System.out.println("GAME OVER! Your Score: " + score);
        input.close();
    }
}
