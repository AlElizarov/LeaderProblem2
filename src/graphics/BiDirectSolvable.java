package graphics;

import java.util.List;

import algorithm.Agent;

public interface BiDirectSolvable extends ISolver {

	public List<Integer> getLeftSenders();

	public List<Integer> getRightSenders();

	public List<Integer> getCurrentLeaders();

	Agent get(int idx);

	public int getStep();

	public double getStage();

}
