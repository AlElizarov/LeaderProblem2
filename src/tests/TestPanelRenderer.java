package tests;

import static org.junit.Assert.*;
import graphics.MyCoord;
import graphics.PanelRenderer;

import org.junit.Before;
import org.junit.Test;

public class TestPanelRenderer {

	private PanelRenderer renderer;

	@Before
	public void setUp() {
		renderer = new PanelRenderer(null, new MyCoord(350, 300, 270), 6);
	}

	@Test
	public void testXCoordBall0() {
		int expect = 620;
		int actual = renderer.newCoordX(0);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall1() {
		int expect = 485;
		int actual = renderer.newCoordX(1);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall2() {
		int expect = 215;
		int actual = renderer.newCoordX(2);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall3() {
		int expect = 80;
		int actual = renderer.newCoordX(3);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall4() {
		int expect = 214;
		int actual = renderer.newCoordX(4);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall5() {
		int expect = 485;
		int actual = renderer.newCoordX(5);
		assertEquals(expect, actual);
	}

	@Test
	public void testXCoordBall6() {
		int expect = 620;
		int actual = renderer.newCoordX(6);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall0() {
		int expect = 300;
		int actual = renderer.newCoordY(0);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall1() {
		int expect = 533;
		int actual = renderer.newCoordY(1);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall2() {
		int expect = 533;
		int actual = renderer.newCoordY(2);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall3() {
		int expect = 300;
		int actual = renderer.newCoordY(3);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall4() {
		int expect = 66;
		int actual = renderer.newCoordY(4);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall5() {
		int expect = 66;
		int actual = renderer.newCoordY(5);
		assertEquals(expect, actual);
	}

	@Test
	public void testYCoordBall6() {
		int expect = 299;
		int actual = renderer.newCoordY(6);
		assertEquals(expect, actual);
	}

}
