//  M3DeckOfCards.java : M3 Deck Of Cards assignment
//  Create a slot machine
//
//  Cerberus Security Concepts - 15 Mar 2022
//  CST-338, Nina Khuu, Kevin Swei, Steve Forgacs
//
//  Version:	1
//
//  15 Mar 2022	slf Initial, changed comment block
//  16 Mar 2022 slf Added M3DeckOfCards.start()and called from main()
//  18 Mar 2022 slf Added Class Card and supporting methods

package m3deckofcards;

/**
 * M3 Deck of Cards assignment for CST-338
 * @author Cerberus Security Concepts
 */
public class M3DeckOfCards
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            // Create a new DeckOfCards and start it
            M3DeckOfCards m3 = new M3DeckOfCards();
            m3.start();
        }
        catch (Exception e)
        {
            System.out.println("There was an error.");
        }
    }
    
    /**
     * start() to avoid non-static call errors
     */
    public void start()
    {
        testCardClass();
    }
    
    /**
     * 
     */
    static class Card
    {
        // enum for valid suits of Card
        public static enum Suit
        {
            clubs, diamonds, hearts, spades
        }
        
        // String names of cards or invalid
        private String[] SUIT_NAMES = { "Clubs", "Diamonds", "Hearts", "Spades" };
        private String ILLEGAL_CARD = "** illegal **";
        
        // Ranks of the cards
        public static char[] ranks = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'J', 'Q', 'K'};
        public static enum Rank
        {
            Ace, One, Two, Three, Four, Five, Six, Seven, 
            Eight, Nine, Ten, Jack, Queen, King
        }
        
        private char    value;      // rank of this card
        private Suit    suit;       // suit of this card
        private Boolean cardError;  // whether this card has an error (invalid)
        
        /**
         * Default Card constructor
         */
        Card()
        {
            value = Card.ranks[Card.Rank.Ace.ordinal()];
            suit = Suit.clubs;
            cardError = false;
        }
        
        /**
         * Card constructor copying origCard
         * 
         * If origCard is invalid, this Card will be invalid
         * @param origCard a Card to copy
         */
        Card(Card origCard)
        {
            // If the original card is invalid
            this.cardError = origCard.getCardError();
            
            if (this.cardError == false)
            {
                char origValue = origCard.getCardValue();
                // Check if value is really valid
                if (isValidRank(origValue))
                {
                    this.value = origValue;
                    this.suit = origCard.getCardSuit();
                    this.cardError = false;
                }
            }
        }
        
        /**
         * Create a new Card with rank value, and Suit suit
         * 
         * @param value rank of the card
         * @param suit  suit of the card
         */
        Card(char value, Card.Suit suit)
        {
            this.cardError = true;
            
            // If the rank is valid, set card data
            if (isValidRank(value))
            {
                this.value = value;
                this.suit = suit;
                this.cardError = false;
            }
            
            // If an invalid rank was passed in, set cardError to show
            // it is invalid and set card to Ace of Spades
            if (this.cardError == true)
            {
                this.value = ranks[Card.Rank.Ace.ordinal()];
                this.suit = Suit.spades;
                this.cardError = false;
            }
            
        }
        
        /**
         * Set Card values for a card
         * 
         * @param value rank of the card
         * @param suit suit of the card
         * @return 
         */
        public boolean set(char value, Card.Suit suit)
        {
            this.cardError = true;
            
            if (isValidRank(value))
            {
                this.cardError = false;
                this.value = value;
                this.suit = suit;
            }
            
            return this.cardError;
        }
        
        /**
         * 
         * @return rank of the card
         */
        public char getCardValue()
        {
            return this.value;
        }
        
        /**
         * 
         * @return suit of the card
         */
        public Card.Suit getCardSuit()
        {
            return this.suit;
        }
        
        /**
         * 
         * @return cardError, true if invalid card, false if valid
         */
        public boolean getCardError()
        {
            return this.cardError;
        }
        
        /**
         * Check if card is equal to this
         * 
         * @param card Card to compare
         * @return true if equal, false otherwise
         */
        public boolean equals(Card card)
        {
            boolean isEqual = false;
            
            // If both cards are valid
            if (!card.getCardError() && !this.getCardError())
            {
                // Check equality of the suit and rank
                if ( this.suit == card.getCardSuit() && 
                        this.getCardValue() == card.getCardValue())
                {
                    isEqual = true;
                }
            }
            
            return isEqual;
        }

        public boolean isValidRank(char value)
        {
            boolean isValid = false;
            
            // Check if value is a valid rank
            for (int i = 0; i < ranks.length; i++)
            {
                if (value == ranks[i])
                {
                    isValid = true;
                    break;
                }
            }
          
            return isValid;
        }
        /**
         * 
         * @return string of cards variables
         */
        @Override
        public String toString()
        {
            String  s;
            // If the card is invalid return invalid card string
            if (this.cardError == true)
            {
                s = ILLEGAL_CARD;
            }
            else
            {
                // Otherwise return rank of suit
                s = this.value + " of " + suit;
            }
            
            return s;
        }
                        
    }
    
    /**
     * tests for the Card class
    */
    void testCardClass()
    {
    
        Card c = new Card();
        Boolean good = c.set(Card.ranks[Card.Rank.Ace.ordinal()],
                Card.Suit.spades);
        Card d = new Card(c);
        
        System.out.println(c.toString());
        System.out.println(d.toString());
        
        if (c.equals(d))
        {
            System.out.println("Equal");
        }

        Card e = new Card('2', Card.Suit.hearts);
        System.out.println(e.toString());
        if (c.equals(e))
        {
            System.out.println("Equal");
        }
        else
        {
            System.out.println("Not equal");
        }
        
        System.out.println("Card rank " + c.getCardValue() + " Suit " + 
                c.getCardSuit() + " Error " + c.getCardError());
    }

}
