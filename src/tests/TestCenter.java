package tests;

import static org.junit.Assert.*;
import graphics.Center;

import org.junit.Before;
import org.junit.Test;

public class TestCenter {
	
	private Center center;
	
	@Before
	public void setUp(){
		center = new Center(350, 300, 270);
	}

	@Test
	public void testXBall0() {
		int expect = 620;
		int actual = center.calculateXPos(6, 0);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall1() {
		int expect = 485;
		int actual = center.calculateXPos(6, 1);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall2() {
		int expect = 215;
		int actual = center.calculateXPos(6, 2);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall3() {
		int expect = 80;
		int actual = center.calculateXPos(6, 3);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall4() {
		int expect = 214;
		int actual = center.calculateXPos(6, 4);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall5() {
		int expect = 485;
		int actual = center.calculateXPos(6, 5);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testXBall6() {
		int expect = 620;
		int actual = center.calculateXPos(6, 6);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall0() {
		int expect = 300;
		int actual = center.calculateYPos(6, 0);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall1() {
		int expect = 533;
		int actual = center.calculateYPos(6, 1);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall2() {
		int expect = 533;
		int actual = center.calculateYPos(6, 2);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall3() {
		int expect = 300;
		int actual = center.calculateYPos(6, 3);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall4() {
		int expect = 66;
		int actual = center.calculateYPos(6, 4);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall5() {
		int expect = 66;
		int actual = center.calculateYPos(6, 5);
		assertEquals(expect, actual);
	}
	
	@Test
	public void testYBall6() {
		int expect = 299;
		int actual = center.calculateYPos(6, 6);
		assertEquals(expect, actual);
	}

}
