package GameElements;

/**
 * The Card Class represents a playing card.  
 * <p>
 * It can be of any value between 2 and 10, Jack, Queen, King, and Ace.
 * Each card has an associated rank, 2 to 10 have ranks equal to their number. Jack, Queen and King have a rank of 10.
 * <p>
 * Ace can have a rank of 1 or 11. The 11 case is handled in the Hand class.
 * <p>
 * This class is part of the GameElements Package.
 * 
 * @author Wesley Chan
 * @version 1.0
 * 
 */
public class Card {
	private String cardName;	
	private int rank;

	/**
	 * Constructor for the Card class with a String argument.
	 * 
	 * @param s	Corresponds the card's name
	 */
	Card(String s){
		cardName = s;
		setValue();		
	}
	
	/**
	 * Copy constructor for the Card class with a Card argument.
	 * 
	 * @param c	Card to be copied
	 */
	Card(Card c){
		cardName = c.getCardName();
		setValue();
	}
	
	/**
	 * Gets the card's name 2 to 10, J, Q, K, or Ace
	 * 
	 * @return 2 to 10 or J, Q, K, or A
	 * <p>
	 * postcondition: return value will be a string that corresponds to a playing card
	 */
	public String getCardName(){
		return cardName;
	}
	
	/**
	 * Gets the corresponding value of the card.
	 * 
	 * @return 1 to 10
	 * <p>
	 * postcondition: return value will be of type int between 1 and 10
	 */
	public int getValue(){
		return rank;
	}
	
	/**
	 * Sets the card's rank based on the playing card's name.
	 * <p>
	 * Ace can be 1 or 11.  The 11 is handled in the Hand class.
	 */
	private void setValue(){
		switch (cardName){
			case "A":	// Ace can be soft Ace or hard ace, will handle in Hand class
				rank = 1;
				break;
			case "K":	// Face cards have value of 10
			case "Q":
			case "J":
				rank = 10;
				break;
			default:
				rank =  Integer.parseInt(cardName);	// Parse the string as an int
		}		
	}
	
}
