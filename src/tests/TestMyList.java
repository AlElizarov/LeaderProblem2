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
	public void test() {
		assertTrue(!list.isEmpty());
	}
	
	@Test
	public void testAddition() {
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
	}

}
