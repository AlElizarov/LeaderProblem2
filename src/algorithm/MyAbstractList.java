package algorithm;

import java.util.ArrayList;

public interface MyAbstractList {

	public boolean isEmpty();

	public int size();

	public void add(Agent agent);

	public Agent next();

	public boolean hasNext();

	public Agent getNextNeiborough();

	public void setMessages();

	public String printMsgs();

	public String printLeftAndRightMsgs();

	public Agent get(int i);

	public void initiateCurrentLeaders();

	public void initiateLeftSenders();

	public void setLeftSenders();

	public void setRightRequesters();

	public void setCorrectLeftSendersIndexes();

	public ArrayList<Integer> getLeftSenders();

	public void setLeftMsgs();

	public void setCorrectRightSendersIndexes();

	public ArrayList<Integer> getRightSenders();

	public void setRighMsgs();

	public void setLeftRequesters();

	public void setRighSenders();

	public void initiateRightSenders();

	public void choiceCurrentLeaders();

	public Object getRightRequesters();

	public Object getLeftRequesters();

	public void setLeftRequests(int stage);

	public void setRightRequests(int stage);

	public ArrayList<Integer> getCurrentLeaders();

	public ArrayList<Integer> getNewLeftSenders();

}
