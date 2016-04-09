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
		list.initiateStartState();
	}

	@Test
	public void testSetAndPrintMsgs() {
		list.initiateSenders();
		list.send(0, 0);
		String res = list.printMsgs();
		assertEquals(
				"<i>”злы, отправл€ющие сообщени€ налево:"
				+ "</i> 0  1  2  3  4  5  6  7  <br><i>—ообщени€ идущие по часовой стрелке:"
				+ "</i> 6  3  7  2  5  1  8  4  <br><i>”злы, отправл€ющие сообщени€ направо:"
				+ "</i> 0  1  2  3  4  5  6  7  <br><i>—ообщени€ идущие против часовой стрелке:"
				+ "</i> 7  2  5  1  8  4  6  3  ",
				res);
	}

	@Test
	public void testInitiateCurrentLeaders() {
		assertArrayEquals(new Object[] { 0, 1, 2, 3, 4, 5, 6, 7 }, list
				.getCurrentLeaders().toArray());
	}

}
