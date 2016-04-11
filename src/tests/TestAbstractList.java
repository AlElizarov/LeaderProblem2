package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.DataForLeaderElecionKeepable;
import data.DuplicateValueException;
import data.EmptyDataException;
import data.FormatException;
import data.NonPositiveValueException;
import data.RingArrayList;

public class TestAbstractList {

	private DataForLeaderElecionKeepable list;

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

	@Test(expected = DuplicateValueException.class)
	public void testDuplicatesAddition() {
		int testData[] = { 8, 7, 9, 8 };
		list.addAll(testData);
	}

	@Test(expected = NonPositiveValueException.class)
	public void testNonPositiveAddition() {
		int testData[] = { 8, 7, 9, 0 };
		list.addAll(testData);
	}

	@Test(expected = FormatException.class)
	public void testWrongFormatAddition() {
		String[] testData = { "10", "11", "12e" };
		list.addAll(testData);
	}

	@Test(expected = EmptyDataException.class)
	public void testEmptyDataAddition() {
		int[] testData = {};
		list.addAll(testData);
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmtyNext() {
		list = new RingArrayList();
		list.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmtyGetNextNeiborough() {
		list = new RingArrayList();
		list.getNextNeiborough();
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmtyGet() {
		list = new RingArrayList();
		list.get(0);
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
