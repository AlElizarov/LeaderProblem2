package algorithm;

import graphics.ISolver;
import data.RingArrayList;

public abstract class Solver implements ISolver {

	protected DataForLeaderElecionKeepable list = new RingArrayList();

	@Override
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

	@Override
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

	@Override
	public Agent next() {
		return list.next();
	}

	@Override
	public boolean hasNext() {
		return list.hasNext();
	}
	
	@Override
	public String toString(){
		return list.toString();
	}

}
