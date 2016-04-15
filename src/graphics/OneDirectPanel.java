package graphics;

import java.awt.Graphics;

import algorithm.Agent;
import algorithm.OneDirectSolver;

public class OneDirectPanel extends AbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OneDirectSolver solver;
	private OneDirectRendable renderer;

	public OneDirectPanel(OneDirectSolver solver, int quantity,
			OneDirectRendable renderer) {
		super(renderer, solver, quantity);
		this.solver = solver;
		this.renderer = renderer;
		maxQuantity = 39;
	}

	@Override
	protected void createMsgs(Agent currentBall, int ballIdx) {
		if (currentBall.getLeftMsg() > 0) {
			renderer.drawMsgs(ballIdx, "" + currentBall.getLeftMsg());
		}
	}

	@Override
	protected void createLeader(Graphics graphics, Agent currentBall,
			int ballIdx) {
		if (solver.hasSolution()) {
			printLeader(graphics, ballIdx + 1, currentBall);
		}
	}

	@Override
	protected void createBalls(Agent currentBall, int ballIdx) {
		renderer.drawBalls(ballIdx + 1, "" + currentBall.getId());
	}

}
