package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.Agent;
import algorithm.MyRingList;

public class TestMyList {

	@Test
	public void test() {
		MyRingList list = new MyRingList();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testAddition() {
		MyRingList list = new MyRingList();
		list.add(new Agent(3));
		assertEquals(1, list.size());
	}

}
