package algorithm;


public class MyRingList extends MyAbstractList {

	public void setMessages() {
		Agent next;
		while (hasNext()) {
			next = next();
			if (next.getId() < next.getMsg()) {
				getNextNeiborough().setNewMsg(next.getMsg());
			} else {
				getNextNeiborough().setNewMsg(0);
			}
		}
		while (hasNext()) {
			next = next();
			next.setMsg(next.getNewMsg());
		}
	}

	public String printMsgs() {
		String s = "";
		Agent next;
		while (hasNext()) {
			next = next();
			if (next.getMsg() != 0) {
				s += "<font color = red>";
			} else {
				s += "<font color = balck>";
			}
			s += next.getMsg();
			s += "</font>";
			if (index < size)
				s += ", ";
		}
		return s;
	}

}
