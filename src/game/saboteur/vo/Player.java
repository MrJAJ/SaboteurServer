package game.saboteur.vo;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public ArrayList<Integer>handcards; //手牌
	private String nickname;//昵称
	private int photoNum;
	private boolean householder;
	private int identify;//身份标识
	private boolean pick;//镐头状态
	private boolean turn=false;
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	private boolean minecar;//矿车状态
	private boolean lamp;//矿灯
	private int tempcount;//三局总分
	public  Socket socket;
	public Player() {
		// TODO Auto-generated constructor stub
	}
	public Player(String nickname,int identify,int tempcount,boolean householder,Socket socket,boolean turn,int photoNum) {
		// TODO Auto-generated constructor stub
		this.householder=householder;
		this.nickname=nickname;
		this.identify=identify;
		handcards = new ArrayList<Integer>();
		this.socket = socket;
		this.pick=true;
		this.minecar=true;
		this.lamp=true;
		this.tempcount=0;
		this.turn=turn;
		this.photoNum=photoNum;
	}
	public Player(String nickname,int identify,int tempcount,boolean householder,Socket socket,int photoNum) {
		// TODO Auto-generated constructor stub
		this.householder=householder;
		this.nickname=nickname;
		this.identify=identify;
		handcards = new ArrayList<Integer>();
		this.socket = socket;
		this.pick=true;
		this.minecar=true;
		this.lamp=true;
		this.tempcount=0;
		this.photoNum=photoNum;
	}
	public Player(String nickname,int identify,int tempcount,boolean householder,Socket socket,ArrayList<Integer> handcards,int photoNum) {
		// TODO Auto-generated constructor stub
		this.householder=householder;
		this.nickname=nickname;
		this.identify=identify;
		handcards = new ArrayList<Integer>();
		this.socket = socket;
		this.pick=true;
		this.minecar=true;
		this.lamp=true;
		this.tempcount=0;
		this.handcards=handcards;
		this.photoNum=photoNum;
	}
	public boolean isHouseholder() {
		return householder;
	}
	public void setHouseholder(boolean householder) {
		this.householder = householder;
	}
	public ArrayList<Integer> getHandcards() {
		return handcards;
	}
	public void setHandcards(ArrayList<Integer> handcards) {
		this.handcards = handcards;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}
	public boolean isPick() {
		return pick;
	}
	public void setPick(boolean pick) {
		this.pick = pick;
	}
	public boolean isMinecar() {
		return minecar;
	}
	public void setMinecar(boolean minecar) {
		this.minecar = minecar;
	}
	public boolean isLamp() {
		return lamp;
	}
	public void setLamp(boolean lamp) {
		this.lamp = lamp;
	}
	public int getTempcount() {
		return tempcount;
	}
	public void setTempcount(int tempcount) {
		this.tempcount = tempcount;
	}
	public int getPhotoNum() {
		return photoNum;
	}
	public void setPhotoNum(int photoNum) {
		this.photoNum = photoNum;
	}
	

}
