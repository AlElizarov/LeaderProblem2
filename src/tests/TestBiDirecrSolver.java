package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.BiDirectLeaderElection;
import algorithm.MyAbstractList;
import algorithm.MyRingArrayList;

public class TestBiDirecrSolver {

	private MyAbstractList list;
	private int stage;
	private int step;

	@Before
	public void setUp() {
		list = new MyRingArrayList();
		stage = 0;
		step = 0;
	}

	@Test
	public void testBiDirectModeSimpleCaseEven() {
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(8));
		list.add(new Agent(4));
		list.add(new Agent(6));
		
		while (!list.hasSolution()) {
			System.out.println("yes");
			BiDirectLeaderElection.choice(list, stage, step);
			if (step == Math.pow(2, stage)) {
				stage++;
				step = 0;
			} else {
				step++;
			}
		}
		
		int leaderId = list.getLeaderId();
		assertEquals("stage: "+stage+"step: "+step,8, leaderId);
	}
	
	@Test
	public void testBiDirectModeSimpleCaseOdd() {
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(1));
		list.add(new Agent(6));
		list.add(new Agent(4));
		list.add(new Agent(5));
		
		while (!list.hasSolution()) {
			BiDirectLeaderElection.choice(list, stage, step);
			if (step == Math.pow(2, stage)) {
				stage++;
				step = 0;
			} else {
				step++;
			}
		}
		
		int leaderId = list.getLeaderId();
		assertEquals("stage: "+stage+"step: "+step, 7, leaderId);
	}

}
