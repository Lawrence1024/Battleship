/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	Bot is a class which "is-a" Player. It is a child class of Player and it would randomly pick a valid coordinate
*		to attack the player's fleet.
*/
public class Bot extends Player{
	//fileds
	
	//constructor
		Bot(String name){
			super(name);
		}
	//setter/getter
	
	//public methods
		/**return a valid random coordinate*/
		public int[] takeTurn(int[] bSize,char[][] squares){
			boolean validInput=false;
			int num1=0;
			int num2=0;
			while(!validInput){
				num1=(int)(Math.random()*bSize[0]);
				num2=(int)(Math.random()*bSize[1]);
				validInput=super.checkIfValid(bSize,num1,num2,squares);
			}
			return new int[]{num1,num2};
		}
	//private methods
}