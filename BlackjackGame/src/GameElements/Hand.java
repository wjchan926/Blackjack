package GameElements;

/**
 * The Hand class represents a Blackjack player or dealer's hand of cards.  Hands are composed of Card objects.
 * <p>
 * This class handles the case of the soft Ace, where the rank of the card can be 1 or 11.
 * <p>
 * This class is part of the GameElements package.
 * 
 * @author Wesley
 * @version 1.0
 */
public class Hand {
	private int value;
	private boolean hasAce;
	private Card[] cardsInHand;
	
	/**
	 * Constructor for the Hand class.
	 */
	Hand(){
		value = 0;
		cardsInHand = new Card[0];
		hasAce = false;
	}
	
	/**
	 * Gets the hands total value.
	 * <p>
	 * Handles the case of the soft Ace, where the rank of the card can be 1 or 11.
	 * 
	 * @return The total value of the hand
	 * 
	 * postcondition: return value will be of type int
	 */
	public int getValue(){
		// Handles the Soft Ace
		return hasAce && value + 10 <= 21 ? value + 10 : value;
	}	
	
	/**
	 * Gets all the cards in the hand
	 * 
	 * @return All cards in the hand
	 * <p>
	 * postcondition: return value will be an array that contains references to Card objects.
	 */
	public Card[] getCardsInHand(){
		return cardsInHand;
	}
	
	/**
	 * Gets the hasAce variable that signifies if the hand has an Ace.
	 * 
	 * @return true if the hand contains an Ace.
	 * 			false if it does not
	 */
	public boolean getHasAce(){
		return hasAce;
	}
	
	/**
	 * Calculates the total value of hand based.
	 * <p>
	 * Invoked when a player hits.
	 * 
	 * @param h hand before hitting
	 * @param c card that was received after hitting
	 * <p>
	 * precondition: h is a reference to the player's hand prior to hitting and c a reference to the card object that
	 * 				was received after hitting
	 */
	private void setValue(Hand h, Card c){
		value = h.value + c.getValue();
	}
	
	/**
	 * Sets the value of hasAce to true if the hand contains an Ace.
	 * 
	 * @param c Card that has it's name checked to see if it's an Ace.
	 */
	private void setHasAce(Card c){
		if (c.getCardName().equals("A")){
			hasAce = true;
		}
	}
	
	/**
	 * Creates a string with all the cards in the hand separated by a space
	 * 
	 * @return s string that shows all the card names in the hand
	 * <p>
	 * postcondition: return value will be a String
	 */
	public String showHand(){
		String s = new String();
		
		for (int i = 0; i < cardsInHand.length; i++){
			s = s.concat(cardsInHand[i].getCardName() + " ");
		}
		
		return s;
	}
	
	/**
	 * Adds a card to the hand.  Invoked when a hit occurs.
	 * 
	 * @param shoeOb the shoe where the card is retrieved from
	 * <p>
	 * precondition: shoeOb is of type Shoe
	 */
	public void addCard(Shoe shoeOb){
		Card[] cArr = new Card[cardsInHand.length +1];
		
		for (int i = 0; i < cardsInHand.length; i++){
			cArr[i] = cardsInHand[i];
		}
		
		cArr[cardsInHand.length] = shoeOb.getCard();
		
		cardsInHand = cArr;
		setValue(this, cardsInHand[cardsInHand.length - 1]);
		setHasAce(cardsInHand[cardsInHand.length - 1]);
	}
	
}
