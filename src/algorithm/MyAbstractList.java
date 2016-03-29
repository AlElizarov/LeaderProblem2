package algorithm;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyAbstractList {
	
	protected int size;
	protected Agent[] arr = new Agent[1];
	protected int index = 0;

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
	}

	public void add(Agent agent){
		if (size >= arr.length) {
			Agent[] temp = arr;
			arr = new Agent[temp.length * 2];
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
		arr[size++] = agent;
	}

	public Agent next(){
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (index == size) {
			index = 0;
		}
		return arr[index++];
	}

	public boolean hasNext(){
		if (index >= size) {
			index = 0;
			return false;
		}
		return true;
	}

	public Agent getNextNeiborough(){
		if (index == size) {
			return arr[0];
		}
		return arr[index];
	}

	public void setMessages(){
	}

	public String printMsgs(){
		return null;
	}

	public String printLeftAndRightMsgs(){
		return null;
	}

	public Agent get(int i){
		return null;
	}

	public void initiateCurrentLeaders(){
	}

	public void initiateLeftSenders(){
	}

	public void setLeftSenders(){
	}

	public void setRightRequesters(){
	}

	public void setCorrectLeftSendersIndexes(){
	}

	public ArrayList<Integer> getLeftSenders(){
		return null;
	}

	public void setLeftMsgs(){
	}

	public void setCorrectRightSendersIndexes(){
	}

	public ArrayList<Integer> getRightSenders(){
		return null;
	}

	public void setRighMsgs(){
	}

	public void setLeftRequesters(){
	}

	public void setRighSenders(){
	}

	public void initiateRightSenders(){
	}

	public void choiceCurrentLeaders(){
	}

	public Object getRightRequesters(){
		return null;
	}

	public Object getLeftRequesters(){
		return null;
	}

	public void setLeftRequests(int stage){
	}

	public void setRightRequests(int stage){
	}

	public ArrayList<Integer> getCurrentLeaders(){
		return null;
	}

	public ArrayList<Integer> getNewLeftSenders(){
		return null;
	}
	
	public boolean hasSolution(){
		return false;
	}
	
	public int getLeaderId(){
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

}
