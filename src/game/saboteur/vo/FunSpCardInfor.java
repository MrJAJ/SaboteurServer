package game.saboteur.vo;

import java.io.Serializable;

//封装信息：用户使用塌方牌、探索牌
public class FunSpCardInfor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int cardorder;// 卡牌在排队中的顺序
	private String activeplayer;// 使用者的昵称
	private String function;// 作用描述
	private int offset;// 使用的位置偏移量

	public int getCardorder() {
		return cardorder;
	}

	public void setCardorder(int cardorder) {
		this.cardorder = cardorder;
	}

	public String getActiveplayer() {
		return activeplayer;
	}

	public void setActiveplayer(String activeplayer) {
		this.activeplayer = activeplayer;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public FunSpCardInfor(int cardorder, String activeplayer, String function,
			int offset) {
		super();
		this.cardorder = cardorder;
		this.activeplayer = activeplayer;
		this.function = function;
		this.offset = offset;
	}

	public FunSpCardInfor() {
		super();
	}

}
