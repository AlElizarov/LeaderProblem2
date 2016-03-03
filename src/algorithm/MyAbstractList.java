package algorithm;

public interface MyAbstractList {

	public boolean isEmpty();

	public int size();

	public void add(Agent agent);

	public Agent next();

	public boolean hasNext();

	public Agent getNextNeiborough();

	public void setMessages();

	public String printMsgs();

}
