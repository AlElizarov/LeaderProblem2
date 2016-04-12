package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Trigonometry;

public class TestMyTrigonometry {
	
	private double epsilan = 1E-10;

	@Test
	public void testSin90() {
		double expect = 1;
		double actual = Trigonometry.sin(90);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin0() {
		double expect = 0;
		double actual = Trigonometry.sin(0);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin180() {
		double expect = 0;
		double actual = Trigonometry.sin(180);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin270() {
		double expect = -1;
		double actual = Trigonometry.sin(270);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin30() {
		double expect = 0.5;
		double actual = Trigonometry.sin(30);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin150() {
		double expect = 0.5;
		double actual = Trigonometry.sin(150);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin210() {
		double expect = -0.5;
		double actual = Trigonometry.sin(210);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin330() {
		double expect = -0.5;
		double actual = Trigonometry.sin(330);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos90() {
		double expect = 0;
		double actual = Trigonometry.cos(90);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos0() {
		double expect = 1;
		double actual = Trigonometry.cos(0);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos180() {
		double expect = -1;
		double actual = Trigonometry.cos(180);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos270() {
		double expect = 0;
		double actual = Trigonometry.cos(270);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos60() {
		double expect = 0.5;
		double actual = Trigonometry.cos(60);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos120() {
		double expect = -0.5;
		double actual = Trigonometry.cos(120);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testSin240() {
		double expect = -0.5;
		double actual = Trigonometry.cos(240);
		assertEquals(expect, actual, epsilan);
	}
	
	@Test
	public void testCos300() {
		double expect = 0.5;
		double actual = Trigonometry.cos(300);
		assertEquals(expect, actual, epsilan);
	}

}
