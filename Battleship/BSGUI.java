/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	BSGUI is a subclass of GUI and it could display the game board and return a coordinate reply in int[] form.
*/
public class BSGUI extends GUI{
	//fields
		
	//constructor
		
	//setter/getter
		
	//public methods
		/**in take a board display and display the board in a fancy way*/
		public static void displayGameBoard(BoardDisplay b1){
			char[][] squares=b1.getSquares();
			String tempMsg="";
			System.out.println("*********************************");
			System.out.println(b1.getOwner()+"'s board");
			int num=b1.getDeadShips().length();
			if(num!=0){num=num-2;}
			System.out.println("Dead Ships: "+b1.getDeadShips().substring(0,num));
			if(b1.getRows()>=10){
					System.out.print("   ");
				}else{
					System.out.print("  ");
				}
			for(int i=65;i<b1.getColumns()+65;i++){
				System.out.print(Character.toChars(i));
				System.out.print(" ");
			}
			System.out.print("\n");
			for(int i=0;i<squares.length;i++){
				if(b1.getRows()>=10&&i<9){
					System.out.print((i+1)+"  ");
				}else{
					System.out.print((i+1)+" ");
				}
				for(int j=0;j<squares[i].length;j++){
					System.out.print(squares[i][j]+" ");
				}
				System.out.print("\n");
			}
		}
		/**return a valid coordinate that user inputed*/
		public static int[] receiveCoordReply(String requestStatement,int[] bSize){
			int firstNum=0;
			int secondNum=0;
			String msg="";
			String firstString="";
			boolean valid=false;
			while(!valid){
				System.out.print(requestStatement);
				msg=receiveStringReply();
				if(!(msg.length()<2||msg.length()>3)){
					int[] numPos=identifyNumPos(msg);
					if(numPos[0]!=-1){
						valid=true;
						secondNum=findSecondNum(numPos,msg)-1;
					}
					firstNum=findFirstNum(msg,numPos);
				}
			}
			return new int[]{secondNum,firstNum};
		}
	//private methods
		/**Find the column position of the cordinate given user input*/
		private static int findFirstNum(String msg,int[] pos){
			String firstString="";
			if(pos[0]!=0){
				firstString=msg.substring(0,1).toUpperCase();
			}else{
				int num=msg.length();
				firstString=msg.substring(num-1,num).toUpperCase();
			}
			char tempC=firstString.charAt(0);
			int firstNum=Character.getNumericValue(tempC)-10;
			return firstNum;
		}
		/**return the indexs which the number protion is in between, given user input*/
		private static int[] identifyNumPos(String msg){
			String first=msg.substring(0,1);
			String second=msg.substring(1,2);
			String third="";
			if(msg.length()==3){
				third=msg.substring(2,3);
				if((!isNumeric(first)&&!isNumeric(second))||(!isNumeric(first)&&!isNumeric(third))||(!isNumeric(third)&&!isNumeric(second))){
					return new int[]{-1,-1};
				}
				if(isNumeric(first)&&isNumeric(second)){
					return new int[]{0,2};
				}else if(isNumeric(second)&&isNumeric(third)){
					return new int[]{1,3};
				}else if(isNumeric(first)){
					return new int[]{0,1};
				}else if(isNumeric(third)){
					return new int[]{2,3};
				}
			}else if(msg.length()==2){
				if(isNumeric(first)){
					return new int[]{0,1};
				}else if(isNumeric(second)){
					return new int[]{1,2};
				}
			}
			return new int[]{-1,-1};
		}
		/**return the row value of coordinate, given user input and position of the interger within the user input*/
		private static int findSecondNum(int[] pos,String msg){
			return Integer.valueOf(msg.substring(pos[0],pos[1]));
		}
}






