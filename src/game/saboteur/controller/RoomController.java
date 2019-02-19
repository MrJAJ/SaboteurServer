package game.saboteur.controller;

import game.saboteur.biz.UserBiz;
import game.saboteur.biz.impl.RoomBizImpl;
import game.saboteur.biz.impl.UserBizImpl;
import game.saboteur.po.User;
import game.saboteur.vo.DinHall;
import game.saboteur.vo.FunCardInfor;
import game.saboteur.vo.FunSpCardInfor;
import game.saboteur.vo.Game;
import game.saboteur.vo.Info;
import game.saboteur.vo.Player;
import game.saboteur.vo.RoadCardInfor;
import game.saboteur.vo.Room;
import game.saboteur.vo.SaboteurServer;
import game.saboteur.vo.SaboteurSocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;



public class RoomController {
	static DinHall dinHall=new DinHall();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 //定义一个SaboteurServer监听在端口900上 		
		SaboteurServer ser=new SaboteurServer(900);		
		while(true){
			//server尝试接收其他socket的连接请求，server的accept方法是阻塞式的  
	
			Socket socket=ser.accept();
		
			
			//每接收到一个socket就建立一个新的线程来处理它  
			new Thread(new Roomrun(socket)).start();			
		}
		}
	public static class Roomrun implements Runnable{
		private Socket socket;
		public Roomrun() {
			// TODO Auto-generated constructor stub
		}
		
		public Roomrun(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket=socket;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				RoomRun();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	private  void RoomRun() throws IOException, ClassNotFoundException  {
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream())); 
			Info info=new Info();
			info=(Info) is.readObject();					
			String option=info.getOption();
			//刷新房间列表
			if(option.equals("roominfo")){
				
				info.setOption("房间列表");
				
				User us=(User) info.getObject();				
				UserBiz userBiz=new UserBizImpl();
				userBiz.updateUserPhoto(us.getNickname(), us.getUserphoto(),us.getScore());
				User user=userBiz.userLogin(us.getUsername(),us.getPassword());
				
				info.setObject(user);				
				if(dinHall.getRoomList()!=null){
					
				for(RoomBizImpl room:dinHall.getRoomList()){
					List<Player> playerss=new ArrayList<Player>();
					for(Player ppp:room.getRoom().getPlayers()){
						playerss.add(new Player(ppp.getNickname(),ppp.getIdentify(),ppp.getTempcount(),ppp.isHouseholder(),null,ppp.getPhotoNum()));
					}
					info.getList().add(new Room(room.getRoom().getNum(), room.getRoom().getCurrentnum(), room.getRoom().getRoomid(), playerss, room.getRoom().port, room.getRoom().SaboteurServer));
					
				}
				}
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
				ou.writeObject(info);				
				ou.flush();
				ou.close();
				}
			//新建房间
			if(option.equals("buildroom")){	
			Room room=(Room) info.getObject();
			SaboteurServer roomser=new SaboteurServer(0); 			
			int port=roomser.getLocalPort();
			room.port=port;
			room.SaboteurServer=roomser;
			info.setObject(room);	
			ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
			ou.writeObject(info);			
			ou.flush();
			dinHall.addRoom(room);	
			
			while(true){			
				Socket socket=roomser.accept();
				//每接收到一个socket就建立一个新的线程来处理它  
				new Thread(new gameRun(socket)).start();				
			}
			}
			//加入房间
			if(option.equals("joinin")){
				Room room=(Room) info.getObject();
				
				for(RoomBizImpl roo:dinHall.getRoomList()){
					if(room.port==roo.getRoom().port){						
						if(roo.addPlayer(room.getPlayers().get(room.getPlayers().size()-1))!=null)							
						{
						//将传回player的socket属性置为null
							List<Player> playerss=new ArrayList<Player>();
							for(Player ppp:roo.getRoom().getPlayers()){
								playerss.add(new Player(ppp.getNickname(),ppp.getIdentify(),ppp.getTempcount(),ppp.isHouseholder(),null,ppp.getPhotoNum()));
							}					
						Room rrrRoom=new Room(roo.getRoom().getNum(),roo.getRoom().getCurrentnum(), roo.getRoom().getRoomid(), playerss, roo.getRoom().port, roo.getRoom().SaboteurServer);						
						info.setObject(rrrRoom);
						
						}
						else {
							info.setOption("full");
						}
						
					}
				}
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
				ou.writeObject(info);
				ou.flush();
				ou.close();					
				is.close();
				
	}
			//刷新列表
			if(option.equals("dorefresh")){
				Room room=(Room) info.getObject();				
				
				for(RoomBizImpl roo:dinHall.getRoomList()){
					if(room.port==roo.getRoom().port){				
						Room RRRRR=(Room)info.getObject();											
						List<Player> playerss=new ArrayList<Player>();
						for(Player ppp:roo.getRoom().getPlayers()){
							playerss.add(new Player(ppp.getNickname(),ppp.getIdentify(),ppp.getTempcount(),ppp.isHouseholder(),null,ppp.getPhotoNum()));
						}					
					Room rrrRoom=new Room(roo.getRoom().getNum(),roo.getRoom().getCurrentnum(), roo.getRoom().getRoomid(), playerss, roo.getRoom().port, roo.getRoom().SaboteurServer);					
					info.setObject(rrrRoom);
					}					
					}
				
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
				ou.writeObject(info);
				ou.flush();
				ou.close();					
				
			}
			if(option.equals("savesocket")){
				Room room=(Room) info.getObject();				
				for(RoomBizImpl roo:dinHall.getRoomList()){
					if(room.port==roo.getRoom().port){
						for(Player player:roo.getRoom().getPlayers()){
							if(player.getNickname().equals(room.getPlayers().get(0).getNickname())){
								player.socket=socket;
							}
						}																
					}					
					}														
			}
			if(option.equals("gostart")){			
				Room room=(Room) info.getObject();
				Room room2=new Room();				
				for(RoomBizImpl roo:dinHall.getRoomList()){
					if(room.port==roo.getRoom().port){
						room2=roo.getRoom();						
						Game game=roo.GameStart().getGame();						
						List<Player> playerss=new ArrayList<Player>();
						for(Player ppp:game.getPlayers()){							
							ArrayList<Integer>ssss=new ArrayList<Integer>();
							Player newplPlayer=new Player(ppp.getNickname(),ppp.getIdentify(),ppp.getTempcount(),ppp.isHouseholder(),null,ppp.isTurn(),ppp.getPhotoNum());																			
							for(int i=0;i<ppp.getHandcards().size();i++){
								ssss.add(ppp.getHandcards().get(i));
								newplPlayer.handcards.add(ppp.getHandcards().get(i));
							}
							playerss.add(newplPlayer);	
						}
						
						Date date=new Date();
						int ran=(int) (date.getTime()%3);
						ran+=1;
						game.setPlace(ran);
						
						
						game.setPlayers(playerss);						
						info.setObject(game);
						}					
					}
			
				//Game g=(Game) info.getObject();
				
				for(Player p:room2.getPlayers()){					
					ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
					ou.writeObject(info);
					ou.flush();
						
				}
			}
			if(option.equals("exit")){
				
				Room room=(Room) info.getObject();
				for(RoomBizImpl roo:dinHall.getRoomList()){
					if(room.port==roo.getRoom().port){
						if(roo.deletePlayer(room.getPlayers().get(room.getPlayers().size()-1).getNickname())!=null)
						{
							List<Player> playerss=new ArrayList<Player>();
							for(Player ppp:roo.getRoom().getPlayers()){
								playerss.add(new Player(ppp.getNickname(),ppp.getIdentify(),ppp.getTempcount(),ppp.isHouseholder(),null,ppp.getPhotoNum()));
							}					
						Room rrrRoom=new Room(roo.getRoom().getNum(),roo.getRoom().getCurrentnum(), roo.getRoom().getRoomid(), playerss, roo.getRoom().port, roo.getRoom().SaboteurServer);
						
						info.setObject(rrrRoom);
						
						for(int i=0;i<roo.getRoom().getPlayers().size();i++){
							Player player=roo.getRoom().getPlayers().get(i);
}
						}
						else {
							
							dinHall.deleteRoom(room.port);
							break;
						}						
					}
					else{
						System.out.println("房间没找到");
					}
					}
				
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
				ou.writeObject(info);
				ou.flush();
				ou.close();					
				is.close();
			}
				
	}
	}

