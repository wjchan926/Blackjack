package GameElements;

public class Result {

	private SuperPlayer[] winners;
	private SuperPlayer[] losers;
	private SuperPlayer[] almostWinners;	// Players that tied
	
	public Result(SuperPlayer... spArr){	// Variadic Arguments
		winners = new SuperPlayer[0];
		losers = spArr;
		almostWinners = new SuperPlayer[0];
	}
		
	public SuperPlayer[] getWinners(){
		return winners;
	}
		
	public SuperPlayer[] getAlmostWinners(){
		return almostWinners;
	}
	
	public void addWinner(SuperPlayer sp){
		winners = addToGroup(sp, winners);	
		removeFromLosers(sp);
	}
	
	public void addAlmostWinner(SuperPlayer sp){
		almostWinners = addToGroup(sp, almostWinners);	
		removeFromLosers(sp);
	}
	
	private SuperPlayer[] addToGroup(SuperPlayer sp, SuperPlayer[] group){
		SuperPlayer[] tempPlayer = new SuperPlayer[group.length + 1];
		
		for(int i = 0; i < group.length; i++){
			tempPlayer[i] = group[i];
		}	// Copy Original Group

		tempPlayer[group.length] = sp;
		
		return tempPlayer;		
	}
	
	private void removeFromLosers(SuperPlayer sp){
		SuperPlayer[] tempPlayer = new SuperPlayer[losers.length - 1];
		
		for(int i = 0, j = 0; i < losers.length; i++){
			if (!losers[i].equals(sp)){
				tempPlayer[j] = losers[i];
				j++;
			}
		}		
		
		losers = tempPlayer;		
	}
	
}
