package game.saboteur.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Info implements Serializable {
	private static final long serialVersionUID = 2L;
	private String option;
	private Object object;
	private boolean flag;
	private int port;
	private int offset;
	private List<Object> list;
	private int order;//牌在牌堆里的位置
	public Info() {
		// TODO Auto-generated constructor stub
	}
	public Info(String option,Object object) {
		// TODO Auto-generated constructor stub
		this.option=option;
		this.object=object;
		list=new ArrayList<Object>();
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
