/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	ScoreBoard is a class which would show the final score of the game. It shows each players
*		name, wins, loses, and win percent. Note that there is a fancy box displaying the information
*		and each column is perfectly alligned to the left.
*/
public class ScoreBoard{
	//fields
		private Player[] players;
	//constructor
		ScoreBoard(Player[] ply){
			this.players=ply;
		}
	//setter/getter
	
	//public methods
		public String toString(){
			String msg="";
			for(int i=0;i<this.players.length;i++){
				msg+=this.players[i].toString();
				msg+="\n";
			}
			return msg;
		}
		/**return a string of fancy board*/
		public String fancyToString(){
			String msg="";
			int longestNumChar=findLongestName();
			int highestWin=findHighestWin();
			int highestLose=findHighestLose();
			int highestPercent=findHighestPercent();
			int wholeLineLength=findWholeLineLength();
			msg+="Here is your fancy score board:\n";
			msg+=drawWidth(wholeLineLength);
			msg+="\n";
			for(int i=0;i<this.players.length;i++){
				int nameLength=longestNumChar-this.players[i].getName().length();
				msg+="* ";
				msg+=this.players[i].getName()+":";
				msg+=addSpaces(nameLength);
				msg+=" Win: "+this.players[i].getWins();
				if(highestWin>9&&this.players[i].getWins()<=9){
					msg+=addSpaces(1);
				}
				msg+=" Loses: "+this.players[i].getLoses();
				if(highestLose>9&&this.players[i].getLoses()<=9){
					msg+=addSpaces(1);
				}
				msg+=" Win Percent: "+this.players[i].getWinPercent()+"%";
				if(highestPercent>9&&this.players[i].getWinPercent()<=9){
					msg+=addSpaces(1);
				}
				if(highestPercent>99&&this.players[i].getWinPercent()<=99){
					msg+=addSpaces(1);
				}
				msg+=" *";
				msg+="\n";
			}
			msg+=drawWidth(wholeLineLength);
			return msg;
		}
	//private methods
		/**return a string of specific amount of spaces*/
		private String addSpaces(int num){
			String msg1="";
			for(int i=0;i<num;i++){
				msg1+=" ";
			}
			return msg1;
		}
		/**find the longest name of the players*/
		private int findLongestName(){
			int num=0;
			for(int i=0;i<this.players.length;i++){
				if(this.players[i].getName().length()>num){
					num=this.players[i].getName().length();
				}
			}
			return num;
		}
		/**given a specific amount, return a string of stars*/
		private String drawWidth(int num){
			String msg="";
			for(int i=0;i<num;i++){
				msg+="*";
			}
			return msg;
		}
		/**return the highest winning number among all players*/
		private int findHighestWin(){
			int num=0;
			for(int i=0;i<this.players.length;i++){
				if(this.players[i].getWins()>num){
					num=this.players[i].getWins();
				}
			}
			return num;
		}
		/**return the highest loseing number among all players*/
		private int findHighestLose(){
			int num=0;
			for(int i=0;i<this.players.length;i++){
				if(this.players[i].getLoses()>num){
					num=this.players[i].getLoses();
				}
			}
			return num;
		}
		/**return the highest win percent (a interger) among all players*/
		private int findHighestPercent(){
			int num=0;
			for(int i=0;i<this.players.length;i++){
				if(this.players[i].getWinPercent()>num){
					num=this.players[i].getWinPercent();
				}
			}
			return num;
		}
		/**return how long will the total line length be*/
		private int findWholeLineLength(){
			String msg="";
			int longestNumChar=findLongestName();
			int highestWin=findHighestWin();
			int highestLose=findHighestLose();
			int highestPercent=findHighestPercent();
				int nameLength=longestNumChar-this.players[0].getName().length();
				msg+="* ";
				msg+=this.players[0].getName()+":";
				msg+=addSpaces(nameLength);
				msg+=" Win: "+this.players[0].getWins();
				if(highestWin>9&&this.players[0].getWins()<=9){
					msg+=addSpaces(1);
				}
				msg+=" Loses: "+this.players[0].getLoses();
				if(highestLose>9&&this.players[0].getLoses()<=9){
					msg+=addSpaces(1);
				}
				msg+=" Win Percent: "+this.players[0].getWinPercent()+"%";
				if(highestPercent>9&&this.players[0].getWinPercent()<=9){
					msg+=addSpaces(1);
				}
				if(highestPercent>99&&this.players[0].getWinPercent()<=99){
					msg+=addSpaces(1);
				}
				msg+=" *";
			return msg.length();
		}
}