package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Interval;

public class TestInterval {
	
	private Interval interval;
	
	@Before
	public void setUp(){
		interval = new Interval(5, 7);
	}

	@Test
	public void testGetCenter() {
		int expect = 6;
		int actual = interval.getCenter();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveTop() {
		interval.moveTop(10);
		int expect = 16;
		int actual = interval.getCenter();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveDown() {
		interval.moveDown(3);
		int expect = 3;
		int actual = interval.getCenter();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testTurnBack() {
		Interval turned = interval.turnBack();
		int expectStart = 7;
		int actual = turned.getStart();
		assertEquals(expectStart, actual);
	}

}
