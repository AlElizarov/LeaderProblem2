package algorithm;

public interface MyList<T> {
	
	boolean isEmpty();
	int size();
	void add(T agent);
	void addAll(T[] data);
	T next();
	boolean hasNext();
	T get(int idx);

}
