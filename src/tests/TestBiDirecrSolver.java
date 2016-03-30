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

	@Before
	public void setUp() {
		list = new MyRingArrayList();
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(8));
		list.add(new Agent(4));
		list.add(new Agent(6));
	}

	@Test
	public void testChoice() {
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= Math.pow(2, i); j++) {
				BiDirectLeaderElection.choice(list, i, j);
			}
		}
		assertEquals(8, list.getLeaderId());
	}

}
