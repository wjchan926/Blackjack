package BlackjackBoard;

import java.util.Scanner;

import GameElements.Dealer;
import GameElements.Player;
import GameElements.Shoe;


/**
 * This class simulates a simplified version of a Blackjack game. It uses a looping construct that continues play
 * until the player decides to quit or runs out of chips to bet.
 * <p>
 * This class is part of the BlackjackBoard Package.
 * <p>
 * The construct has a delay during the dealer's turn so the user can view the dealer's actions. 
 * <p>
 * Blackjacks are worth 2 : 1 instead of 3 : 2 in this version of the game.
 * <p>
 * Dealer will also stay on a Soft 17. 
 * 	
 * @author Wesley Chan
 * @version 1.0
 * 
 */
public class BlackjackGameSimulator {
	public static void main (String[] args) throws InterruptedException{
		// Define and initialize all components of the game
		Shoe shoeOb = new Shoe();
		Player playerOb = new Player();
		Dealer dealerOb = new Dealer();
		Table tableOb = new Table(playerOb, dealerOb);
		boolean gameEnd = false;
		
		String action = new String();	// variable to store user input
		
		/*
		 * Simplified Blackjack Game Sequence:
		 * 
		 * 1. Player makes a bet 
		 * 2. Cards are dealt to Player and Dealer
		 * 3. Check for Blackjacks (Possible End Game)
		 * 4. Player Hits or Stays (Possible End Game)
		 * 5. Check for Bust (Possible End Game)
		 * 6. Repeat 4 and 5 until Player Stays
		 * 7. Dealer Hits or Stays (Possible End Game)
		 * 		a. Dealer Stays on 17
		 * 8. Check for Bust (Possible End Game)
		 * 9. Repeat 7 and 8 until Dealer Stays 
		 * 10. Check for Winner / Distribute Winnings
		 * 11. End Round
		 * 
		 */
		
		Scanner input = new Scanner(System.in);					// Create scanner for user input
		
		System.out.println("Let's Play Blackjack!");				
		System.out.println("\nCurrent Chip Amount: $" + playerOb.getChipAmount());
		
		while (!gameEnd){	

			System.out.print("Please place your bet: $");		// Player Makes Bet
			playerOb.placeBet(input.nextInt());
			
			System.out.println();	
			
			// Deal Cards
			playerOb.hit(shoeOb);
			dealerOb.hit(shoeOb);
			playerOb.hit(shoeOb);			
			dealerOb.hit(shoeOb);	
						
			if(!dealerOb.hasBlackjack() && !playerOb.hasBlackjack()){		
				// Player's Turn							
				while (tableOb.getPlayerTurn().equals("Player") && !tableOb.checkBust(playerOb)){
	
					tableOb.displayTable();
					System.out.print("Player Hit(Type \"H\") or Stay(Type \"S\"): ");			
					input.nextLine();
					action = input.next();
					System.out.println();
					if(action.equalsIgnoreCase("H")){				// Player Hits
						playerOb.hit(shoeOb);							
					} else if (action.equalsIgnoreCase("S")){		// Player Stays
						tableOb.setPlayer(dealerOb);		
					}				
				} 
				
				tableOb.displayTable();
				// Dealer's Turn			
				while (!tableOb.getPlayerTurn().equals("Player") && !tableOb.checkBust(dealerOb)){					
					Thread.sleep(1000);			// Add pause for 1000 milliseconds so we can see dealer decision			
					if(dealerOb.dealerAction(shoeOb)){								
						System.out.println("Dealer Hits.");	// Dealer Hits		
						tableOb.displayTable();	
					} else{
						tableOb.setPlayer(playerOb);						// Dealer Stays
					}				
				} 								
			} 
			
			// Check how the round ended	
			tableOb.endTableRound();
			
			// Continue play?
			if (playerOb.getChipAmount() != 0){
				System.out.println("Current Chip Amount: $" + playerOb.getChipAmount());
				System.out.print("Play again? (Y/N): ");
				input.nextLine();
				action = input.next();
				
				if(action.equalsIgnoreCase("Y")){					// Continue play
					tableOb.resetTable();							// Clears player and dealer hands
				} else if(action.equalsIgnoreCase("N")){			// Player decides to quit
					// End the game
					gameEnd = true;
					System.out.println("You have left the table.");
				}
			} else{
				// End the game
				gameEnd = true;
				System.out.println("You are out of chips!");		// Ran out of Chips
			}
		}
		
		input.close();												// Close out scanner
	}
}
