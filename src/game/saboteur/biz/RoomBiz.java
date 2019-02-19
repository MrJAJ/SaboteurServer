package game.saboteur.biz;

import game.saboteur.vo.Game;
import game.saboteur.vo.Player;
import game.saboteur.vo.Room;

public interface RoomBiz {
	Room addPlayer(Player player);
	Room deletePlayer(String nickname);
	GameBiz GameStart();
	
}
