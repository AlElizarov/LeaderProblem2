package algorithm;

import graphics.BiDirectSolvable;

import java.util.ArrayList;
import java.util.List;

public class BiDirectSolver extends Solver implements BiDirectSolvable {

	private int stage = 0;
	private int step = 0;
	private List<Integer> rightSendersIndexes = new ArrayList<>();
	private List<Integer> newRightSendersIndexes = new ArrayList<>();
	private List<Integer> leftSendersIndexes = new ArrayList<>();
	private List<Integer> newLeftSendersIndexes = new ArrayList<>();
	private List<Integer> currentLeaders = new ArrayList<>();
	private List<Integer> rightRequesters = new ArrayList<>();
	private List<Integer> leftRequesters = new ArrayList<Integer>();

	public void solve() {
		if (stage == 0 && step == 0) {
			initiateStartState();
		}
		if (step == 0) {
			initiateSenders();
		}
		if (step < Math.pow(2, stage)) {
			send();
		} else {
			setRequests();
		}

		if (step == Math.pow(2, stage)) {
			stage++;
			step = 0;
		} else {
			step++;
		}
	}

	public void initiateStartState() {
		for (int i = 0; i < list.size(); i++) {
			currentLeaders.add(i);
		}
	}

	private void initiateSenders() {
		leftSendersIndexes = new ArrayList<>();
		newLeftSendersIndexes = new ArrayList<>();
		rightSendersIndexes = new ArrayList<>();
		newRightSendersIndexes = new ArrayList<>();
		Agent node;
		int leaderIdx = 0;
		for (int i = 0; i < currentLeaders.size(); i++) {
			leaderIdx = currentLeaders.get(i);
			leftSendersIndexes.add(leaderIdx);
			newLeftSendersIndexes.add(leaderIdx);
			node = list.get(leftSendersIndexes.get(i));
			node.setLeftMsg(node.getId());
			rightSendersIndexes.add(leaderIdx);
			newRightSendersIndexes.add(leaderIdx);
			node = list.get(rightSendersIndexes.get(i));
			node.setRightMsg(node.getId());
		}
	}

	private void setMessages() {
		setLeftMsgs();
		setRighMsgs();
	}

