package game.saboteur.vo;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;

public class SaboteurServer extends ServerSocket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;

	public SaboteurServer() throws IOException {
		// TODO Auto-generated constructor stub
		super();
	}

	public SaboteurServer(int port) throws IOException {
		// TODO Auto-generated constructor stub
		super(port);
	}
}
