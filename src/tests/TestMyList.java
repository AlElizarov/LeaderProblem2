package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.MyRingList;

public class TestMyList {
	
	private MyRingList list;
	
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
		assertEquals("( 8 7 9)", list.toString());
	}
	
	@Test
	public void testCicleNext() {
		assertEquals(8, list.next().getId());
		assertEquals(7, list.next().getId());
		assertEquals(9, list.next().getId());
		assertEquals(8, list.next().getId());
		assertFalse(list.hasNext());
	}
	
	@Test
	public void testGettingNeiborough() {
		list.next();
		assertEquals(7, list.getNeiborough().getId());
		list.next();
		assertEquals(9, list.getNeiborough().getId());
		list.next();
		assertEquals(8, list.getNeiborough().getId());
		list.next();
		assertEquals(7, list.getNeiborough().getId());
		assertFalse(list.hasNext());
	}
	
	@Test
	public void testSendingMsgsToNeiboroughs() {
		Agent next;
		while (list.hasNext()) {
			next = list.next();
			list.getNeiborough().setMsg(next.getId());
		}
		assertEquals("9 8 7 ", list.printMsgs());
	}
	
	@Test
	public void testValidationMsgs(){
		Agent next;
		while (list.hasNext()) {
			next = list.next();
			list.getNeiborough().setMsg(next.getId());
		}
		assertEquals("9 8 7 ", list.printMsgs());
		while (list.hasNext()) {
			next = list.next();
			if (next.getId() < next.getMsg()) {
				list.getNeiborough().setNewMsg(next.getMsg());
			} else {
				list.getNeiborough().setNewMsg(0);
			}
		}
		list.setMessages();
		assertEquals("0 9 8 ", list.printMsgs());
	}

}
