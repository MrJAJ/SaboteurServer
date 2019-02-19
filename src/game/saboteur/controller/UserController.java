package game.saboteur.controller;

import game.saboteur.biz.UserBiz;

import game.saboteur.biz.impl.UserBizImpl;
import game.saboteur.po.User;
import game.saboteur.vo.Info;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UserController {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 //定义一个ServerSocket监听在端口800上 
		
		ServerSocket server=new ServerSocket(800);
		//DinHall dinHall=new DinHall();
		System.out.println("等待连接");
		
		while(true){
			//server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
			//Socket socket=new Socket();
			//synchronized (socket){
			
			Socket socket=server.accept();
			//}
			System.out.println("正在连接");
			//每接收到一个Socket就建立一个新的线程来处理它  
			new Thread(new UserR(socket)).start();
			
		}
		}
public static	class UserR implements Runnable{
		private Socket socket;
		public UserR() {
			// TODO Auto-generated constructor stub
		}
		
		public UserR(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket=socket;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				UserRun();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("客户端已断开");
				System.exit(0);
			}
		}
		
	
	private  void UserRun() throws IOException, ClassNotFoundException  {
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream())); 
			Info info=(Info) is.readObject();
			String option=info.getOption();
			System.out.println(option);
			//用户登录
			if(option.equals("login")){
			User us=(User) info.getObject();
			//
			System.out.println(us.getUsername()+us.getPassword());
			
			UserBiz userBiz=new UserBizImpl();
			User user=userBiz.userLogin(us.getUsername(),us.getPassword());				
			ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
			ou.writeObject(user);
			ou.flush();
			ou.close();			
			}
			//用户注册
			if(option.equals("register")){
				User us=(User) info.getObject();
				System.out.println(us.getUsername()+us.getPassword()+us.getNickname());
				UserBiz userBiz=new UserBizImpl();
				Integer n=userBiz.addUser(us.getUsername(),us.getPassword(),us.getNickname(), "0 0 ");
				ObjectOutputStream ou = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));  
				ou.writeObject(n);
				ou.flush();
				ou.close();
		}
			
			is.close();
			socket.close();
	}
}
}


