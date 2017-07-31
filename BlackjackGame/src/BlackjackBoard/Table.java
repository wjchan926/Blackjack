package BlackjackBoard;

import GameElements.Dealer;
import GameElements.Player;
import GameElements.Result;
import GameElements.SuperPlayer;

/**
 * The Table class handles all the game states during the Blackjack games.
 * <p>
 * A table consists of the player and the dealer.
 * <p>
 * This class is part of the BlackjackBoard Package.  
 * <p>
 * It is responsible for: Determining the player turn, Displaying the cards on the table at the moment,
 * Any end of round conditions such as busts and wins.
 * 
 * @author Wesley Chan
 * @version 1.0
 * 	
 */
public class Table {
	private Player p;
	private Dealer d;
	private String playerTurn;
	
	
	/**
	 * Constructor for the Table class
	 * 
	 * @param ptemp	The user/player
	 * @param dtemp	The dealer 
	 * <p>
	 * precondition: ptemp is of type Player and dtemp is of type dealer
	 */
	Table(Player ptemp, Dealer dtemp){
		p = ptemp;
		d = dtemp;
		playerTurn = "Player";
	}
	
	/**
	 * Gets the current player's turn.
	 * 
	 * @return	"Player" if it is the player turn
	 * 			"Dealer" if it is the dealer's turn.
	 */
	public String getPlayerTurn(){
		return playerTurn;
	}
	
	/**
	 * 	Switches the playerTurn to the other player/dealer.
	 */
	public void setPlayer(SuperPlayer sp){
		playerTurn = sp.getName();
	}
	
	/**
	 * Prints out the player's and dealer's hands.
	 * <p>
	 * If it is still the player's turn the dealer's hidden card will be shown as an "X".
	 */
	public void displayTable(){		
		if (playerTurn.equals("Player")){	// Dealer has hidden card			
			System.out.println(p);
			System.out.println("------------------");
			System.out.println(d.hidden());			
		} else {					
			System.out.println(p);			// All cards revealed
			System.out.println("------------------");
			System.out.println(d);	
			System.out.println();
		}
	}
	
	/**
	 * Checks if the dealer has a Blackjack.
	 *
	 * @return true if the dealer has a Blackjack
	 * 			false otherwise
	 */ 
	/*public boolean dealerBlackjack(){
		if (d.getPlayerHand().getHasAce() && d.getTotal() == 21){
			nextPlayer();
			displayTable();
			p.setChipAmount(-p.getBet());
			System.out.println("Dealer has a Blackjack!");
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Checks if the player has a Blackjack.
	 * 
	 * @return true if the player has a Blackjack
	 * 			false otherwise
	 *//*
	public boolean playerBlackjack(){
		 if (p.getPlayerHand().getHasAce() && p.getTotal() == 21){
			nextPlayer();
			displayTable();
			p.setChipAmount(p.getBet());
			System.out.println("You have a Blackjack!");
			return true;
		 } else {
			return false;
		}
	}*/
	
	/**
	 * Checks if SuperPlayer busts/hand value of over 21
	 * 
	 * @param sp SuperPlayer with a hand to check
	 * @return true if the SuperPlayer's hand is over 21
	 * 			false otherwise
	 */
	public boolean checkBust(SuperPlayer sp){		
		return sp.getPlayerHand().getValue() > 21;
	}

	/**
	 * Resets the dealer's hand and the player's hand.  Changes the current turn back to the player.
	 */
	public void resetTable(){
		p.resetHand();
		d.resetHand();
		playerTurn = "Player";
	}
	
	/**
	 * Checks if dealer or player has a blackjack.
	 * 
	 * @return Dealer object if the dealer has a blackjack.
	 * <p>
	 * 			Player object if the player has a blackjack.
	 * <p>
	 * 			Null otherwise
	 */
	private SuperPlayer checkBlackjack(){
		if (d.hasBlackjack()){
			p.setChipAmount(-p.getBet());
			return d;
		} else if (p.hasBlackjack()){
			p.setChipAmount(p.getBet());
			return p;
		} else {
			return null;
		}
	}
	

	private Result roundResult(Dealer d, Player p){
		// Calculates earnings
		SuperPlayer winner = checkBlackjack();
		Result r = new Result(d, p);
		
		if (winner != null){								// Blackjack Case
			r.addWinner(winner);
		} else if (checkBust(p)){
			p.setChipAmount(-p.getBet());
			r.addWinner(d);									// Player Busted								
		} else if(checkBust(d)){
			p.setChipAmount(p.getBet());
			r.addWinner(p);									// Dealer Busted										
		} else if (d.getTotal() > p.getTotal()){
			p.setChipAmount(-p.getBet());
			r.addWinner(d);									// Dealer wins
		} else if (d.getTotal() < p.getTotal()){	
			p.setChipAmount(p.getBet());
			r.addWinner(p);									// Player wins					
		} else {
			r.addAlmostWinner(p);							// Tie
			r.addAlmostWinner(d);
		}
		
		return r;
	}
	
	public void endTableRound(){
		// Prints
		Result r = roundResult(d, p);
		
		SuperPlayer[] winners = r.getWinners();
		SuperPlayer[] almostWinners = r.getAlmostWinners();
				
		if (almostWinners.length == 0){
			for (int i = 0; i < winners.length; i++){
				if (winners[i].getTotal() == 21 && winners[i].getPlayerHand().getHasAce() && // Blackjack Case
					winners[i].getPlayerHand().getCardsInHand().length == 2){		
					setPlayer(d);
					displayTable();
					System.out.println(winners[i].getName() + " wins with a Blackjack!");
				} else {	
					if (p.getTotal() > 21){
						setPlayer(d);
						displayTable();
					}
					System.out.println(winners[i].getName() + " wins!");
				}
			}		
		} else {
			System.out.println("Dealer and Player Ties!");
		}
	}
	
	/**
	 * Displays messages based on how the round ended.  The round can end in player blackjack, bust or win, dealer bust
	 * or win or a tie
	 */
	/*
	public void endTableRound(){
		String result = checkBlackjack();
		
		if (!result.equals("")){							// Blackjack Case
			nextPlayer();									// Shows hidden card
			displayTable();
			System.out.println(result);
		} else if (checkBust(p)){
			nextPlayer();									// Shows hidden card
			displayTable();
			System.out.println("You Busted!\n");			// Player busted	
			p.setChipAmount(-p.getBet());
		} else if(checkBust(d)){
			displayTable();
			System.out.println("Dealer Busted!\n");			// Dealer Busted
			p.setChipAmount(p.getBet());
		} else if (p.getTotal() > d.getTotal()){
			System.out.println("You won the round!\n");		// Player wins
			p.setChipAmount(p.getBet());
		} else if (p.getTotal() < d.getTotal()){
			System.out.println("Dealer won the round.\n"); 	// Dealer wins
			p.setChipAmount(-p.getBet());
		} else {
			System.out.println("Round is a tie.\n"); 		// Tie
		}		
	}*/
}
