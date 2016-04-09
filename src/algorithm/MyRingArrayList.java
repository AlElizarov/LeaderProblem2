package algorithm;

import java.util.ArrayList;

public class MyRingArrayList extends MyAbstractList {

	private ArrayList<Integer> rightSendersIndexes = new ArrayList<>();
	private ArrayList<Integer> newRightSendersIndexes = new ArrayList<>();
	private ArrayList<Integer> leftSendersIndexes = new ArrayList<>();
	private ArrayList<Integer> newLeftSendersIndexes = new ArrayList<>();
	private ArrayList<Integer> currentLeaders = new ArrayList<>();
	private ArrayList<Integer> rightRequesters = new ArrayList<>();
	private ArrayList<Integer> leftRequesters = new ArrayList<Integer>();

	@Override
	public void initiateStartState() {
		for (int i = 0; i < size(); i++) {
			currentLeaders.add(i);
		}
	}

	@Override
	public void initiateSenders() {
		initiateLeftSenders();
		initiateRightSenders();
	}

	private void initiateLeftSenders() {
		leftSendersIndexes = new ArrayList<>();
		newLeftSendersIndexes = new ArrayList<>();
		for (int i = 0; i < currentLeaders.size(); i++) {
			leftSendersIndexes.add(currentLeaders.get(i));
			newLeftSendersIndexes.add(currentLeaders.get(i));
			get(leftSendersIndexes.get(i)).setLeftMsg(
					get(leftSendersIndexes.get(i)).getId());
		}
	}

	private void initiateRightSenders() {
		rightSendersIndexes = new ArrayList<>();
		newRightSendersIndexes = new ArrayList<>();
		for (int i = 0; i < currentLeaders.size(); i++) {
			rightSendersIndexes.add(currentLeaders.get(i));
			newRightSendersIndexes.add(currentLeaders.get(i));
			get(rightSendersIndexes.get(i)).setRightMsg(
					get(rightSendersIndexes.get(i)).getId());
		}
	}

