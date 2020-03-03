// Colin Kugler
// CSC161.101
// Semester Project
// Sticks card game
// This is a GameGoals class with the method testGoals()
// to test whether or not the player reached their goal to win
public class GameGoals {
// game goals class made up of 1D and 2D arrays, type string values and type integer values 
	int[] cardsUsed = new int[5];
	int[] goalsMet = new int[5];
	
	public boolean testGoals(int[][] goalToMeet, int[][] currentHand) {
		boolean gameWon = false;
		
		for (int x = 0; x < 5; x++){
			for (int y = 0; y < 5; y++){
				if (goalToMeet[x][0] == 0){
					if (cardsUsed[y] != 1 && goalsMet[x] != 1){
						if (currentHand[y][1] == goalToMeet[x][1]){
							cardsUsed[y] = 1;
							goalsMet[x] = 1;
							break;
						}
					}
				}
				else if (goalToMeet[x][1] == 0){
					if (cardsUsed[y] != 1 && goalsMet[x] != 1){
						if (currentHand[y][0] == goalToMeet[x][0]){
							cardsUsed[y] = 1;
							goalsMet[x] = 1;
							break;
						}
					}
				}
				else{
					if (cardsUsed[y] != 1 && goalsMet[x] != 1){
						if (currentHand[y][0] == goalToMeet[x][0] && currentHand[y][1] == goalToMeet[x][1]){
							cardsUsed[y] = 1;
							goalsMet[x] = 1;
							break;
						}
					}
				}
			}
		}
		
		int c = 0;
		for (int z = 0; z < 5; z++){
			if (goalsMet[z] == 1){
				c += 1;
			}
			System.out.println(c);;
		}
		
		if (c == 5){
			gameWon = true;
		}
		
		return gameWon;
	}
}
