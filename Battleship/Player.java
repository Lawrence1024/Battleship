/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	Player is a object that contains information about a player. It knows its name, wins, loses,
*		win percentage, and more. Players could take turn in this class.
*/
public class Player{
	//fields
		static int id=-1;
		private String name;
		private int playerID;
		private int wins=0;
		private int loses=0;
		private int winPercent;
		private BoardDisplay b1;
		private Fleet f;
	//constructor
		Player(String name){
			id++;
			this.name=name;
			this.playerID=id;
			b1=new BoardDisplay(name);
			f=new Fleet(name);
			f.setPositions(b1.getBoundries());
		}
	//setter/getter
		public String getName(){return this.name;}
		public int getWins(){return this.wins;}
		public int getLoses(){return this.loses;}
		public int getWinPercent(){return this.winPercent;}
		public int getPlayerID(){return this.playerID;}
		public BoardDisplay getBoardDisplay(){return this.b1;}
		public Fleet getFleet(){return this.f;}
		public void setName(String name){this.name=name;}
		public void setWins(int wins){this.wins=wins;}
		public void setLoses(int loses){this.loses=loses;}
		public void setWinPercent(int winPercent){this.winPercent=winPercent;}
	//public methods
		public String toString(){
			String msg="";
			msg+=this.name+" ";
			msg+=this.playerID+" ";
			msg+=this.wins+" ";
			msg+=this.loses+" ";
			return msg;
		}
		public int[] takeTurn(int[] bSize,char[][] squares){
			boolean validInput=false;
			int[] cord=new int[2];
			while(!validInput){
				String statement=this.name+", please enter a valid coordinate here: ";
				cord=BSGUI.receiveCoordReply(statement,bSize);
				validInput=checkIfValid(bSize,cord[0],cord[1],squares);
			}
			return cord;
		}
		/**to let the display board know if there are any dead ships in the fleet*/
		public void stateDeadShips(){
			this.b1.setDeadShipsString(this.f.stateDeadShips());
		}
	//private methods
		/**returns whether the coordinate is valid*/
		protected boolean checkIfValid(int[] bSize,int firstNum,int secondNum,char[][] squares){
			if(firstNum>=bSize[0]||firstNum<0){
				return false;
			}
			if(secondNum>=bSize[1]||secondNum<0){
				return false;
			}
			if(squares[firstNum][secondNum]=='X'||squares[firstNum][secondNum]=='M'){
				return false;
			}
			return true;
		}
		/**To show the positions of the ships on display board*/
		protected void displayShipsPositions(){
			for(int i=0;i<this.b1.getRows();i++){
				for(int j=0;j<this.b1.getColumns();j++){
					String aliveString=this.f.checkIfAlive(new int[]{i,j});
					if(aliveString!=null){
						char aliveChar=aliveString.charAt(0);
						this.b1.setCordValue(new int[]{i,j},aliveChar);
					}else{
						this.b1.setCordValue(new int[]{i,j},'.');
					}
				}
			}
		}
}