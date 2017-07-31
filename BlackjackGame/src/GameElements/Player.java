package GameElements;

/**
 * The Player class extends the SuperPlayer abstract class. 
 * <p>
 * It contains information about the player's chips amount and bet amount.
 * <p>
 * This class is part of the GameElements Package.
 *  
 * @author Wesley Chan
 * @version 1.0
 */
public class Player extends SuperPlayer{
	private int chipAmount;
	private int bet;
	
	/**
	 * Constructor for the Player class.
	 */
	public Player(){
		playerName = "Player";
		playerHand = new Hand();
		chipAmount = 100;	// Player starts with $100
		bet = 0;
	}
	
	/**
	 * Gets the player's current chip amount.
	 * 
	 * @return current chip amount
	 * <p>
	 * postcondition: return value will be of type int
	 */
	public int getChipAmount(){
		return chipAmount;
	}
				
	/**
	 * Gets the player's bet amount for the round.
	 * 
	 * @return bet amount for the round.
	 * <p>
	 * postcondition: return value will be of type int
	 */
	public int getBet(){
		return bet;
	}
		
	/**
	 * Sets the players' chip amount.
	 * 
	 * @param i bet that was made
	 * <p>
	 * precondition: i will be of type int and equal to the bet amount that was made
	 * <p>
	 * postcondition: chipAmount will never be less than 0
	 */
	public void setChipAmount(int i){
		chipAmount += i;
	}	
	
	/**
	 * Sets the bet variable to the amount the player bet.
	 * 
	 * @param i amount the player bet
	 * <p>
	 * precondition: i must be of type int
	 */
	public void placeBet(int i){
		if (i <= 0){
			bet = 1;
			System.out.println("Cannot bet $0 or lower. A bet of $1 has been made for you.");			
		} else if (i > chipAmount){
			bet = chipAmount;
			System.out.println("Insufficient funds. All your chips will be bet.");
		} else{
			bet = i;
		}
	}
	
	/**
	 * Overrides the toString method.
	 * 
	 * @return A string that shows the player's hand and the total hand value.
	 */
	@Override
	public String toString(){		
		return "Player Hand: " + playerHand.showHand() + "\nTotal: " + playerHand.getValue();
	}
}
