package GameElements;

/**
 * The Dealer class extends the SuperPlayer abstract class. It controls how the dealer will behave during the game.
 * <p>
 * This class is part of the GameElements Package.
 * 
 * @author Wesley Chan
 * @version 1.0
 */
public class Dealer extends SuperPlayer{
	
	/**
	 * Constructor for the Dealer class.
	 */
	public Dealer(){
		playerName = "Dealer";
		playerHand = new Hand();	
	}	
	
	/*
	 * Checks if the dealer has a hand of less than 17. The dealer will hit if true, but will stay otherwise.
	 */
	public boolean dealerAction(Shoe shoeOb){
		if (playerHand.getValue() < 17){
			hit(shoeOb);				
			return true;	// Dealer Hits
			
		} else{			
			return false;	// Dealer Stays
		}
	}
	
	/**
	 * Creates a string with a hidden card for the dealers hand.
	 * <p>
	 * Only used during the player's turn.
	 * 
	 * @return s A string with 1 card being shown and an "X" to represent the hidden card.
	 */
	public String hidden(){
		String s = new String();			
		if (playerHand.getHasAce()){	// Hidden Card is an Ace
			s = "Dealer Hand: " + playerHand.getCardsInHand()[0].getCardName() + " X\nTotal: " 
					+ (this.getTotal() - 11) + "\n";
		} else{
			s = "Dealer Hand: " + playerHand.getCardsInHand()[0].getCardName() + " X\nTotal: " 
					+ (this.getTotal() - playerHand.getCardsInHand()[1].getValue()) + "\n";
		}		
		return s;		
	}
	
	/**
	 * Overrides the toString method.
	 * 
	 * @return A string that shows the dealer's hand and the total hand value.
	 */
	@Override
	public String toString(){			
		return "Dealer Hand: " + playerHand.showHand() + "\nTotal: " + playerHand.getValue();
	}
	
}
