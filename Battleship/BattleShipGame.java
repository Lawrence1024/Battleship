/**	Author: Lawrence Shieh
*	Version: 1.7
*	Date: 10/19/2019
*	BattleShipGame is the main class which creates a battle ship game and start playing. It resets before
*		game starts and determins whether to play again.
*/
public class BattleShipGame{
	public static void main(String[] args){
		Game game1=new Game();
		boolean playAgain=true;
		while(playAgain){
			game1.reset();
			playAgain=game1.play();
		}
	}
}
