/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	Game is a class that controlls the actual game playing. It have information about all players 
*		and ScoreBoard. It is where all the game is played.
*/
public class Game{
	//fields
		private boolean playAgain=false;
		private boolean ifWinner=false;
		private int turn=0;
		private Player[] playersObj=new Player[2];
		private ScoreBoard sB1=new ScoreBoard(playersObj);
	//constructor
		
	//setter/getter
	
	//public methods
		public void reset(){
			if(playersObj[0]==null){
				getPlayerInfo();
			}else{
				this.playersObj[0].getFleet().setPositions(this.playersObj[0].getBoardDisplay().getBoundries());
				this.playersObj[1].getFleet().setPositions(this.playersObj[1].getBoardDisplay().getBoundries());
			}
			this.ifWinner=false;
			this.playersObj[0].getBoardDisplay().resetBoard();
			this.playersObj[1].getBoardDisplay().resetBoard();
			for(int i=0;i<this.playersObj.length;i++){
				if(!(this.playersObj[i] instanceof Bot)){
					this.playersObj[i].displayShipsPositions();
				}
			}
			for(int i=0;i<this.playersObj[0].getFleet().getShips().length;i++){
				this.playersObj[0].getFleet().getShips()[i].setIfSink(false);
				this.playersObj[1].getFleet().getShips()[i].setIfSink(false);
			}
		}
		public boolean play(){
			BSGUI.displayGameBoard(this.playersObj[0].getBoardDisplay());
			BSGUI.displayGameBoard(this.playersObj[1].getBoardDisplay());
			while(!this.ifWinner){
				this.turn++;
				this.turn=this.turn%2;
				int[] cord=new int[]{0,0};
				if(this.playersObj[this.turn] instanceof Bot){
					cord=this.playersObj[this.turn].takeTurn(this.playersObj[(this.turn+1)%2].getBoardDisplay().getBoundries(),this.playersObj[(this.turn+1)%2].getBoardDisplay().getSquares());
					takeShot(cord);
					cord=this.playersObj[this.turn].takeTurn(this.playersObj[(this.turn+1)%2].getBoardDisplay().getBoundries(),this.playersObj[(this.turn+1)%2].getBoardDisplay().getSquares());
					this.ifWinner=this.playersObj[(this.turn+1)%2].getFleet().checkIfAllSunk();
					if(!ifWinner){
						takeShot(cord);
						this.ifWinner=this.playersObj[(this.turn+1)%2].getFleet().checkIfAllSunk();
					}
				}else{
					cord=this.playersObj[this.turn].takeTurn(this.playersObj[(this.turn+1)%2].getBoardDisplay().getBoundries(),this.playersObj[(this.turn+1)%2].getBoardDisplay().getSquares());
					boolean hit=takeShot(cord);
					this.ifWinner=this.playersObj[(this.turn+1)%2].getFleet().checkIfAllSunk();
					if(hit&&!ifWinner){this.turn--;}
				}
				this.playersObj[0].stateDeadShips();
				this.playersObj[1].stateDeadShips();
				BSGUI.displayGameBoard(this.playersObj[0].getBoardDisplay());
				BSGUI.displayGameBoard(this.playersObj[1].getBoardDisplay());
				System.out.println();
				if(ifWinner){win();}
			}
			System.out.println("Would you like to play again?");
			this.playAgain=BSGUI.receiveBooleanReply();
			if(!this.playAgain){
				System.out.println(sB1.fancyToString());
			}
			return this.playAgain;
		}
	//private methods
		/**recieve player's name to create an object of players*/
		private void getPlayerInfo(){
			System.out.println("Please enter a name for player.");
			String tempName=BSGUI.receiveStringReply();
			this.playersObj[0]=new Bot("bot");
			this.playersObj[1]=new Player(tempName);
		}
		/**add scores everytime someone wins*/
		private void win(){
			System.out.println("Congrats, "+this.playersObj[this.turn].getName()+" wins the game!");
			this.playersObj[this.turn].setWins(this.playersObj[this.turn].getWins()+1);
			this.playersObj[this.turn].setLoses(this.playersObj[this.turn].getLoses()-1);
			for(int i=0;i<this.playersObj.length;i++){
				this.playersObj[i].setLoses(this.playersObj[i].getLoses()+1);
				this.playersObj[i].setWinPercent(Math.round((int)(100.0*this.playersObj[i].getWins()/(this.playersObj[i].getWins()+this.playersObj[i].getLoses()))));
			}
		}
		/**Take the shot and modify values in ships and display board. Return whether if any boat is shot.*/
		private boolean takeShot(int[] cord){
			if(this.playersObj[(this.turn+1)%2].getFleet().checkIfShot(cord)){
				this.playersObj[(this.turn+1)%2].getBoardDisplay().setCordValue(cord,'X');
				return true;
			}else{
				this.playersObj[(this.turn+1)%2].getBoardDisplay().setCordValue(cord,'M');
				return false;
			}
		}
}
