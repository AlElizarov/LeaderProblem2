package algorithm;

import java.util.ArrayList;

public class MyRingArrayList extends MyAbstractList {

	private ArrayList<Integer> rightSendersIndexes = new ArrayList<>();
	private ArrayList<Integer> newRightSendersIndexes = new ArrayList<>();
	private static ArrayList<Integer> leftSendersIndexes = new ArrayList<>();
	private static ArrayList<Integer> newLeftSendersIndexes = new ArrayList<>();
	private static ArrayList<Integer> currentLeaders = new ArrayList<>();
	private static ArrayList<Integer> rightRequesters = new ArrayList<>();
	private ArrayList<Integer> leftRequesters = new ArrayList<Integer>();

	@Override
	public String printMsgs() {
		String res = "";
		res += "<i>����, ������������ ��������� ������:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			res += leftSendersIndexes.get(i) + "  ";
		}
		res += "<br><i>��������� ������ �� ������� �������:</i> ";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			res += get(leftSendersIndexes.get(i)).getLeftMsg() + "  ";
		}
		res += "<br>";
		res += "<i>����, ������������ ��������� �������:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			res += rightSendersIndexes.get(i) + "  ";
		}
		res += "<br><i>��������� ������ ������ ������� �������:</i> ";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			res += get(rightSendersIndexes.get(i)).getRightMsg() + "  ";
		}
		return res;
	}

	@Override
	public String printLeftAndRightMsgs() {
		String res = "";
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			res += get(leftSendersIndexes.get(i) + 1).getLeftMsg();
			if (i != size - 1)
				res += ", ";
		}
		res += "\n";
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			res += get(rightSendersIndexes.get(i) - 1).getRightMsg();
			if (i != size - 1)
				res += ", ";
		}
		return res;
	}

	@Override
	public Agent get(int i) {
		while (i >= size) {
			i -= size;
		}
		while (i < 0) {
			i += size;
		}
		return arr[i];
	}

	@Override
	public void initiateCurrentLeaders() {
		for (int i = 0; i < size(); i++) {
			currentLeaders.add(i);
		}
	}

	// ��� initiateRightSenders. ���������� ��� �� � ���������
	@Override
	public void initiateLeftSenders() {
		leftSendersIndexes = new ArrayList<>();
		newLeftSendersIndexes = new ArrayList<>();
		for (int i = 0; i < currentLeaders.size(); i++) {
			leftSendersIndexes.add(currentLeaders.get(i));
			newLeftSendersIndexes.add(currentLeaders.get(i));
			get(leftSendersIndexes.get(i)).setLeftMsg(
					get(leftSendersIndexes.get(i)).getId());
		}
	}

	@Override
	public void setLeftSenders() {
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

	@Override
	public void setRightRequesters() {
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
	public void setCorrectLeftSendersIndexes() {
		leftSendersIndexes = newLeftSendersIndexes;
	}

	@Override
	public ArrayList<Integer> getLeftSenders() {
		return leftSendersIndexes;
	}

	@Override
	public void setLeftMsgs() {
		for (int i = 0; i < leftSendersIndexes.size(); i++) {
			get(leftSendersIndexes.get(i) + 1).setLeftMsg(
					get(leftSendersIndexes.get(i) + 1).getNewLeftMsg());
		}
	}

	@Override
	public void setCorrectRightSendersIndexes() {
		rightSendersIndexes = newRightSendersIndexes;
	}

	@Override
	public ArrayList<Integer> getRightSenders() {
		return rightSendersIndexes;
	}

	@Override
	public void setRighMsgs() {
		for (int i = 0; i < rightSendersIndexes.size(); i++) {
			get(rightSendersIndexes.get(i) - 1).setRightMsg(
					get(rightSendersIndexes.get(i) - 1).getNewRightMsg());
		}
	}

	@Override
	public void setLeftRequesters() {
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

	@Override
	public void setRighSenders() {
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
	public void initiateRightSenders() {
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
	public void choiceCurrentLeaders() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getRightRequesters() {
		return rightRequesters;
	}

	@Override
	public Object getLeftRequesters() {
		return leftRequesters;
	}

	@Override
	public void setLeftRequests(int stage) {
		for (int i = 0; i < leftRequesters.size(); i++) {
			get(leftRequesters.get(i) + (int) Math.pow(2, stage)).setLeftOk(
					true);
		}
	}

	@Override
	public void setRightRequests(int stage) {
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

	@Override
	public ArrayList<Integer> getNewLeftSenders() {
		return newLeftSendersIndexes;
	}

	@Override
	public boolean hasSolution() {
		for (int i = 0; i < currentLeaders.size(); i++) {
			if (get(i).getId() == get(i - 1).getLeftMsg()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getLeaderId() {
		int leaderId = 0;
		for (int i = 0; i < size; i++)
			if (get(i).getId() == get(i - 1).getLeftMsg()) {
				leaderId = get(i).getId();
			}

		return leaderId;
	}

}
