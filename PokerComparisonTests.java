package proj3;

import java.util.ArrayList;

/**
 * Contains test methods to check the functionality of classes Card, Deck, and PokerHand
 */
public class PokerComparisonTests {
    final int DECK_SIZE = 52;
    final int JACK = 11;
    final int QUEEN = 12;
    final int ACE = 14;

    public static void main(String[] args) {
        testCard();
        testDeck();
        testHandCompare();
    }

    /**
     * Tests Card functions and behaviour
     */
    static void testCard() {
        Card card;

        card = new Card(JACK, "clubs");
        Testing.assertEquals("Card.toString()", card.toString(), "Jack of clubs");
        card = new Card(2, "diamonds");
        Testing.assertEquals("Card.toString()", card.toString(), "2 diamonds");
        card = new Card(ACE, "diamonds");
        Testing.assertEquals("Card.toString()", card.toString(), "Ace of diamonds");
        card = new Card(QUEEN, "clubs");
        Testing.assertEquals("Card.toString()", card.toString(), "Queen of clubs");
    }

    /**
     * Tests Deck functions and behaviour
     */
    static void testDeck() {
        Deck deck;

        // testing size
        deck = new Deck();
        Testing.assertEquals("Deck.size()", deck.size(), DECK_SIZE);
        deck.deal();
        Testing.assertEquals("Deck.size() after deal", deck.size(), DECK_SIZE - 1);
        while (deck.size() > 0) {
            deck.deal();
        }
        Testing.assertEquals("Deck.size() after all cards got dealt", deck.size(), 0);

        // testing deal null
        Testing.assertEquals("Deck.deal() null", deck.deal(), null);

    }

    /**
     * Helper method to create hand easily given cards as multiple arguments
     *
     * @param cards multiple arguments card array
     * @return PokerHand instance of cards
     */
    static PokerHand createHand(Card... cards) {
        var list = new ArrayList<Card>();
        for (int i = 0; i < cards.length; i++) {
            list.add(cards[i]);
        }
        return new PokerHand(list);
    }

    /**
     * Tests PokerHand.compareTo method
     */
    static void testHandCompare() {

        PokerHand handA;
        PokerHand handB;

        handA = createHand(
                new Card(9, "spades"),
                new Card(5, "diamonds"),
                new Card(8, "clubs"),
                new Card(6, "hearts"),
                new Card(4, "spades"));
        handB = createHand(
                new Card(10, "diamonds"),
                new Card(7, "hearts"),
                new Card(9, "clubs"),
                new Card(5, "spades"),
                new Card(4, "hearts"));

        Testing.assertTrue("case 1: Hands with different high card", handA.compareTo(handB) < 0);

        handA = createHand(
                new Card(9, "spades"),
                new Card(9, "diamonds"),
                new Card(8, "clubs"),
                new Card(6, "hearts"),
                new Card(4, "spades"));
        handB = createHand(
                new Card(9, "diamonds"),
                new Card(9, "hearts"),
                new Card(8, "clubs"),
                new Card(5, "spades"),
                new Card(4, "hearts"));

        Testing.assertTrue("case 2: Hands with same high card", handA.compareTo(handB) == 0);

        handA = createHand(
                new Card(9, "spades"),
                new Card(9, "diamonds"),
                new Card(8, "clubs"),
                new Card(6, "hearts"),
                new Card(4, "spades"));
        handB = createHand(
                new Card(10, "diamonds"),
                new Card(10, "hearts"),
                new Card(8, "clubs"),
                new Card(5, "spades"),
                new Card(4, "hearts"));

        Testing.assertTrue("case 3: Hands with different pairs", handA.compareTo(handB) < 0);
        handA = createHand(
                new Card(9, "spades"),
                new Card(9, "diamonds"),
                new Card(8, "clubs"),
                new Card(8, "hearts"),
                new Card(4, "spades"));
        handB = createHand(
                new Card(11, "diamonds"),
                new Card(11, "hearts"),
                new Card(10, "clubs"),
                new Card(10, "spades"),
                new Card(4, "hearts"));

        Testing.assertTrue("case 4: Hands with different two pairs", handA.compareTo(handB) < 0);

        handA = createHand(
                new Card(10, "hearts"),
                new Card(8, "hearts"),
                new Card(7, "hearts"),
                new Card(6, "hearts"),
                new Card(4, "hearts"));
        handB = createHand(
                new Card(9, "spades"),
                new Card(8, "spades"),
                new Card(7, "spades"),
                new Card(6, "spades"),
                new Card(4, "spades"));

        Testing.assertTrue("case 5: Hands with different flushes", handA.compareTo(handB) > 0);

    }

}