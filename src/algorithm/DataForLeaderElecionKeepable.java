package algorithm;

public interface DataForLeaderElecionKeepable {

	Agent get(int idx);

	int size();

	void add(Agent agent);

	void add(int item);

	void add(String item);

	void addAll(Agent[] data);

	void addAll(int[] data);

	void addAll(String[] data);

	Agent getNextNeiborough();

	boolean isEmpty();

	Agent next();

	boolean hasNext();

}
