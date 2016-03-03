package algorithm;

public class Agent {
	
	private int id;
	private int msg;
	private int newMsg;
	private int leftMsg;
	private int rightMsg;
	private int newLeftMsg;
	private int newRightMsg;

	public int getNewLeftMsg() {
		return newLeftMsg;
	}

	public void setNewLeftMsg(int newLeftMsg) {
		this.newLeftMsg = newLeftMsg;
	}

	public int getNewRightMsg() {
		return newRightMsg;
	}

	public void setNewRightMsg(int newRightMsg) {
		this.newRightMsg = newRightMsg;
	}

	public int getLeftMsg() {
		return leftMsg;
	}

	public void setLeftMsg(int leftMsg) {
		this.leftMsg = leftMsg;
	}

	public int getRightMsg() {
		return rightMsg;
	}

	public void setRightMsg(int rghtMsg) {
		this.rightMsg = rghtMsg;
	}

	public Agent(int id) {
		this.id = id;
		leftMsg = id;
		rightMsg = id;
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
