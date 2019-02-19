package game.saboteur.vo;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class SaboteurSocket extends Socket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	public SaboteurSocket() {
		// TODO Auto-generated constructor stub
		super();
	}
	public SaboteurSocket(String ip,int port) throws IOException {
		super(ip,port);
		// TODO Auto-generated constructor stub
	}
	
}
