package Tennis;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private Map<Player, String> game;
	private Player player1;
	private Player player2;
	
	public Game(Player player1, Player player2){
		
		game = new HashMap<Player, String>();
		
		this.player1 = player1;
		this.player2 = player2;
		
		game.put(player1, "0");
		game.put(player2, "0");
	}
		
	public boolean playerWinPoint(Player player){
		boolean playerWon = false;
		Player otherPlayer = (player == player1 ? player2 : player1);
		
		if(game.get(player).equals(Constants.GAME.FIRST)){
			game.put(player, Constants.GAME.SECOND);
		}else if(game.get(player).equals(Constants.GAME.SECOND)){
			game.put(player, Constants.GAME.THIRD);
		}else if(game.get(player).equals(Constants.GAME.THIRD)){
			
			if(game.get(otherPlayer).equals(Constants.GAME.THIRD)){
				game.put(player, Constants.GAME.ADVANTAGE);
			}else if(game.get(otherPlayer).equals(Constants.GAME.ADVANTAGE)){
				game.put(otherPlayer, Constants.GAME.THIRD);
			}else{
				playerWon = true;
			}
			
		}else if(game.get(player).equals(Constants.GAME.ADVANTAGE)){
			playerWon = true;
		}
		
		return playerWon;
	}
	
}
