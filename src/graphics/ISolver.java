package graphics;

import algorithm.Agent;

public interface ISolver {

	void solve();

	String printMsgs();

	boolean hasSolution();

	int getLeaderId();
	
	void add(Agent agent);

	void add(int item);

	void add(String item);

	void addAll(Agent[] data);

	void addAll(int[] data);

	void addAll(String[] data);
	
	public void initiateStartState();
	
	Agent next();

	boolean hasNext();

	void setRandomData(int quantuty);

}
