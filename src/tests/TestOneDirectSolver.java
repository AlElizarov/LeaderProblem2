package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.OneDirectSolver;
import algorithm.Solver;

public class TestOneDirectSolver {

	private Solver solver;

	@Before
	public void setUp() {
		solver = new OneDirectSolver();
	}

	@Test
	public void testOneDirectModeSimpleCaseEven() {
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
		Agent[] data = { new Agent(4), new Agent(3), new Agent(2), new Agent(1) };
		solver.addAll(data);
		solve();
		String msgs = solver.printMsgs();
		String expect = "<font color = red>4</font> <font color = balck>"
				+ "0</font> <font color = balck>"
				+ "0</font> <font color = balck>0</font> ";
		assertEquals(expect, msgs);
	}

	private void solve() {
		while (!solver.hasSolution()) {
			solver.solve();
		}
	}

}
