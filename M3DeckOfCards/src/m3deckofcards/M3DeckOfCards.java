//  M3DeckOfCards.java : M3 Deck Of Cards assignment
//  Create a slot machine
//
//  Cerberus Security Concepts - 15 Mar 2022
//  CST-338, Nina Khuu, Kevin Swei, Steve Forgacs
//
//	Version:	1
//
//	15 Mar 2022	slf     Initial, changed comment block
//  16 Mar 2022 slf     Added M3DeckOfCards.start()and called from main()

package m3deckofcards;

/**
 *
 * @author Steve
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
            // Create a new Casino and start it
            M3DeckOfCards m3 = new M3DeckOfCards();
            m3.start();
        }
        catch (Exception e)
        {
            System.out.println("There was an error.");
        }
    }
    
    /**
     * start() to avoid non-static calls
     */
    public void start()
    {
        // Do the stuff here
    }

    
}
