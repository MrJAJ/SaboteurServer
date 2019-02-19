package game.saboteur.vo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private List<Player> players=new ArrayList<Player>();
	private List<Integer>cards = new ArrayList<Integer>();
	private String action;
	private List<RoadCardInfor>map= new ArrayList<RoadCardInfor>();
	private int currentcardsposition;
	private int inning;
	private int turn=0;
	private int place=1;
	public List<String> getWinner() {
		return winner;
	}


	public void setWinner(List<String> winner) {
		this.winner = winner;
	}


	public List<Integer> getInningscore() {
		return inningscore;
	}


	public void setInningscore(List<Integer> inningscore) {
		this.inningscore = inningscore;
	}
	private List<String> winner;
	private List<Integer> inningscore;
	public List<Player> getPlayers() {
		return players;
	}
	

	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	public int getTurn() {
		return turn;
	}


	public void setTurn(int turn) {
		this.turn = turn;
	}


	public List<Integer> getCards() {
		return cards;
	}


	public void setCards(List<Integer> cards) {
		this.cards = cards;
	}


	public String getAction() {
		return action;
	}


	public int getInning() {
		return inning;
	}


	public void setInning(int inning) {
		this.inning = inning;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public List<RoadCardInfor> getMap() {
		return map;
	}


	public void setMap(List<RoadCardInfor> map) {
		this.map = map;
	}


	public int getCurrentcardsposition() {
		return currentcardsposition;
	}


	public void setCurrentcardsposition(int currentcardsposition) {
		this.currentcardsposition = currentcardsposition;
	}


	public Game(List<Player>players){
		this.players=players;		
		currentcardsposition=0;
		inning=0;
	}
	
	
	public Game UseRoadCard(RoadCardInfor roadCardInfor){
		this.map.add(roadCardInfor);
		this.action = roadCardInfor.getNickname()+"使用道路牌";
		for(Player player:players){
			if(player.getNickname().equals(roadCardInfor.getNickname()))
			{
				
				for(int i=0;i<player.getHandcards().size();i++){
					if(player.getHandcards().get(i)==roadCardInfor.getCardorder()){
						player.getHandcards().remove(i);
						break;
					}
				}
				break;
			}
		}
		return this;
	}
	
	public Game UseFunSpCard(FunSpCardInfor funSpCardInfor){
		if(funSpCardInfor.getFunction().equals("collapse")){
			this.action = funSpCardInfor.getActiveplayer()+"使用塌方牌";
			for(RoadCardInfor roadCardInfor:map){
				if(roadCardInfor.getOffset()==funSpCardInfor.getOffset())
				{
					map.remove(roadCardInfor);
					break;
				}
			}
		}else if(funSpCardInfor.getFunction().equals("foresight")){
			if(funSpCardInfor.getOffset()==8){
				this.action = funSpCardInfor.getActiveplayer()+"观看左边的目标牌";
			}
			if(funSpCardInfor.getOffset()==26){
				this.action = funSpCardInfor.getActiveplayer()+"观看中间目标牌";
			}
			if(funSpCardInfor.getOffset()==44){
				this.action = funSpCardInfor.getActiveplayer()+"观看了右边的目标牌";
			}
		}
		/*for(Player player:players){
			if(player.getNickname().equals(funSpCardInfor.getActiveplayer()))
			{
				for(int i=0;i<player.getHandcards().size();i++){
					if(player.getHandcards().get(i)==funSpCardInfor.getCardorder()){
						player.getHandcards().remove(i);
						break;
					}
				}
				break;
			}
		}*/
		return this;
	}
	
	
	public Game UseFunCard(FunCardInfor funCardInfor){
		if(funCardInfor.getFunction().equals("destroycart")){
			action = funCardInfor.getActiveplayer()+"破坏"+funCardInfor.getPassiveplayer()+"的矿车";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setMinecar(false);
					break;
				}
			}
		}else if(funCardInfor.getFunction().equals("destroyhammer")){
			action = funCardInfor.getActiveplayer()+"破坏"+funCardInfor.getPassiveplayer()+"的镐头";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setPick(false);
					break;
				}
			}
		}else if(funCardInfor.getFunction().equals("destroylight")){
			action = funCardInfor.getActiveplayer()+"破坏"+funCardInfor.getPassiveplayer()+"的矿灯";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setLamp(false);
					break;
				}
			}
		}
		else if(funCardInfor.getFunction().equals("fixlight")){
			action = funCardInfor.getActiveplayer()+"修复"+funCardInfor.getPassiveplayer()+"的矿灯";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setLamp(true);
					break;
				}
			}
		}else if(funCardInfor.getFunction().equals("fixhammer")){
			action = funCardInfor.getActiveplayer()+"修复"+funCardInfor.getPassiveplayer()+"的镐头";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setPick(true);
					break;
				}
			}
		}else if(funCardInfor.getFunction().equals("fixcart")){
			action = funCardInfor.getActiveplayer()+"修复"+funCardInfor.getPassiveplayer()+"的矿车";
			for(Player player:players){
				if(player.getNickname().equals(funCardInfor.getPassiveplayer())){
					player.setMinecar(true);
					break;
				}
			}
		}
	/*	for(Player player:players){
			if(player.getNickname().equals(funCardInfor.getActiveplayer()))
			{
				for(int i=0;i<player.getHandcards().size();i++){
					if(player.getHandcards().get(i)==funCardInfor.getCardorder()){
						player.getHandcards().remove(i);
						break;
					}
				}
				break;
			}
		}*/
		return this;
	}
	public int cardLeave(){
		return cards.size()-currentcardsposition;
	}
	
	public int getcard(){				
		if(currentcardsposition<cards.size())
			return cards.get(currentcardsposition++);
		else 
			return -1;
	}
	
	void allocate(Player player,int num){
		ArrayList<Integer>handcards=new ArrayList<Integer>();
		for(int j=0;j<num;j++){		
			handcards.add(getcard());
		}
		player.setHandcards(handcards);
	}
	
	void allocateIden(int bad,int good){
		for(Player player:players){
			player.setIdentify(1);
		}
		Date date=new Date();
		Random random = new Random(date.getTime());
		int all = bad+good;
		int idty = random.nextInt(all);
		if(idty>=good){
			for(int i=0;i<bad-1;i++){
				int idty1 = random.nextInt(all-1);
				if(players.get(idty1).getIdentify()==1)
					players.get(idty1).setIdentify(0);
				else {
					i--;
				}
			}
		}else {
			for(int i=0;i<bad;i++){
				int idty1 = random.nextInt(all-1);
				if(players.get(idty1).getIdentify()==1)
					players.get(idty1).setIdentify(0);
				else {
					i--;
				}
			}
		}
		
	}
	
	public void startInning(){
		inningscore = new ArrayList<Integer>();
		winner = new ArrayList<String>();
		inning++;
		Date date =new Date();
		Random random = new Random(date.getTime());
		
		//初始化牌堆
		while(cards.size()<67){
			
			int a = random.nextInt(67);
			boolean if_add = true;
			for(int i=0;i<cards.size();i++){
				if(a==cards.get(i)){
					if_add=false;
					break;
				}
			}
			if(if_add){
				cards.add(a);
				
			}
			
		}
		//据人数随机身份
		
		if(players.size()==2){
			allocateIden(1, 2);
		}else if(players.size()==3){
			allocateIden(1, 3);
		}else if(players.size()==4){
			allocateIden(2, 3);
		}else if(players.size()==5){
			allocateIden(2, 4);
		}else if(players.size()==6){
			allocateIden(3, 4);
		}else if(players.size()==7){
			allocateIden(3, 5);
		}else if(players.size()==8){
			allocateIden(3, 6);
		}else if(players.size()==9){
			allocateIden(3, 7);
		}else if(players.size()==10){
			allocateIden(4, 7);
		}
		//分发手牌
		
		if(players.size()<=5){
			
			for(Player player:players)
				allocate(player, 6);
		}else if(players.size()<=7){
			for(Player player:players)
				allocate(player, 5);
		}else{
			for(Player player:players)
				allocate(player, 4);
		}
		
		for(Player player:players){
			player.setLamp(true);
			player.setMinecar(true);
			player.setPick(true);
			player.setTempcount(0);
		}
	
		
	}
	public Game endInning(int num){
		int winnercount=0;
		if(num==0){
			action="坏胚子获胜";
			for(Player player:players){
				if(player.getIdentify()==0){
					winner.add(player.getNickname());
					winnercount++;
				}
			}
			if(winnercount==1){
				inningscore.add(4);
				for(Player player:players){
					if(player.getIdentify()==0){
						player.setTempcount(player.getTempcount()+4);
					}
				}
			}else if(winnercount==4){
				for(int i=0;i<4;i++)
					inningscore.add(2);
				for(Player player:players){
					if(player.getIdentify()==0){
						player.setTempcount(player.getTempcount()+2);
					}
				}
			}else{
				for(int i=0;i<winnercount;i++)
					inningscore.add(3);
				for(Player player:players){
					if(player.getIdentify()==0){
						player.setTempcount(player.getTempcount()+3);
					}
				}
			}
			
		}else{
			action = "矿工获胜";
			Date date=new Date();
			Random random = new Random(date.getTime());
				
			for(Player player:players){
				if(player.getIdentify()==1){
					if(player.isLamp()&&player.isMinecar()&&player.isPick()){
						winnercount++;
						winner.add(player.getNickname());
						int ss=random.nextInt(3)+1;
						inningscore.add(ss);
						player.setTempcount(player.getTempcount()+ss);
					}
				}				
			}
			

		}
		return this;
	}


	public int getPlace() {
		return place;
	}


	public void setPlace(int place) {
		this.place = place;
	}
	
	
}


