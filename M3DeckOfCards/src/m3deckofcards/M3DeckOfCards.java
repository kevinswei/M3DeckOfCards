//  M3DeckOfCards.java : M3 Deck Of Cards assignment
//  Create a deck of cards
//
//  Cerberus Security Concepts - 15 Mar 2022
//  CST-338, Nina Khuu, Kevin Swei, Steve Forgacs
//
//  Version:	1
//
//  15 Mar 2022	slf Initial, changed comment block
//  16 Mar 2022 slf Added M3DeckOfCards.start()and called from main()
//  18 Mar 2022 slf Added Class Card and supporting methods
//  19 Mar 2022 slf Changed isValidRank() to isValid() as per spec

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
                Suit origSuit = origCard.getCardSuit();
                // Check if value is really valid
                if (isValid(origValue, origSuit))
                {
                    this.value = origValue;
                    this.suit = origSuit;
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
            if (isValid(value, suit))
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
            
            if (isValid(value, suit))
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

        public boolean isValid(char value, Suit suit)
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
static class Deck 
    {

       public final int MAX_CARDS = 52 * 6;

       private static Card[] masterPack;
       private Card[] cards;
       private int topCard;

    
       /**
       * Default constructor 
       *If no parameters passed, default to 1 pack of cards
       */
       public Deck() 
       {
          allocateMasterPack();                 //Populate the master deck
          topCard = 51;                         
          cards = new Card[52];
          int index = 0;
          for (Card c : masterPack)     //Populate cards array from masterPack
          {
             cards[index++] = c;
          }
       }
       
       /**
        *Constructor for multiple packs
        *@param number of packs
        */
       public Deck(int numPacks)
       {
           if (numPacks * 52 > MAX_CARDS) 
           {
               System.out.println("Max number of packs allowed. "
                     + "Defaulting to 6 packs.");
               numPacks = 6;
           }
           allocateMasterPack();                //Populate the master deck
           topCard = numPacks * 52 - 1;
           cards = new Card[numPacks * 52];
           int index = 0;
           for (int i = 0; i < numPacks; i++)   
           {
              for (Card c : masterPack)   //Populate cards array from masterPack
              {
                 cards[index++] = c;
              }
           }
       }
       /**
        * Method to re populate Cards array without populating masterPack
        * @param number of packs of cards
        */
       public void init(int numPacks)
       {
           if (numPacks * 52 > MAX_CARDS)
           {
              System.out.println("Max number of packs allowed. "
                     + "Defaulting to 6 packs.");
              numPacks = 6;
           }
           cards = new Card[52 * numPacks];
           topCard = 52 * numPacks - 1;
           int index = 0;
           for (int i = 0; i < numPacks; i++)
           {
              for (Card c : masterPack) 
              {
                 cards[index++] = c;
              }
           }
       }
       
       /**
        * Method to shuffle the deck
        * Uses getRandom method to generate random number 
        * Uses loop to swap cards from random indexes
        */
       public void shuffle() 
       {
           for (int i = 0; i < cards.length; i++) 
           {
              int swapIndex = getRandom(cards.length);

              Card temp = cards[i];
              cards[i] = cards[swapIndex];
              cards[swapIndex] = temp;
           }
       }
       
       /**
        * Method to deal a card from top of deck
        * @return If empty, return bad card, else return card
        */
       public Card dealCard()
       {
           if (topCard < 0) 
           {
              Card c = new Card('Z', Card.Suit.hearts);
               return c;
           }
           Card returnValue = cards[topCard--];

           Card[] newDeck = new Card[cards.length - 1];

           for (int i = 0; i < cards.length - 1; i++)
           {
              newDeck[i] = cards[i];
           }

           cards = newDeck;
           return returnValue;
       }

       /*
        * Method to get random number between a specified range
        * @param the max range of the number
        * @return a random number
        */
       public static int getRandom(int max)
       {
          return (int) (Math.random()* max);
       }
       
       //Accessor for topCard
       public int getTopCard()
       {
          return topCard;
       }
       
       //Method used to populate the master pack array
       static void allocateMasterPack() 
       {

          if (masterPack == null) //Has master pack been populated already? 
          {
             //Initialize the array for suits and ranks
             char[] ranks = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '0',
               'J', 'Q', 'K'};

             Card.Suit[] suits = {Card.Suit.clubs, Card.Suit.diamonds, 
                     Card.Suit.hearts, Card.Suit.spades};

             int index = 0;
             
             masterPack = new Card[52];
             
             //Loop to create card objects for each suit and rank
             for (char rank: ranks)
             {
                  for (Card.Suit suit: suits) 
                  {
                     //Store the card object to masterPack index
                      masterPack[index++] = new Card(rank, suit);
                  }
             }
           }
       }
       }
    void testDeckClass()
    {
       //test for 2 packs of cards
       Deck d1 = new Deck(2);

       for (int i = 0; i < 104; i++) 
       {
          System.out.println(d1.dealCard());
       }
       
       System.out.println();
       System.out.println();
       System.out.println();
       
       //initialize and shuffle
       d1.init(2);
       d1.shuffle();

       for (int i = 0; i < 104; i++)
       {
          System.out.println(d1.dealCard());
       }
       
       System.out.println();
       System.out.println();
       System.out.println();
       //Test for 1 pack
       Deck d2 = new Deck();

       for (int i = 0; i < 52; i++) 
       {
          System.out.println(d2.dealCard());
       }
       
       System.out.println();
       System.out.println();
       System.out.println();
       
       //Initialize and shuffle
       d2.init(1);
       d2.shuffle();

       for (int i = 0; i < 52; i++)
       {
          System.out.println(d2.dealCard());
       }
       
   }
}
