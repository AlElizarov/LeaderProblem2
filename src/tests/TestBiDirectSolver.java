package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.BiDirectSolver;

public class TestBiDirectSolver {

	private BiDirectSolver solver;

	@Before
	public void setUp() {
		solver = new BiDirectSolver();
	}

	@Test
	public void testHasSolution() {
		solver.add(3);
		solver.solve();
		boolean isSoluted = solver.hasSolution();
		assertTrue(isSoluted);
	}

	@Test
	public void testGetLeaderId() {
		solver.add(3);
		solver.solve();
		int expect = 3;
		int leaderId = solver.getLeaderId();
		assertEquals(expect, leaderId);
	}

	@Test
	public void testBiDirectModeSimpleCaseEven() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(8), new Agent(4),
				new Agent(6) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(8, leaderId);
	}

	@Test
	public void testOneDirectModeSimpleCaseOdd() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(4), new Agent(6) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(7, leaderId);
	}

	@Test
	public void testOneDirectModeOneVariable() {
		Agent[] data = { new Agent(3) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeTwoVariables() {
		Agent[] data = { new Agent(3), new Agent(2) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeBestCaseEven() {
		Agent[] data = { new Agent(6), new Agent(1), new Agent(2),
				new Agent(3), new Agent(4), new Agent(5) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(6, leaderId);
	}

	@Test
	public void testOneDirectModeBestCaseOdd() {
		Agent[] data = { new Agent(5), new Agent(1), new Agent(2),
				new Agent(3), new Agent(4) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(5, leaderId);
	}

	@Test
	public void testOneDirectModeWorstCaseOdd() {
		Agent[] data = { new Agent(3), new Agent(1), new Agent(2) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeWorstCaseEven() {
		Agent[] data = { new Agent(4), new Agent(3), new Agent(2), new Agent(1) };
		solver.addAll(data);
		solve();
		int leaderId = solver.getLeaderId();
		assertEquals(4, leaderId);
	}

	@Test
	public void testPrint() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(8), new Agent(4),
				new Agent(6) };
		solver.addAll(data);
		solve();
		String expect = "<i>”злы, отправл€ющие сообщени€ налево:</i> 4  "
				+ "<br><i>—ообщени€ идущие по часовой стрелке:</i> 8  "
				+ "<br><i>”злы, отправл€ющие сообщени€ направо:</i> 6  "
				+ "<br><i>—ообщени€ идущие против часовой стрелке:</i> 8  ";
		String msgs = solver.printMsgs();
		assertEquals(expect, msgs);
	}

	@Test
	public void testInitiateCurrentLeaders() {
		int[] data = { 3, 7, 2, 5, 1, 8, 4, 6 };
		solver.addAll(data);
		Object[] expect = { 5 };
		solve();
		Object[] actualLeadres = solver.getCurrentLeaders().toArray();
		assertArrayEquals(expect, actualLeadres);
	}

	private void solve() {
		while (!solver.hasSolution()) {
			solver.solve();
		}
	}

}
