package proj3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Deck type, that contains the cards to deal with
 */
public class Deck {
    /**
     * Available suits
     */
    static final String[] SUITS = { "diamonds", "clubs", "hearts", "spades" };

    ArrayList<Card> cards;
    int nextToDeal;

    /**
     * Constructs a new Deck object that contains all 52 cards, one of each rank and suit
     */
    public Deck() {
        cards = new ArrayList<>();
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                cards.add(new Card(i, SUITS[j]));
            }
        }
        gather();
    }

    /**
     * Deal card from the deck
     * 
     * @return an undealt card or null if there is no more undealt cards
     */
    public Card deal() {
        if (nextToDeal < 0)
            return null;
        var card = cards.get(nextToDeal);
        cards.remove(nextToDeal);
        nextToDeal--;
        return card;
    }

    /**
     * Returns the deck to a state where all cards are undealt
     */
    public void gather() {
        nextToDeal = cards.size() - 1;
    }

    /**
     * Gets the size of undealt cards
     * 
     * @return size of undealt cards
     */
    public int size() {
        return nextToDeal + 1;
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Random random = ThreadLocalRandom.current();
        for (int i = size() - 1; i > 0; i--) {
            var k = random.nextInt(i + 1);
            // Swapping
            var itemA = cards.get(i);
            var itemB = cards.get(k);
            cards.set(k, itemA);
            cards.set(i, itemB);
        }
    }

    /**
     * Returns all undealt cards in the deck as a string
     *
     * @return string of undealt cards
     */
    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < cards.size(); i++) {
            str += cards.get(i);
            // Don't insert ',' at the end of string
            if (i != cards.size() - 1)
                str += ", ";
        }
        str += "]";
        return str;
    }
}
