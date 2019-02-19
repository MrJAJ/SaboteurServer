package game.saboteur.biz.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import game.saboteur.biz.GameBiz;
import game.saboteur.vo.FunCardInfor;
import game.saboteur.vo.FunSpCardInfor;
import game.saboteur.vo.Game;
import game.saboteur.vo.Player;
import game.saboteur.vo.RoadCardInfor;

public class GameBizImpl implements GameBiz {
	private Game game;
	public int turn;
	public int bigwincount;
	public List<String>bigwinnerPlayer=new ArrayList<String>();
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public GameBizImpl(List<Player>players) {
		// TODO Auto-generated constructor stub
		game = new Game(players);
		game.getMap().add(new RoadCardInfor(67, true, "", 18));
		game.getMap().add(new RoadCardInfor(68, true, "", 8));
		game.getMap().add(new RoadCardInfor(69, true, "", 26));
		game.getMap().add(new RoadCardInfor(70, true, "", 44));
		
		Date date=new Date();
		Random random=new Random(date.getTime());
	/*	for(int i=0;i<game.getPlayers().size();i++){
			if(game.getPlayers().get(i).isHouseholder())
				game.getPlayers().get(i).setHouseholder(false);
		}*/
		game.startInning();
		turn=random.nextInt(game.getPlayers().size());
		System.out.println(turn);
		int set1=turn%game.getPlayers().size();
		players.get(turn).setTurn(true);
	}
//	@Override
	public Game UseFunSpCard(FunSpCardInfor funSpCardInfor) {
		// TODO Auto-generated method stub
		return game.UseFunSpCard(funSpCardInfor);
	}
//	@Override
	public Game UseRoadCard(RoadCardInfor roadCardInfor) {
		// TODO Auto-generated method stub
	
		return game.UseRoadCard(roadCardInfor);
	}
//	@Override
	public Game UseFunCard(FunCardInfor funCardInfor) {
		// TODO Auto-generated method stub
		return game.UseFunCard(funCardInfor);
	}
//	@Override
	public int getcard() {
		// TODO Auto-generated method stub
		Player thisplayer=new Player();
		for(Player player:game.getPlayers()){
			
			if(player.isTurn()){
				thisplayer=player;
				player.setTurn(false);
				break;
			}
		}
		turn++;
		int set1 = turn%game.getPlayers().size();
		game.getPlayers().get(set1).setTurn(true);
		int n = game.getcard();
		if(n<0)
			return n;
		
		thisplayer.getHandcards().add(n);
				
		
		return n;
	}
	
//	@Override
	public Game giveUpCard(int deletedcards) {
		// TODO Auto-generated method stub
		Player player = new Player();
		for(int i=0;i<game.getPlayers().size();i++){
			if(game.getPlayers().get(i).isTurn()){
				player=game.getPlayers().get(i);
				break;
			}
		}
		game.setAction(player.getNickname()+"弃牌");
		for(int i=0;i<player.getHandcards().size();i++){
			if(player.getHandcards().get(i)==deletedcards)
			{
				player.getHandcards().remove(i);
				break;
			}
		}
		int count=0;
		for(int i=0;i<game.getPlayers().size();i++){
			if(game.getPlayers().get(i).getHandcards().size()==0)
				count++;
		}
		if(count==game.getPlayers().size())
			return null;
		
		
		return game;
		
	}
	
	public void deletecard(int deletedcards){
		Player player = new Player();
		for(int i=0;i<game.getPlayers().size();i++){
			if(game.getPlayers().get(i).isTurn()){
				player=game.getPlayers().get(i);
				break;
			}
		}
		for(int i=0;i<player.getHandcards().size();i++){
			if(player.getHandcards().get(i)==deletedcards)
			{
				player.getHandcards().remove(i);
				break;
			}
		}
	}
	
	public Game gameEnd(int num){
		game.endInning(num);
		if(game.getInning()==3){
			int most=0;
			for(Player player: game.getPlayers()){
				if(most<player.getTempcount())
					most=player.getTempcount();
				
			}
			for(Player player:game.getPlayers()){
				if(player.getTempcount()==most){
					bigwinnerPlayer.add(player.getNickname());
					bigwincount=most;
				}
			}
			
			return null;
		}
		else{
		//	game.startInning();
			return game;
		}
		
	}
}