public static class gameRun implements Runnable{
	private Socket socket;
	public gameRun() {
		// TODO Auto-generated constructor stub
	}
	public gameRun(Socket socket) {
		// TODO Auto-generated constructor stub
		//this.setsocket(socket);
		this.socket=socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			playGame();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playGame() throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream())); 
		Info info=new Info();
		info=(Info) is.readObject();						
		String option=info.getOption();
		
		if(option.equals("tosavesocket")){
		// TODO Auto-generated method stub
		Room room=(Room) info.getObject();
		Room room2=new Room();
		
		for(RoomBizImpl roo:dinHall.getRoomList()){
		if(room.port==roo.getRoom().port){
			for(Player player:roo.getRoom().getPlayers()){
				if(player.getNickname().equals(room.getPlayers().get(0).getNickname())){
					player.socket=socket;
					room2=roo.getRoom();
				}
			}																		
		}		
		}	
		}
		//打出功能牌
		if(option.equals("sendFunCardInfor")){
			FunCardInfor fcif=(FunCardInfor) info.getObject();
			int port=info.getPort();
			Game game = null;
			Room room2=new Room();
			for(RoomBizImpl roo:dinHall.getRoomList()){
				if(port==roo.getRoom().port){
					room2=roo.getRoom();
					roo.getRoom().getGameBiz().UseFunCard(fcif);
					roo.getRoom().getGameBiz().deletecard(fcif.getCardorder());
					roo.getRoom().getGameBiz().getcard();
					game=roo.getRoom().getGameBiz().getGame();
				}
				}
			info.setObject(game);
			info.setOrder(fcif.getCardorder());
			for(Player p:room2.getPlayers()){
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
				ou.writeObject(info);
				ou.flush();		
			}
		}
		//打出路牌
		if(option.equals("sendRoadCardInfor")){
			RoadCardInfor fcif=(RoadCardInfor) info.getObject();
			int port=info.getPort();			
			Game game = null;
			Room room2=new Room();
			for(RoomBizImpl roo:dinHall.getRoomList()){
				if(port==roo.getRoom().port){
					
					room2=roo.getRoom();
					roo.getRoom().getGameBiz().UseRoadCard(fcif);
					roo.getRoom().getGameBiz().deletecard(fcif.getCardorder());
					roo.getRoom().getGameBiz().getcard();
					game=roo.getRoom().getGameBiz().getGame();
					}
				}
			
			info.setOrder(fcif.getCardorder());
			info.setObject(game);
			info.setOrder(fcif.getCardorder());
			for(Player p:room2.getPlayers()){					
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
				ou.writeObject(info);
				ou.flush();		
			}
		}
		//打出特殊功能牌
				if(option.equals("sendFunSpCardInfor")){
					FunSpCardInfor fcif=(FunSpCardInfor) info.getObject();
					int port=info.getPort();
					info.setOffset(fcif.getOffset());
					Game game = null;
					Room room2=new Room();
					info.setOrder(fcif.getCardorder());
					for(RoomBizImpl roo:dinHall.getRoomList()){
						if(port==roo.getRoom().port){
							room2=roo.getRoom();

							roo.getRoom().getGameBiz().UseFunSpCard(fcif);
							roo.getRoom().getGameBiz().deletecard(fcif.getCardorder());
							roo.getRoom().getGameBiz().getcard();
							game=roo.getRoom().getGameBiz().getGame();
						}
						}
				
					info.setObject(game);
					info.setOrder(fcif.getCardorder());
										
					for(Player p:room2.getPlayers()){					
						ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
						ou.writeObject(info);
						ou.flush();	
					}
				}
				if(option.equals("giveupcard")){
					int cardid=info.getOffset();
					int port=info.getPort();
			 		Game game = null;
					Room room2=new Room();
					for(RoomBizImpl roo:dinHall.getRoomList()){
						if(port==roo.getRoom().port){
							room2=roo.getRoom();
							if(roo.getRoom().getGameBiz().giveUpCard(cardid)!=null){
							roo.getRoom().getGameBiz().getcard();
							game=roo.getRoom().getGameBiz().getGame();}else {
								game=roo.getRoom().gameBiz.gameEnd(0);
								info.setOption("sendwin");
								//更新数据
								UserBiz userBiz=new UserBizImpl();
								for(int i=0;i<game.getWinner().size();i++){
								userBiz.updateUserScore(game.getWinner().get(i), 1, game.getInningscore().get(i));
								}
								for(int i=0;i<game.getPlayers().size();i++){
									boolean flag=true;
									for(int j=0;j<game.getWinner().size();j++){
									if(game.getPlayers().get(i).getNickname().equals(game.getWinner().get(j)))
									flag=false;
									break;
									}
									if(flag){
									userBiz.updateUserScore(game.getPlayers().get(i).getNickname(), 0, 0);
									}
									}
								dinHall.deleteRoom(room2.port);
								break;
								
							}			
						}
						}
					
					info.setObject(game);
					for(Player p:room2.getPlayers()){					
						ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
						ou.writeObject(info);
						ou.flush();		
					}
				}
				if(option.equals("sendwin")){
					Game game=(Game) info.getObject();
					int port=info.getPort();
					Room room2=new Room();
					Game game2=null;
					for(RoomBizImpl roo:dinHall.getRoomList()){
						if(port==roo.getRoom().port){
							room2=roo.getRoom();	
							game2=roo.getRoom().gameBiz.gameEnd(1);
						}
						}
					
					UserBiz userBiz=new UserBizImpl();
					for(int i=0;i<game2.getWinner().size();i++){
					userBiz.updateUserScore(game2.getWinner().get(i), 1, game2.getInningscore().get(i));
					}
					for(int i=0;i<game2.getPlayers().size();i++){
						boolean flag=true;
						for(int j=0;j<game2.getWinner().size();j++){
						if(game2.getPlayers().get(i).getNickname().equals(game2.getWinner().get(j)))
						flag=false;
						break;
						}
						if(flag){
						userBiz.updateUserScore(game2.getPlayers().get(i).getNickname(), 0, 0);
						}
						}
					info.setObject(game2);
					dinHall.deleteRoom(room2.port);
					for(Player p:room2.getPlayers()){					
						ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
						ou.writeObject(info);
						ou.flush();	
					}
				}
				if(option.equals("updatemap")){
					int port=info.getPort();
					int offset=info.getOffset();
					Room room2=new Room();
					for(RoomBizImpl roo:dinHall.getRoomList()){
						if(port==roo.getRoom().port){
							room2=roo.getRoom();							
						}
						}
					if(offset==8){
						info.setOrder(1);
					}else if(offset==26){
						info.setOrder(2);
					}else if(offset==44){
						info.setOrder(3);
					}
					for(Player p:room2.getPlayers()){					
						ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
						ou.writeObject(info);
						ou.flush();	
					}
				}
				if(option.equals("exitgame")){
					Player player=(Player) info.getObject();
					int port=info.getPort();
					Room room2=new Room();
					for(RoomBizImpl roo:dinHall.getRoomList()){
						if(port==roo.getRoom().port){
							room2=roo.getRoom();							
						}
						}
					UserBiz userBiz=new UserBizImpl();
					userBiz.forceExit(player.getNickname());
					
					
					
					for(Player p:room2.getPlayers()){					
						ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(p.socket.getOutputStream()));  
						ou.writeObject(info);
						ou.flush();	
					}
					dinHall.deleteRoom(room2.port);
				}
	}
	
}

}


