/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	Fleet is a class and it owns a array of ships. It have the ability to set random positions for the ships and check 
*	if the ships are hit. It could also check if all ships in the fleet are sunk.
*/
public class Fleet{
	//fields
		private String owner;
		private int size;
		private Ship[] ships;
	//constructor
		Fleet(String owner){
			Ship s1=new Ship(5,"AircraftCarrier");
			Ship s2=new Ship(4,"Battleship");
			Ship s3=new Ship(3,"Cruiser");
			Ship s4=new Ship(3,"Submarine");
			Ship s5=new Ship(2,"Destroyer");
			Ship[] sp=new Ship[]{s1,s2,s3,s4,s5};
			this.ships=sp;
			this.size=sp.length;
			this.owner=owner;
		}
	//setter/getter
		public String getOwner(){return this.owner;}
		public int getSize(){return this.size;}
		public Ship[] getShips(){return this.ships;}
	//public methods
		public String toString(){
			String msg="";
			msg+="Owner: "+this.owner+"\n";
			for(int i=0;i<this.ships.length;i++){
				msg+=ships[i].toString();
				msg+="\n";
			}
			return msg;
		}
		/**give boats random position and make sure the positions are valid*/
		public void setPositions(int[] boardBounds){
			boolean allGood=false;
			while(!allGood){
				doActualSettingPositions(boardBounds);
				allGood=checkIfValidPositions(boardBounds);
			}
		}
		/**check every boat that if any of them get shot, return whether any of them get shot*/
		public boolean checkIfShot(int[] cord){
			for(int i=0;i<this.ships.length;i++){
				boolean getShot=this.ships[i].checkIfShot(cord);
				if(getShot){
					return true;
				}
			}
			return false;
		}
		/**To check if a specific coordinate of boat is alive or not. Return the initial of the boat name
		*	if that coordinate have a alive boat body. Return null if there isn't a alive boat body there.
		*/
		public String checkIfAlive(int[] cord){
			for(int i=0;i<this.ships.length;i++){
				String aliveString=this.ships[i].checkIfAlive(cord);
				if(aliveString!=null){
					return aliveString;
				}
			}
			return null;
		}
		/**return a string with all dead ship's names*/
		public String stateDeadShips(){
			String msg="";
			for(int i=0;i<this.ships.length;i++){
				if(this.ships[i].checkIfSink()){
					msg+=this.ships[i].getName()+", ";
				}
			}
			return msg;
		}
		/**return whether all the boats are sunk*/
		public boolean checkIfAllSunk(){
			for(int i=0;i<this.ships.length;i++){
				if(!this.ships[i].checkIfSink()){
					return false;
				}
			}
			return true;
		}
	//private methods
		/**create a random coordinate for one of the boat to start at*/
		private int[] setAnchor(int[] bounds){
			int num1=(int)(Math.random()*bounds[0]);
			int num2=(int)(Math.random()*bounds[1]);
			return new int[]{num1,num2};
		}
		/**return a random number from 1 to 4 that indicates the dirction of the boat*/
		private int getDirction(){
			return (int)(Math.random()*4)+1;
		}
		/**give each boat and random position in a random dirction (Boats could be vertical or horizontal).*/
		private void doActualSettingPositions(int[] boardBounds){
			for(int k=0;k<this.ships.length;k++){
				int[][] tempPositions=new int[this.ships[k].getSize()][3];
				int[] tempAnchor=setAnchor(boardBounds);
				int direction=getDirction();
				for(int i=0;i<this.ships[k].getSize();i++){
					tempPositions[i][2]=-1;
					if(direction==1){
						tempPositions[i][0]=tempAnchor[0];
						tempPositions[i][1]=tempAnchor[1]+i;
					}else if(direction==3){
						tempPositions[i][0]=tempAnchor[0];
						tempPositions[i][1]=tempAnchor[1]-i;
					}else if(direction==2){
						tempPositions[i][0]=tempAnchor[0]+i;
						tempPositions[i][1]=tempAnchor[1];
					}else if(direction==4){
						tempPositions[i][0]=tempAnchor[0]-i;
						tempPositions[i][1]=tempAnchor[1];
					}
				}
				this.ships[k].setPositions(tempPositions);
			}
		}
		/**check if the positions are valid (no overlapping or out of bound)*/
		private boolean checkIfValidPositions(int[] boardBounds){
			int counter=0;
			for(int i=0;i<this.ships.length;i++){
				for(int j=0;j<this.ships[i].getSize();j++){
					counter++;
				}
			}
			int[][] allPositions=new int[counter][2];
			counter=0;
			for(int i=0;i<this.ships.length;i++){
				for(int j=0;j<this.ships[i].getSize();j++){
					int x=this.ships[i].getPositions()[j][0];
					int y=this.ships[i].getPositions()[j][1];
					if(x<0||x>=boardBounds[0]||y<0||y>=boardBounds[1]){
						return false;
					}else{
						allPositions[counter][0]=x;
						allPositions[counter][1]=y;
						counter++;
					}
				}
			}
			for(int i=0;i<allPositions.length;i++){
				for(int j=0;j<allPositions.length;j++){
					if((allPositions[i][0]==allPositions[j][0])&&(allPositions[i][1]==allPositions[j][1])&&(i!=j)){
						return false;
					}
				}
			}
			return true;
		}
}