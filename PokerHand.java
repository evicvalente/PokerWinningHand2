package proj3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Represents the player hand which has a set of cards
 * A two PokerHand instance can be compared to see which one gets higher a score (winner)
 */
public class PokerHand {
    ArrayList<Card> cards;

    /**
     * Constructs a PokerHand instance with a list of cards
     *
     * @param cardList the list of cards to construct the hand with
     */
    public PokerHand(ArrayList<Card> cardList) {
        // Copying cardList items to cards
        this.cards = new ArrayList<>();
        for (int i = 0; i < cardList.size(); i++) {
            var card = cardList.get(i);
            this.cards.add(card);
        }
    }

    /**
     * Adds card to deck
     *
     * @param card Card instance
     */
    public void addCard(Card card) {
        if (cards.size() >= 5)
            return;
        cards.add(card);
    }

    /**
     * Gets the card at index
     *
     * @param index should be >= 0 and < size
     * @return a card if valid index, null otherwise
     */
    public Card getCard(int index) {
        if (index < 0 || index >= cards.size())
            return null;
        return cards.get(index);
    }

    /**
     * Returns a string representation of the hand, listing each card
     *
     * @return a string representation of the hand
     */
    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < cards.size(); i++) {
            var card = cards.get(i);
            // Don't insert ',' at the end
            str += card;
            if (i != cards.size() - 1)
                str += ", ";
        }
        str += "]";
        return str;
    }

    /**
     * Determines how this hand compares to another hand, returns
     * positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     *         if they are worth the SAME, and a positive number if this is worth
     *         MORE than other
     */
    public int compareTo(PokerHand other) {
        int scoreDiff = this.score() - other.score();
        if (scoreDiff != 0) {
            return scoreDiff;
        } else {
            // Compare high cards
            ArrayList<Card> thisCards = this.cards;
            ArrayList<Card> otherCards = other.cards;
            for (int i = 4; i >= 0; i--) {
                Card thisCard = thisCards.get(i);
                Card otherCard = otherCards.get(i);
                int cardRankDiff = thisCard.getRank() - otherCard.getRank();
                if (cardRankDiff != 0) {
                    return cardRankDiff;
                }
            }
            // Hands are equal
            return 0;
        }
    }

    /**
     * Calculates the score of a hand on a continuous scale
     * score 2-14: for high card
     * score 15-27: for one pair
     * score 28-40: for two pair
     * score 41-53: for a flush
     *
     * @return score of the current hand based on the conditions
     */
    int score() {
        int score;
        HashMap<Integer, Integer> rankValues = new HashMap<Integer, Integer>();

        for (Card card : cards) {
            var rank = card.getRank();
            if (!rankValues.containsKey(rank))
                rankValues.put(rank, 1);
            else
                rankValues.replace(rank, rankValues.get(rank) + 1);
        }

        // A pair or two pairs
        if (rankValues.size() == 4 || rankValues.size() == 3 && max(rankValues.values()) == 2) {
            ArrayList<Integer> pairRanks = new ArrayList<Integer>();
            for (var entry : rankValues.entrySet()) {
                var rank = entry.getKey();
                var count = entry.getValue();

                if (count == 2)
                    pairRanks.add(rank);
            }
            score = 13 * pairRanks.size() + max(pairRanks);
        }
        // A Flush
        else if (flush()) {
            score = 39 + max(rankValues.keySet());
        }
        // High Card
        else {
            score = max(rankValues.keySet());
        }
        return score;

    }

    /**
     * Checks if all cards have the same suit
     *
     * @return True if the cards form a flush else False
     */
    public Boolean flush() {
        var suit = cards.get(0).getSuit();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != suit)
                return false;
        }
        return true;
    }

    /**
     * Clears hand
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Find the highest within a set of integers
     *
     * @param integers the set to find the highest in
     * @return the highest integer
     */
    static int max(Collection<Integer> integers) {
        int highest = 0;
        for (var i : integers) {
            if (i > highest)
                highest = i;
        }
        return highest;
    }
}