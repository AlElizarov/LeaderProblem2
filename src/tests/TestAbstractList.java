package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.MyListForLeaderElection;
import algorithm.RingArrayList;

public class TestAbstractList {

	private MyListForLeaderElection<Agent> list;

	@Before
	public void setUp() {
		list = new RingArrayList();
		list.add(new Agent(8));
		list.add(new Agent(7));
		list.add(new Agent(9));
	}

	@Test
	public void testIsEmpty() {
		boolean isEmpty = list.isEmpty();
		assertFalse(isEmpty);
	}

	@Test
	public void testAdditionAndSize() {
		int expectedSize = 3;
		int actualSize = list.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testAddAll() {
		Agent[] data = { new Agent(10), new Agent(11), new Agent(12) };
		list.addAll(data);
		int expectedSize = 6;
		int actualSize = list.size();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void testAddAllIntegers() {
		int[] data = { 10, 11, 12 };
		list.addAll(data);
		int expectedSize = 6;
		int actualSize = list.size();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void testAddAllStrings() {
		String[] data = { "10", "11", "12" };
		list.addAll(data);
		int expectedSize = 6;
		int actualSize = list.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testPrint() {
		String listToString = list.toString();
		assertEquals("8, 7, 9", listToString);
	}

	@Test
	public void testCicleNext() {
		int testData[] = { 8, 7, 9, 8 };
		for (int i = 0; i < testData.length; i++) {
			int expect = testData[i];
			int actual = list.next().getId();
			assertEquals(expect, actual);
		}
	}

	@Test
	public void testHasNext() {
		boolean testData[] = { true, true, false, true };
		for (int i = 0; i < testData.length; i++) {
			boolean expect = testData[i];
			list.next();
			boolean actual = list.hasNext();
			assertEquals(expect, actual);
		}
	}

	@Test
	public void testGettingNeiborough() {
		int testData[] = { 7, 9, 8, 7 };
		for (int i = 0; i < testData.length; i++) {
			int expect = testData[i];
			list.next();
			int actual = list.getNextNeiborough().getId();
			assertEquals(expect, actual);
		}
	}

	@Test
	public void testGet() {
		int expect = 7;
		int actual = list.get(1).getId();
		assertEquals(expect, actual);
	}

	@Test
	public void testCicleGet() {
		int expect = 9;
		int actual = list.get(11).getId();
		assertEquals(expect, actual);
	}

}
