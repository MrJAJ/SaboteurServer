package game.saboteur.po;

import java.io.Serializable;
import java.util.Vector;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;//用户名
	private String password;//密码
	private Vector<Integer> userphoto;//用户头像
	private String nickname;//用户昵称
	private int score;//用户积分
	private int wincount;//获胜局数
	private int totlecount;//总局数
	public User() {
		// TODO Auto-generated constructor stub
		userphoto=new Vector<Integer>();
		userphoto.add(0);
	}
//构造函数
	public User(String username,String password,Vector<Integer>userphoto,String nickname,int score,int wincount,int totlecount) {
		// TODO Auto-generated constructor stub
		this.username=username;
		this.password=password;
		this.userphoto=userphoto;
		this.nickname=nickname;
		this.score=score;
		this.wincount=wincount;
		this.totlecount=totlecount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vector<Integer> getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(Vector<Integer> userphoto) {
		this.userphoto = userphoto;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getWincount() {
		return wincount;
	}
	public void setWincount(int wincount) {
		this.wincount = wincount;
	}
	public int getTotlecount() {
		return totlecount;
	}
	public void setTotlecount(int totlecount) {
		this.totlecount = totlecount;
	}
	
	

}
