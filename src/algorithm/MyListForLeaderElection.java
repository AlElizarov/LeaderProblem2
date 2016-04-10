package algorithm;

public interface MyListForLeaderElection<T> {

	boolean isEmpty();

	int size();

	void add(T agent);

	void add(int item);

	void add(String item);

	void addAll(T[] data);

	void addAll(int[] data);

	void addAll(String[] data);

	T next();

	boolean hasNext();

	T get(int idx);

	T getNextNeiborough();

}
