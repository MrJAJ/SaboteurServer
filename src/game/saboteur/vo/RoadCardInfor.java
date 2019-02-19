package game.saboteur.vo;

import java.io.Serializable;

public class RoadCardInfor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private int cardorder;//卡牌id
	private boolean dir;//方向标识
	private String nickname;//昵称
	private int offset;//ImageId,表示卡牌位置
	public RoadCardInfor() {
		// TODO Auto-generated constructor stub
	}
	
	public RoadCardInfor(int cardorder,boolean dir,String nickname,int ivid) {
		// TODO Auto-generated constructor stub
		this.cardorder=cardorder;
		this.dir=dir;
		this.nickname=nickname;
		this.offset=ivid;
	}
	public boolean isDir() {
		return dir;
	}
	public void setDir(boolean dir) {
		this.dir = dir;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCardorder() {
		return cardorder;
	}

	public void setCardorder(int cardorder) {
		this.cardorder = cardorder;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int ofset) {
		this.offset = ofset;
	}
	
}
