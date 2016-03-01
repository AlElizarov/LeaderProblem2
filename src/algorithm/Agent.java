package algorithm;

public class Agent {
	
	private int id;
	private int msg;
	private int newMsg;

	public Agent(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setMsg(int msg) {
		this.msg = msg;
	}

	public int getMsg() {
		return msg;
	}

	public void setNewMsg(int newMsg) {
		this.newMsg = newMsg;
	}

	public int getNewMsg() {
		return newMsg;
	}

}
