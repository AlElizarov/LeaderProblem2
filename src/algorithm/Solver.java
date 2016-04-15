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

	@Override
	public void add(Agent agent) {
		list.add(agent);
	}

	@Override
	public void addAll(Agent[] agent) {
		list.addAll(agent);
	}

	@Override
	public void add(int item) {
		list.add(item);
	}

	@Override
	public void addAll(int[] item) {
		list.addAll(item);
	}

	@Override
	public void add(String item) {
		list.add(item);
	}

	@Override
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
	
	@Override
	public void setRandomData(int quantity){
		list.setRandomData(quantity);
	}

}
