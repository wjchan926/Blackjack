package GameElements;

/**
 * The SuperPlayer class is an abstract base class for all the types of players.  
 * <p>
 * Every player will have a hand. Both the player an the dealer can hit.
 * <p>
 * This class is part of the GameElements Package.
 * 
 * @author Wesley Chan
 * @version 1.0
 */
public abstract class SuperPlayer {

	protected Hand playerHand;
	protected String playerName;
	
	/**
	 * Gets the player's hand
	 * 
	 * @return Current player's hand or cards
	 * <p>
	 * postcondition: return will be a reference to a Hand object
	 */
	public Hand getPlayerHand(){
		return playerHand;
	}
	
	/**
	 * Adds a card to the current hand.
	 * <p>
	 * When subclasses hit, they will all receive a card in the same way.  How they decide to hit is variable.
	 * 
	 * @param shoeOb This is the shoe where the cards are pulled from
	 * 
	 */
	public final void hit(Shoe shoeOb){
		playerHand.addCard(shoeOb);
	}
	
	/**
	 * Gets the total value of the player's hand
	 * 
	 * @return Total value of the player's hand
	 * <p>
	 * postcondition: return value will be a positive int
	 */
	public final int getTotal(){		
		return playerHand.getValue();
	}
	
	public final String getName(){		
		return playerName;
	}
	
	/**
	 * Removes all cards from the player's hands.
	 */
	public final void resetHand(){
		playerHand = new Hand();
	}
	
	/**
	 * Checks if this SuperPlayer has Blackjack
	 * 
	 * @return	true if SuperPlayer has Blackjack.
	 * <p>
	 * 			false otherwise.
	 */
	public boolean hasBlackjack(){
		return playerHand.getHasAce() && this.getTotal() == 21;
	}
	
}
