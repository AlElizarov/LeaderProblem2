package algorithm;

import graphics.OneDirectSolvable;

public class OneDirectSolver extends Solver implements OneDirectSolvable {

	private int step;

	public void solve() {
		if (step == 0) {
			afterStart();
		} else {
			setMessages();
		}
		step++;
	}

	private void setMessages() {
		Agent current;
		Agent neiborough;
		while (list.hasNext()) {
			current = list.next();
			neiborough = list.getNextNeiborough();
			if (current.getId() < current.getLeftMsg()) {
				neiborough.setNewLeftMsg(current.getLeftMsg());
			} else {
				neiborough.setNewLeftMsg(0);
			}
		}
		while (list.hasNext()) {
			list.next().updateLeftMsgs();
			;
		}
	}

	private void afterStart() {
		Agent current;
		Agent neiborough;
		while (list.hasNext()) {
			current = list.next();
			neiborough = list.getNextNeiborough();
			neiborough.setLeftMsg(current.getId());
			neiborough.setNewLeftMsg(current.getId());
		}
	}

	public void initiateStartState() {
		Agent current;
		while (list.hasNext()) {
			current = list.next();
			current.setLeftMsg(0);
			current.setNewLeftMsg(0);
		}
		step = 0;
	}

	public String printMsgs() {
		String s = "";
		Agent next;
		while (list.hasNext()) {
			next = list.next();
			if (next.getLeftMsg() != 0) {
				s += "<font color = red>";
			} else {
				s += "<font color = balck>";
			}
			s += next.getLeftMsg();
			s += "</font> ";
		}
		return s;
	}

}
