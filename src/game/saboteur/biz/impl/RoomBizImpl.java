package game.saboteur.biz.impl;

import java.util.Random;

import game.saboteur.biz.GameBiz;
import game.saboteur.biz.RoomBiz;
import game.saboteur.vo.FunCardInfor;
import game.saboteur.vo.Game;
import game.saboteur.vo.Player;
import game.saboteur.vo.Room;

public class RoomBizImpl implements RoomBiz {
	private Room room;
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public RoomBizImpl(Room room){
		this.room=room;
	}
	public RoomBizImpl(int num,Player player) {
		// TODO Auto-generated constructor stub
		room=new Room(num,player);
	}
	public Room addPlayer(Player player){
		if(room.getPlayers().size()<room.getNum())
			return room.addPlayer(player);
		else 
			return null;
	}
	public Room deletePlayer(String nickname){
		boolean ra=false;
		Player player=room.findPlayerByNick(nickname);
		if(player!=null){
			if(player.isHouseholder()){
				ra=true;
			}
			room.deletePlayer(player);
		}
		if(room.getPlayers().size()==0){		
			return null;
		}
		if(ra){
			Random random=new Random(47);
			int index = random.nextInt(room.getPlayers().size());
			room.getPlayers().get(index).setHouseholder(true);
		}
		return room;
	}
	@Override
	public GameBizImpl GameStart() {
		// TODO Auto-generated method stub
		if(room.getPlayers().size()>1)//==room.getNum())
		{
			room.gameBiz= new GameBizImpl(room.getPlayers());			
			return room.gameBiz;
		}
		else 
			return null;
		
	}
	
}
