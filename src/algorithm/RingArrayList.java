package algorithm;

import java.util.NoSuchElementException;

public class RingArrayList implements MyListForLeaderElection<Agent>{

	private int size;
	private Agent[] arr = new Agent[1];
	private int index = 0;

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
	
	public void addAll(Agent[] data) {
		for(int i = 0; i < data.length; i++){
			add(data[i]);
		}
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
	
	@Override
	public Agent getNextNeiborough() {
		if (index == size) {
			return arr[0];
		}
		return arr[index];
	}

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
		for (int i = 0; i < size; i++) {
			if (get(i).getId() == get(i).getLeftMsg()) {
				return true;
			}
		}
		return false;
	}

	public int getLeaderId() {
		int leaderId = 0;
		for (int i = 0; i < size; i++)
			if (get(i).getId() == get(i).getLeftMsg()) {
				leaderId = get(i).getId();
			}

		return leaderId;
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

}
