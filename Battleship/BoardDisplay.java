/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	BoardDisplay is a class that holds information of the current board situation. It have the ability to reset itself and 
*		setters and getters to update and get the status of the board. Note that if you want to change
*		the size of the board in the game, you just have to modify values of rows and columns in the constructor.
*		The game will still function perfectly fine as long as the minimum size is 5*5 (Aircraftcarrier's length is 5).
*/
public class BoardDisplay{
	//fields
		private int columns;
		private int rows;
		private char[][] squares;
		private String owner;
		private String deadShipsString;
	//constructor
		BoardDisplay(String name){
			this.columns=11;
			this.rows=11;
			this.squares=new char[this.rows][this.columns];
			owner=name;
			resetBoard();
			deadShipsString="";
		}
	//setter/getter
		public int getColumns(){return columns;}
		public int getRows(){return rows;}
		public String getOwner(){return owner;}
		public char[][] getSquares(){return squares;}
		public void setCordValue(int[] cord,char token){this.squares[cord[0]][cord[1]]=token;}
		public int[] getBoundries(){return new int[]{this.rows,this.columns};}
		public String getDeadShips(){return this.deadShipsString;}
		public void setDeadShipsString(String msg){this.deadShipsString=msg;}
	//public methods
		/**reset every square on the board to dots*/
		public void resetBoard(){
			for(int i=0;i<this.squares.length;i++){
				for(int j=0;j<this.squares[i].length;j++){
					this.squares[i][j]='.';
				}
			}
		}
	//private methods
	
}