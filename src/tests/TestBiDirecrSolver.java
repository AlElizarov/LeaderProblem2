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
	public void setUp(){
		list = new MyRingArrayList();
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(1));
		list.add(new Agent(8));
		list.add(new Agent(4));
		list.add(new Agent(6));
		list.add(new Agent(5));
	}

	@Test
	public void testChoice() {
		//BiDirectLeaderElection.choice(list, 0, 0);
		//assertEquals("3, 7, 2, 1, 8, 4, 6, 5\n3, 7, 2, 1, 8, 4, 6, 5", list.printLeftAndRightMsgs());
	}
	
	@Test
	public void testGet(){
		assertEquals(3, list.get(8).getId());
	}
	
	@Test
	public void testRightRequesters(){
		//BiDirectLeaderElection.choice(list, 0, 0);
		//assertEquals("[2, 3, 5, 7, 0]", list.getRightRequesters().toString());
	}
	
	@Test
	public void testLeftRequesters(){
		BiDirectLeaderElection.choice(list, 0, 0);
		assertEquals("[0, 3, 5]", list.getLeftRequesters().toString());
	}
	
	@Test
	public void testCurrentLeaders(){
		//BiDirectLeaderElection.choice(list, 0, 0);
		//BiDirectLeaderElection.choice(list, 0, 1);
		//assertEquals("[0, 2, 4, 6]", list.getCurrentLeaders().toString());
	}
	


}