	private void setLeftMsgs() {
		Agent leftRecepient;
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			leftRecepient = list.get(leftSendersIndexes.get(i) + 1);
			leftRecepient.updateLeftMsgs();
		}
	}

	private void setRighMsgs() {
		Agent rightRecepient;
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			rightRecepient = list.get(rightSendersIndexes.get(i) - 1);
			rightRecepient.updateRighttMsgs();
		}
	}

	private void setRequests() {
		setLeftRequests();
		setRightRequests();
	}

	private void setLeftRequests() {
		int requesterIdx = 0;
		for (int i = 0; i < leftRequesters.size(); i++) {
			requesterIdx = leftRequesters.get(i) + (int) Math.pow(2, stage);
			list.get(requesterIdx).setLeftOk(true);
		}
	}

	private void setRightRequests() {
		currentLeaders = new ArrayList<>();
		int requesterIdx;
		Agent requester;
		for (int i = 0; i < rightRequesters.size(); i++) {
			requesterIdx = rightRequesters.get(i) - (int) Math.pow(2, stage);
			requester = list.get(requesterIdx);
			if (requester.isLeftOk()) {
				if (requesterIdx >= 0) {
					currentLeaders.add(requesterIdx);

				} else {
					currentLeaders.add(requesterIdx + list.size());
				}
			}
		}
		resetLeftOk();
	}

	private void setRequesters() {
		setLeftRequesters();
		setRightRequesters();
	}

	private void setLeftRequesters() {
		leftRequesters = new ArrayList<>();
		int rightSenderIdx = 0;
		Agent rightSender;
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			rightSenderIdx = rightSendersIndexes.get(i) - 1;
			rightSender = list.get(rightSenderIdx);
			if (rightSender.getRightMsg() > rightSender.getId()) {
				if (rightSenderIdx - 1 >= 0) {
					leftRequesters.add(rightSenderIdx);
				} else {
					leftRequesters.add(rightSenderIdx + list.size());
				}
			}
		}
	}

	private void setRightRequesters() {
		rightRequesters = new ArrayList<>();
		int leftSenderIdx = 0;
		Agent leftSender;
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			leftSenderIdx = leftSendersIndexes.get(i) + 1;
			leftSender = list.get(leftSenderIdx);
			if (leftSender.getLeftMsg() > leftSender.getId()) {
				if (leftSenderIdx < list.size()) {
					rightRequesters.add(leftSenderIdx);
				} else {
					rightRequesters.add(leftSenderIdx - list.size());
				}
			}
		}
	}

	private void send() {
		sendLeft();
		sendRight();
		setMessages();
		if ((int) Math.pow(2, stage) - step == 1) {
			setRequesters();
		} else {
			setSenders();
		}
	}

	private void sendLeft() {
		leftSendersIndexes = newLeftSendersIndexes;
		Agent rightRecepient;
		int rightRecepientIdx = 0;
		Agent leftSender;
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			rightRecepientIdx = leftSendersIndexes.get(i) + 1;
			rightRecepient = list.get(rightRecepientIdx);
			leftSender = list.get(rightRecepientIdx - 1);
			rightRecepient.setNewLeftMsg(leftSender.getLeftMsg());
			leftSender.setLeftMsg(0); // reset left messages
		}
	}

	private void sendRight() {
		rightSendersIndexes = newRightSendersIndexes;
		Agent leftRecepient;
		int leftRecepientIdx = 0;
		Agent rightSender;
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			leftRecepientIdx = rightSendersIndexes.get(i) - 1;
			leftRecepient = list.get(leftRecepientIdx);
			rightSender = list.get(leftRecepientIdx + 1);
			leftRecepient.setNewRightMsg(rightSender.getRightMsg());
		}
	}

	private void setSenders() {
		setLeftSenders();
		setRighSenders();
	}

	private void setLeftSenders() {
		newLeftSendersIndexes = new ArrayList<>();
		int leftSenderIdx = 0;
		Agent leftSender;
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			leftSenderIdx = leftSendersIndexes.get(i) + 1;
			leftSender = list.get(leftSenderIdx);
			if (leftSender.getId() < leftSender.getLeftMsg()) {
				if (leftSenderIdx < list.size()) {
					newLeftSendersIndexes.add(leftSenderIdx);
				} else {
					newLeftSendersIndexes.add(leftSenderIdx - list.size());
				}
			}
		}
	}

	private void setRighSenders() {
		newRightSendersIndexes = new ArrayList<>();
		int rightSenderIdx = 0;
		Agent rightSender;
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			rightSenderIdx = rightSendersIndexes.get(i) - 1;
			rightSender = list.get(rightSenderIdx);
			if (rightSender.getId() < rightSender.getRightMsg()) {
				if ((rightSendersIndexes.get(i) - 1) >= 0) {
					newRightSendersIndexes.add(rightSenderIdx);
				} else {
					newRightSendersIndexes.add(rightSenderIdx + list.size());
				}
			}
		}
	}

	private void resetLeftOk() {
		int idx = 0;
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			idx = leftSendersIndexes.get(i) + (int) Math.pow(2, stage);
			list.get(idx).setLeftOk(false);
		}
	}

	public List<Integer> getLeftSenders() {
		return leftSendersIndexes;
	}

	public List<Integer> getRightSenders() {
		return rightSendersIndexes;
	}

	public List<Integer> getCurrentLeaders() {
		return currentLeaders;
	}

	public String printMsgs() {
		String result = "";

		result += "<i>”злы, отправл€ющие сообщени€ налево:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			result += leftSendersIndexes.get(i) + "  ";
		}
		result += "<br><i>—ообщени€ идущие по часовой стрелке:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			result += list.get(leftSendersIndexes.get(i)).getLeftMsg() + "  ";
		}
		result += "<br>";
		result += "<i>”злы, отправл€ющие сообщени€ направо:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			result += rightSendersIndexes.get(i) + "  ";
		}
		result += "<br><i>—ообщени€ идущие против часовой стрелке:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			result += list.get(rightSendersIndexes.get(i)).getRightMsg() + "  ";
		}
		return result;
	}

	@Override
	public Agent get(int idx) {
		return list.get(idx);
	}

}
