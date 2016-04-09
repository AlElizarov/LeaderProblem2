package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.LeaderElection;
import algorithm.MyAbstractList;
import algorithm.MyRingList;

public class TestLeaderElection {
	
	private MyAbstractList list;
	
	@Before
	public void setUp(){
		list = new MyRingList();
	}
	
	@Test
	public void testOneDirectModeSimpleCaseEven(){
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(8));
		list.add(new Agent(4));
		list.add(new Agent(6));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(8, leaderId);
	}
	
	@Test
	public void testOneDirectModeSimpleCaseOdd(){
		list.add(new Agent(3));
		list.add(new Agent(7));
		list.add(new Agent(2));
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(4));
		list.add(new Agent(6));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(7, leaderId);
	}
	
	@Test
	public void testOneDirectModeOneVariable(){
		list.add(new Agent(3));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}
	
	@Test
	public void testOneDirectModeTwoVariables(){
		list.add(new Agent(3));
		list.add(new Agent(2));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}
	
	@Test
	public void testOneDirectModeBestCaseEven(){
		list.add(new Agent(6));
		list.add(new Agent(1));
		list.add(new Agent(2));
		list.add(new Agent(3));
		list.add(new Agent(4));
		list.add(new Agent(5));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(6, leaderId);
	}
	
	@Test
	public void testOneDirectModeBestCaseOdd(){
		list.add(new Agent(5));
		list.add(new Agent(1));
		list.add(new Agent(2));
		list.add(new Agent(3));
		list.add(new Agent(4));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(5, leaderId);
	}
	
	@Test
	public void testOneDirectModeWorstCaseOdd(){
		list.add(new Agent(3));
		list.add(new Agent(2));
		list.add(new Agent(1));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}
	
	@Test
	public void testOneDirectModeWorstCaseEven(){
		list.add(new Agent(4));
		list.add(new Agent(3));
		list.add(new Agent(2));
		list.add(new Agent(1));
		
		int i = 0;
		while(!list.hasSolution()){
			LeaderElection.solve(list, i++);
		}
		
		int leaderId = list.getLeaderId();
		assertEquals(4, leaderId);
	}

}
