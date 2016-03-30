package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.MyAbstractList;
import algorithm.MyRingArrayList;

public class TestMyArrayList {

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
	public void testSetAndPrintMsgs() {
		list.setMessages();
		String res = list.printMsgs();
		assertEquals(
				"<i>”злы, отправл€ющие сообщени€ налево:</i> <br><i>—ообщени€ идущие по часовой стрелке:"
				+ "</i> <br><i>”злы, отправл€ющие сообщени€ направо:"
				+ "</i> <br><i>—ообщени€ идущие против часовой стрелке:</i> ",
				res);
	}
	
	@Test 
	public void testGet(){
		Agent agent = list.get(1);
		assertEquals(7, agent.getId());
		agent = list.get(11);
		assertEquals(5, agent.getId());
	}
	
	@Test
	public void testInitiateCurrentLeaders(){
		list.initiateCurrentLeaders();
		assertArrayEquals(new Object[] {0, 1, 2, 3, 4, 5, 6, 7}, list.getCurrentLeaders().toArray());
	}

}
