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

}
