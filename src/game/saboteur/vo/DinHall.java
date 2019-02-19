package game.saboteur.vo;

import game.saboteur.biz.impl.RoomBizImpl;

import java.util.ArrayList;
import java.util.List;

public class DinHall {
	private List<RoomBizImpl>roomList;
	public DinHall() {
		// TODO Auto-generated constructor stub
		this.roomList = new ArrayList<RoomBizImpl>();
	}
	public List<RoomBizImpl> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomBizImpl> roomList) {
		this.roomList = roomList;
	}
	
	public DinHall addRoom(Room room){
		roomList.add(new RoomBizImpl(room));
		return this;
	}
	
	public DinHall deleteRoom(int port){
		for(RoomBizImpl roomBizImpl:roomList){
			if(roomBizImpl.getRoom().port==port)
				roomList.remove(roomBizImpl);
			break;
		}
		return this;
	}
}
