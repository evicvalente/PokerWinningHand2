package proj3;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a playing card with a rank and a suit
 */
public class Card {
    /**
     * Maps the rank of a card to its name
     */
    static final Map<Integer, String> RANKS_TO_NAMES = new HashMap<Integer, String>() {
        {
            put(14, "Ace");
            put(13, "King");
            put(12, "Queen");
            put(11, "Jack");
        }
    };
    int rank;
    String suit;

    /**
     * Constructs a new card with the specified rank and suit
     *
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets rank of card
     *
     * @return the rank of the card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Gets suit of card
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns a readable representation of the card, in the format "Jack of clubs" or "7 spades".
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        if (RANKS_TO_NAMES.containsKey(rank))
            return RANKS_TO_NAMES.get(rank) + " of " + suit;
        else
            return Integer.toString(rank) + " " + suit;
    }
}
