package algorithm;

public class Agent {

	private int id;
	private int leftMsg;
	private int rightMsg;
	private int newLeftMsg;
	private int newRightMsg;
	private boolean leftOk;
	
	public Agent(int id) {
		this.id = id;
	}

	public boolean isLeftOk() {
		return leftOk;
	}

	public void setLeftOk(boolean leftOk) {
		this.leftOk = leftOk;
	}

	public void setNewLeftMsg(int newLeftMsg) {
		this.newLeftMsg = newLeftMsg;
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

	public int getId() {
		return id;
	}

	public void updateLeftMsgs() {
		leftMsg = newLeftMsg;
	}

	public void updateRightMsgs() {
		rightMsg = newRightMsg;
	}

}
