package game.saboteur.vo;

import java.io.Serializable;

public class FunCardInfor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cardorder;//卡牌id
	private String activeplayer;//出牌方
	private String passiveplayer;//目标对象
	private String function;//表示牌的功能
	public FunCardInfor() {
		// TODO Auto-generated constructor stub
	}
	public FunCardInfor(int cardorder,String activeplayer,String passiveplayer) {
		// TODO Auto-generated constructor stub
		this.cardorder=cardorder;
		this.activeplayer=activeplayer;
		this.passiveplayer=passiveplayer;
	}
	
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
	public String getPassiveplayer() {
		return passiveplayer;
	}
	public void setPassiveplayer(String passiveplayer) {
		this.passiveplayer = passiveplayer;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	

	
	
	
}
