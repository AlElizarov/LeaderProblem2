package algorithm;


public class MyRingList extends MyAbstractList {

	public void setMessages() {
		Agent next;
		while (hasNext()) {
			next = next();
			if (next.getId() < next.getLeftMsg()) {
				getNextNeiborough().setNewLeftMsg(next.getLeftMsg());
			} else {
				getNextNeiborough().setNewLeftMsg(0);
			}
		}
		while (hasNext()) {
			next = next();
			next.updateLeftMsgs();
		}
	}

	public String printMsgs() {
		String s = "";
		Agent next;
		while (hasNext()) {
			next = next();
			if (next.getLeftMsg() != 0) {
				s += "<font color = red>";
			} else {
				s += "<font color = balck>";
			}
			s += next.getLeftMsg();
			s += "</font>";
			if (index < size)
				s += ", ";
		}
		return s;
	}

}
