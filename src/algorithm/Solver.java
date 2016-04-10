package algorithm;

public abstract class Solver {

	protected MyListForLeaderElection<Agent> list = new RingArrayList();
	
	public abstract void solve();
	
	public abstract String printMsgs();

	public boolean hasSolution() {
		Agent agent;
		for (int i = 0; i < list.size(); i++) {
			agent = list.get(i);
			if (agent.getId() == agent.getLeftMsg()) {
				return true;
			}
		}
		return false;
	}

	public int getLeaderId() {
		int leaderId = 0;
		Agent agent;
		for (int i = 0; i < list.size(); i++) {
			agent = list.get(i);
			if (agent.getId() == agent.getLeftMsg()) {
				leaderId = agent.getId();
			}
		}
		return leaderId;
	}

	public void add(Agent agent) {
		list.add(agent);
	}

	public void addAll(Agent[] agent) {
		list.addAll(agent);
	}

	public void add(int item) {
		list.add(item);
	}

	public void addAll(int[] item) {
		list.addAll(item);
	}

	public void add(String item) {
		list.add(item);
	}

	public void addAll(String[] item) {
		list.addAll(item);
	}

}
