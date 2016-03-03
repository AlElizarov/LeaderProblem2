package algorithm;

import java.util.NoSuchElementException;

public class MyRingArrayList implements MyAbstractList {

	private int size;
	private Agent[] arr = new Agent[1];
	private int index = 0;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Agent agent) {
		if (size >= arr.length) {
			Agent[] temp = arr;
			arr = new Agent[temp.length * 2];
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
		arr[size++] = agent;
	}

	@Override
	public Agent next() {
		if(size == 0){
			throw new NoSuchElementException();
		}
		if(index == size){
			index = 0;
		}
		return arr[index++];
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Agent getNextNeiborough() {
		if(index == size){
			return arr[0];
		}
		return arr[index];
	}
	
	@Override
	public String toString(){
		String res = "";
		for(int i = 0; i < size; i++){
			res += arr[i].getId();
			if(i < size-1){
				res += ", ";
			}
		}
		return res;
	}

	@Override
	public void setMessages() {
		
	}

	@Override
	public String printMsgs() {
		return null;
	}

}
