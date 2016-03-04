package algorithm;

import java.util.ArrayList;

public class MyRingList implements MyAbstractList{

	private int size;
	private Node head;
	private Node current;
	private Node tail;
	private int curIdx;

	private class Node {
		Agent info;
		Node next;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(Agent agent) {
		Node tmp = new Node();
		tmp.info = agent;
		if (size == 0) {
			head = tmp;
			current = head;
			tail = tmp;
		}
		tail.next = tmp;
		tail = tmp;
		tmp.next = head;
		size++;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String s = "";
		while (hasNext()) {
			s += next().getId();
			if (curIdx < size)
				s += ", ";
		}
		return s;
	}

	@Override
	public Agent next() {
		Node tmp = current;
		current = current.next;
		curIdx++;
		return tmp.info;
	}

	@Override
	public boolean hasNext() {
		if (curIdx >= size) {
			curIdx = 0;
			return false;
		}
		return true;
	}

	@Override
	public Agent getNextNeiborough() {
		return current.info;
	}

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
			if (curIdx < size)
				s += ", ";
		}
		return s;
	}

	@Override
	public String printLeftAndRightMsgs() {
		return null;
	}

	@Override
	public Agent get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initiateCurrentLeaders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateLeftSenders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeftSenders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRightRequesters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCorrectLeftSendersIndexes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Integer> getLeftSenders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeftMsgs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCorrectRightSendersIndexes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Integer> getRightSenders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRighMsgs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeftRequesters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRighSenders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initiateRightSenders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void choiceCurrentLeaders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getRightRequesters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getLeftRequesters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getCurrentLeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLeftRequests(int stage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRightRequests(int stage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Integer> getNewLeftSenders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSolution() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLeaderId() {
		Agent next;
		int leaderId = 0;
		while(hasNext()){
			next = next();
			if(next.getId() == next.getMsg()){
				leaderId = next.getId();
			}
		}
		return leaderId;
	}

//	public Agent next(int i) {
//		Node tmp = current;
//		for (int j = 0; j < i - 1; j++) {
//			tmp = tmp.next;
//		}
//		return tmp.info;
//	}

}
