package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Interval;
import utils.LineSegment;

public class TestLineSegment {
	
	private LineSegment segment;
	
	@Before
	public void setUp(){
		segment = new LineSegment(new Interval(5, 7), new Interval(5, 7));
	}
	
	@Test
	public void testGetCenterX() {
		int expect = 6;
		int actual = segment.getCenterX();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testGetCenterY() {
		int expect = 6;
		int actual = segment.getCenterY();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveXRight() {
		segment.moveXRight(10);
		int expect = 16;
		int actual = segment.getCenterX();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveXLeft() {
		segment.moveXLeft(2);
		int expect = 4;
		int actual = segment.getCenterX();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveYTop() {
		segment.moveYTop(8);
		int expect = 14;
		int actual = segment.getCenterY();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveYDown() {
		segment.moveYDown(4);
		int expect = 2;
		int actual = segment.getCenterY();
		assertEquals(expect, actual);
	}
	
	@Test
	public void testMoveTurnBack() {
		LineSegment turned = segment.turnBack();
		int expectYStart = 7;
		int actual = turned.getYInterval().getStart();
		assertEquals(expectYStart, actual);
	}

}
