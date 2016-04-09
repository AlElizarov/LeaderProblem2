package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.MyAbstractList;
import algorithm.MyRingList;

public class TestMyList {
	
	private MyAbstractList list;
	
	@Before
	public void setUp() {
		list = new MyRingList();
		list.add(new Agent(8));
		list.add(new Agent(7));
		list.add(new Agent(9));
	}

	@Test
	public void testIsEmpty() {
		boolean res = false;
		boolean actual = list.isEmpty();
		assertEquals(res, actual);
	}
	
	@Test
	public void testAdditionAndSize() {
		int expectedSize = 3;
		int actualSize = list.size();
		assertEquals(expectedSize, actualSize);
		assertEquals(3, list.size());
	}
	
	@Test
	public void testPrint() {
		assertEquals("8, 7, 9", list.toString());
	}
	
	@Test
	public void testCicleNext() {
		assertEquals(8, list.next().getId());
		assertEquals(7, list.next().getId());
		assertEquals(9, list.next().getId());
		assertFalse(list.hasNext());
		assertEquals(8, list.next().getId());
		assertTrue(list.hasNext());
	}
	
	@Test
	public void testGettingNeiborough() {
		list.next();
		assertEquals(7, list.getNextNeiborough().getId());
		list.next();
		assertEquals(9, list.getNextNeiborough().getId());
		list.next();
		assertEquals(8, list.getNextNeiborough().getId());
		assertFalse(list.hasNext());
		list.next();
		assertEquals(7, list.getNextNeiborough().getId());
		assertTrue(list.hasNext());
	}
	
	@Test
	public void testSetAndPrintMsgs(){
		list.setMessages();
		String res = list.printMsgs();
		assertEquals("<font color = balck>0</font>, <font color = balck>0</font>, <font color = balck>0</font>", res);
	}
	
	@Test 
	public void testGet(){
		Agent agent = list.get(1);
		assertEquals(7, agent.getId());
		agent = list.get(11);
		assertEquals(9, agent.getId());
	}
	
	@Test
	public void testInitiateStartState(){
		list.initiateStartState();
		assertEquals("<font color = red>9</font>, <font color = red>8</font>, <font color = red>7</font>", list.printMsgs());
	}

}
