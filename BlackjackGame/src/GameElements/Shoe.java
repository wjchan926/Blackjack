package GameElements;

/**
 * The Shoe class represents the deck or decks of cards that the game will be using.
 * <p>
 * The Shoe class consists of an array of Card objects that store the Card values.
 * <p>
 * The deckLocation variables allows reference of the array without having to modify it each time a card is taken 
 * from the deck.
 * <p>
 * This class is part of the GameElements Package.
 *  
 * @author Wesley Chan
 * @version 1.0
 *
 */
public class Shoe {
	private final int NUM_DECKS = 1;
	private Card[] deck = new Card[52 * NUM_DECKS];	// Shoe of 1 deck, 52 cards		
	private final String[] CARD_RANK = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};	
//	private final String[] CARD_RANK = {"A", "A", "A", "A", "A", "A", "A", "J", "J", "10", "J", "Q", "K"};	
	private int deckLocation;	// Variable showing where we are in deck
	
	/**
	 * Constructor for the Shoe class.
	 * <p>
	 * Each time a new Shoe object is made, the deck will be shuffled.
	 */
	public Shoe(){
		deckLocation = 0;	// Start from beginning of deck
		
		for (int i = 0; i < CARD_RANK.length; i++){
			for(int j = 0; j < 4 * NUM_DECKS; j++){
				deck[j * CARD_RANK.length + i] = new Card(CARD_RANK[i]);
			}
		}
		
		shuffle();	// Shuffle new deck
	}		

	/**
	 * Displays the deck on the console.
	 * <p>
	 * This method is used for testing purposes only.
	 */
	public void displayShoe(){ 
		// For Testing purposes only
		for (int i = 0; i < deck.length; i++){	
			if (deck[i].getCardName() != "10"){
				System.out.print(" " + deck[i].getCardName() + " "); 
			} else {
				System.out.print(deck[i].getCardName() + " ") ;
			}
		}		
		System.out.println();
	}
	
	/**
	 * Shuffles the deck array
	 */
	private void shuffle(){
		for (int i = 0; i < deck.length; i++) { 
			   int j = i + (int) (Math.random() * (deck.length - i)); 
			   Card tempCard = deck[j];
			   deck[j] = deck[i];
			   deck[i] = tempCard;
		} 
	}	
	
	/**
	 * Shuffles the deck array and resets the deckLocation variable to 0.
	 * <p>
	 * This gives us a "new" deck without having to create a new Shoe object on the heap.
	 */
	private void resetDeck(){
		shuffle();
		deckLocation = 0;
	}
	
	/**
	 * Provides the next Card in the Shoe to the Player or Dealer.
	 * <p>
	 * This method is invoked when the player or dealer Hits.
	 * <p>
	 * The shoe is not reset until the table plays through the entire shoe, which means that the same shoe can be used
	 * for multiple rounds of Blackjack.
	 * 
	 * @return A Card object that represents the next card in the shoe
	 * <p>
	 * postcondition: return value will be a reference to a Card object
	 */
	public Card getCard(){
		// Allows a card value to be returned without modifying the deck array
		// Continues with same deck until it runs out of cards		
		if (deckLocation == deck.length){
			resetDeck();
		}
		
		deckLocation++;
		
		return deck[deckLocation - 1];
	}
}	

