package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.Agent;
import algorithm.LeaderElection;
import algorithm.MyRingList;

public class TestLeaderElection {

	@Test
	public void testElection() {
		MyRingList list = new MyRingList();
		list.add(new Agent(3));
		list.add(new Agent(5));
		list.add(new Agent(2));
		list.add(new Agent(1));
		list.add(new Agent(4));
		//assertEquals(5, LeaderElection.solve(list));
	}

}
