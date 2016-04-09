package algorithm;


public class MyRingList extends MyAbstractList {

	@Override
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
	
	@Override
	public void initiateStartState(){
		Agent next;
		while (hasNext()) {
			next = next();
			getNextNeiborough().setLeftMsg(next.getId());
			getNextNeiborough().setNewLeftMsg(next.getId());
		}
	}
	
	@Override
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
