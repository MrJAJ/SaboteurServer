package game.saboteur.vo;

import game.saboteur.biz.impl.GameBizImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{
	/**
	 * 
	 */
	public GameBizImpl gameBiz;
	
	private static final long serialVersionUID = 1L;
	private int num;//房间容纳人数总数
	String roomid;//房间id,默认显示为id
	private int currentnum;
	public int port;
	public SaboteurServer SaboteurServer;
	private List<Player> players=new ArrayList<Player>();//玩家列表

	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
	public Room() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Room(int num, int currentnum, String roomid, List<Player> players,int port,SaboteurServer SaboteurServer) {
		
		this.SaboteurServer=SaboteurServer;
		this.num = num;
		this.setCurrentnum(currentnum);
		this.roomid = roomid;
		this.players = players;
		this.port=port;
		gameBiz=null;
	}
	//玩家创建房间时的构造
	public Room(int num,Player player) {
		// TODO Auto-generated constructor stub
		this.num=num;
		String id=player.getNickname()+"的房间";
	
		this.addPlayer(player);
		gameBiz=null;
		
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	//添加一个玩家
	public Room addPlayer(Player player){
		players.add(player);
		setCurrentnum(getCurrentnum() + 1);
		return this;
	}
	//删除一个玩家
	public void deletePlayer(Player player){
			setCurrentnum(getCurrentnum() - 1);
			players.remove(player);	
	}
	public Player findPlayerByNick(String nickname){
		for(int index=0;index<players.size();index++){
			if(players.get(index).getNickname().equals(nickname)){
				return players.get(index);
			}
		}
		return null;
	}
	public int getCurrentnum() {
		return currentnum;
	}
	public void setCurrentnum(int currentnum) {
		this.currentnum = currentnum;
	}
	public GameBizImpl getGameBiz() {
		return gameBiz;
	}
	public void setGameBiz(GameBizImpl gameBiz) {
		this.gameBiz = gameBiz;
	}
	
}