	@Override
	public String printMsgs() {
		String res = "";

		res += "<i>”злы, отправл€ющие сообщени€ налево:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			res += leftSendersIndexes.get(i) + "  ";
		}
		res += "<br><i>—ообщени€ идущие по часовой стрелке:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			res += get(leftSendersIndexes.get(i)).getLeftMsg() + "  ";
		}
		res += "<br>";
		res += "<i>”злы, отправл€ющие сообщени€ направо:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			res += rightSendersIndexes.get(i) + "  ";
		}
		res += "<br><i>—ообщени€ идущие против часовой стрелке:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			res += get(rightSendersIndexes.get(i)).getRightMsg() + "  ";
		}
		return res;
	}

	private void setLeftMsgs() {
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			Agent recepient = get(leftSendersIndexes.get(i) + 1);
			recepient.updateLeftMsgs();
		}
	}

	private void setRighMsgs() {
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			get(rightSendersIndexes.get(i) - 1).setRightMsg(
					get(rightSendersIndexes.get(i) - 1).getNewRightMsg());
		}
	}

	@Override
	public void setMessages() {
		setLeftMsgs();
		setRighMsgs();
	}

	@Override
	public void setRequests(int stage) {
		setLeftRequests(stage);
		setRightRequests(stage);
	}

	private void setLeftRequests(int stage) {
		for (int i = 0; i < leftRequesters.size(); i++) {
			get(leftRequesters.get(i) + (int) Math.pow(2, stage)).setLeftOk(
					true);
		}
	}

	private void setRightRequests(int stage) {
		currentLeaders = new ArrayList<>();
		for (int i = 0; i < rightRequesters.size(); i++) {
			if (get(rightRequesters.get(i) - (int) Math.pow(2, stage))
					.isLeftOk()) {
				if (rightRequesters.get(i) - (int) Math.pow(2, stage) >= 0) {
					currentLeaders.add(rightRequesters.get(i)
							- (int) Math.pow(2, stage));

				} else {
					currentLeaders.add(rightRequesters.get(i)
							- (int) Math.pow(2, stage) + size);
				}
			}
		}
		resetLeftOk(stage);
	}

	private void setRequesters() {
		setLeftRequesters();
		setRightRequesters();
	}

	private void setLeftRequesters() {
		leftRequesters = new ArrayList<>();
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			if (get(rightSendersIndexes.get(i) - 1).getRightMsg() > get(
					rightSendersIndexes.get(i) - 1).getId()) {
				if (rightSendersIndexes.get(i) - 1 >= 0) {
					leftRequesters.add(rightSendersIndexes.get(i) - 1);
				} else {
					leftRequesters.add(rightSendersIndexes.get(i) - 1 + size);
				}
			}
		}
	}

	private void setRightRequesters() {
		rightRequesters = new ArrayList<>();
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			if (get(leftSendersIndexes.get(i) + 1).getLeftMsg() > get(
					leftSendersIndexes.get(i) + 1).getId()) {
				if (leftSendersIndexes.get(i) + 1 < size) {
					rightRequesters.add(leftSendersIndexes.get(i) + 1);
				} else {
					rightRequesters.add(leftSendersIndexes.get(i) + 1 - size);
				}
			}
		}
	}

	@Override
	public void send(int stage, int step) {
		sendLeft(stage, step);
		sendRight(stage, step);
		setMessages();
		if ((int) Math.pow(2, stage) - step == 1) {
			setRequesters();
		} else {
			setSenders();
		}
	}

	private void sendLeft(int stage, int step) {
		leftSendersIndexes = newLeftSendersIndexes;
		for (int i = 0; i < getLeftSenders().size(); i++) {
			get(getLeftSenders().get(i) + 1).setNewLeftMsg(
					get(getLeftSenders().get(i)).getLeftMsg());
		}
	}

	private void sendRight(int stage, int step) {
		rightSendersIndexes = newRightSendersIndexes;
		for (int i = 0; i < getRightSenders().size(); i++) {
			get(getRightSenders().get(i) - 1).setNewRightMsg(
					get(getRightSenders().get(i)).getRightMsg());
		}
	}

	private void setSenders() {
		setLeftSenders();
		setRighSenders();
	}

	private void setLeftSenders() {
		newLeftSendersIndexes = new ArrayList<>();
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			if (get(leftSendersIndexes.get(i) + 1).getId() < get(
					leftSendersIndexes.get(i) + 1).getLeftMsg()) {
				if (leftSendersIndexes.get(i) + 1 < size) {
					newLeftSendersIndexes.add(leftSendersIndexes.get(i) + 1);
				} else {
					newLeftSendersIndexes.add(leftSendersIndexes.get(i) + 1
							- size);
				}
			}
		}
	}

	private void setRighSenders() {
		newRightSendersIndexes = new ArrayList<>();
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			if (get(rightSendersIndexes.get(i) - 1).getId() < get(
					rightSendersIndexes.get(i) - 1).getRightMsg()) {
				if ((rightSendersIndexes.get(i) - 1) >= 0) {
					newRightSendersIndexes.add(rightSendersIndexes.get(i) - 1);
				} else {
					newRightSendersIndexes.add(rightSendersIndexes.get(i) - 1
							+ size);
				}
			}
		}
	}

	@Override
	public ArrayList<Integer> getLeftSenders() {
		return leftSendersIndexes;
	}

	@Override
	public ArrayList<Integer> getRightSenders() {
		return rightSendersIndexes;
	}

	private void resetLeftOk(int stage) {
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			get(leftSendersIndexes.get(i) + (int) Math.pow(2, stage))
					.setLeftOk(false);
		}
	}

	@Override
	public ArrayList<Integer> getCurrentLeaders() {
		return currentLeaders;
	}

}
