package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algorithm.Agent;
import algorithm.BiDirectSolver;
import algorithm.RingArrayList;

public class TestBiDirectSolver {

	private RingArrayList list;
	private int stage;
	private int step;
	private BiDirectSolver solver;

	@Before
	public void setUp() {
		list = new RingArrayList();
		stage = 0;
		step = 0;
	}

	@Test
	public void testBiDirectModeSimpleCaseEven() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(8), new Agent(4),
				new Agent(6) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals("stage: " + stage + "step: " + step, 8, leaderId);
	}

	@Test
	public void testOneDirectModeSimpleCaseOdd() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(4), new Agent(6) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(7, leaderId);
	}

	@Test
	public void testOneDirectModeOneVariable() {
		Agent[] data = { new Agent(3) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeTwoVariables() {
		Agent[] data = { new Agent(3), new Agent(2) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeBestCaseEven() {
		Agent[] data = { new Agent(6), new Agent(1), new Agent(2),
				new Agent(3), new Agent(4), new Agent(5) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(6, leaderId);
	}

	@Test
	public void testOneDirectModeBestCaseOdd() {
		Agent[] data = { new Agent(5), new Agent(1), new Agent(2),
				new Agent(3), new Agent(4) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(5, leaderId);
	}

	@Test
	public void testOneDirectModeWorstCaseOdd() {
		Agent[] data = { new Agent(3), new Agent(1), new Agent(2) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(3, leaderId);
	}

	@Test
	public void testOneDirectModeWorstCaseEven() {
		Agent[] data = { new Agent(4), new Agent(3), new Agent(2), new Agent(1) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		int leaderId = list.getLeaderId();
		assertEquals(4, leaderId);
	}

	@Test
	public void testPrint() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(8), new Agent(4),
				new Agent(6) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		solve();
		String expect = "<i>”злы, отправл€ющие сообщени€ налево:</i> 4  "
				+ "<br><i>—ообщени€ идущие по часовой стрелке:</i> 0  "
				+ "<br><i>”злы, отправл€ющие сообщени€ направо:</i> 6  "
				+ "<br><i>—ообщени€ идущие против часовой стрелке:</i> 8  ";
		String msgs = solver.printMsgs();
		assertEquals(expect, msgs);
	}

	@Test
	public void testInitiateCurrentLeaders() {
		Agent[] data = { new Agent(3), new Agent(7), new Agent(2),
				new Agent(5), new Agent(1), new Agent(8), new Agent(4),
				new Agent(6) };
		list.addAll(data);
		solver = new BiDirectSolver(list);
		Object[] expect = { 5};
		solve();
		Object[] actualLeadres = solver.getCurrentLeaders().toArray();
		assertArrayEquals(expect, actualLeadres);
	}

	private void solve() {
		while (!list.hasSolution()) {
			solver.solve(stage, step);
			if (step == Math.pow(2, stage)) {
				stage++;
				step = 0;
			} else {
				step++;
			}
		}
	}

}
