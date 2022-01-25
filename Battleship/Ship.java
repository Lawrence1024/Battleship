/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	Ship is a class of a single ship. It records all the positions that its ship parts are. It could check if the parts in ship
*	is shot and mark change the status of the parts of ship to dead.
*/
public class Ship{
	//fields
		private final int size;
		private String name;
		private int[][] positions;
		private boolean ifSink;
	//constructor
		Ship(int sz,String nm){
			this.size=sz;
			this.name=nm;
			this.positions=new int[sz][3];
			this.ifSink=false;
		}
	//setter/getter
		public void setIfSink(boolean ifSink){this.ifSink=ifSink;}
		public void setPositions(int[][] pos){this.positions=pos;}
		public int getSize(){return this.size;}
		public int[][] getPositions(){return this.positions;}
		public boolean getIfSink(){return this.ifSink;}
		public String getName(){return this.name;}
	//public methods
		public String toString(){
			String msg="";
			msg+="size: "+this.size+"\n";
			msg+="name: "+this.name+"\n";
			for(int i=0;i<this.positions.length;i++){
				for(int j=0;j<this.positions[i].length;j++){
					msg+=this.positions[i][j]+",";
				}
				msg+="\n";
			}
			return msg;
		}
		/**Returns if the specific box of boat is shot*/
		public boolean checkIfShot(int[] cord){
			for(int i=0;i<this.positions.length;i++){
				if(this.positions[i][0]==cord[0]&&this.positions[i][1]==cord[1]){
					this.positions[i][2]=-2;
					return true;
				}
			}
			return false;
		}
		/**return if the boat is sink*/
		public boolean checkIfSink(){
			for(int i=0;i<this.positions.length;i++){
				if(this.positions[i][2]==-1){
					return false;
				}
			}
			this.ifSink=true;
			return true;
		}
		/**return the first letter of the ship's name when there is a alive ship part in the given coordinate*/
		public String checkIfAlive(int[] cord){
			for(int i=0;i<this.positions.length;i++){
				if(this.positions[i][0]==cord[0]&&this.positions[i][1]==cord[1]){
					return this.name.substring(0,1);
				}
			}
			return null;
		}
	//private methods
}