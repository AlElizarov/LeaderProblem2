package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.BiDirectLeaderElection;
import algorithm.MyAbstractList;
import algorithm.MyRingArrayList;

public class TestBiDirecrSolver {
	
	private MyAbstractList list;
	
	@Before
	public void setUp(){
		list = new MyRingArrayList();
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(8));
		list.add(new Agent(3));
		list.add(new Agent(6));
		list.add(new Agent(4));
	}

	@Test
	public void testChoice() {
		BiDirectLeaderElection.choice(list, 0, 0);
		assertEquals("7, 2, 5, 1, 8, 3, 6, 4\n7, 2, 5, 1, 8, 3, 6, 4", list.printLeftAndRightMsgs());
	}
	
	@Test
	public void testGet(){
		assertEquals(7, list.get(8).getId());
	}

}
