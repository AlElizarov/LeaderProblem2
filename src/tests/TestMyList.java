package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.MyRingList;

public class TestMyList {

	@Test
	public void test() {
		MyRingList list = new MyRingList();
		assertTrue(list.isEmpty());
	}

}
