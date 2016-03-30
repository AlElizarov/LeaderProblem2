package algorithm;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class MyAbstractList {

	protected int size;
	protected Agent[] arr = new Agent[1];
	protected int index = 0;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(Agent agent) {
		if (size >= arr.length) {
			Agent[] temp = arr;
			arr = new Agent[temp.length * 2];
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
		arr[size++] = agent;
	}

	public Agent next() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (index == size) {
			index = 0;
		}
		return arr[index++];
	}

	public boolean hasNext() {
		if (index >= size) {
			index = 0;
			return false;
		}
		return true;
	}

	public Agent getNextNeiborough() {
		if (index == size) {
			return arr[0];
		}
		return arr[index];
	}

	public abstract void setMessages();

	public abstract String printMsgs();

	public Agent get(int i) {
		while (i >= size) {
			i -= size;
		}
		while (i < 0) {
			i += size;
		}
		return arr[i];
	}
	
	public boolean hasSolution() {
		return false;
	}

	public int getLeaderId() {
		return 0;
	}

	@Override
	public String toString() {
		String s = "";
		while (hasNext()) {
			s += next().getId();
			if (index < size)
				s += ", ";
		}
		return s;
	}

	public void initiateCurrentLeaders() {
	}

	public void initiateSenders() {
	}
	
	public ArrayList<Integer> getLeftSenders() {
		return null;
	}

	public ArrayList<Integer> getRightSenders() {
		return null;
	}

	public ArrayList<Integer> getCurrentLeaders() {
		return null;
	}

	public void setRequests(int stage) {
	}

	public void send(int stage, int step) {
	}

}
