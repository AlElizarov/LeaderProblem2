package algorithm;

public interface MyListForLeaderElection<T> extends MyList<T>{
	
	T getNextNeiborough();
	public boolean hasSolution();
	public int getLeaderId();

}
