package graphics;

import java.awt.Graphics;

import algorithm.Agent;

public class BiDirectPanel extends AbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BiDirectSolvable solver;
	private BiDirectRendable renderer;

	public BiDirectPanel(BiDirectSolvable solver, int quantity,
			BiDirectRendable renderer) {
		super(renderer, solver, quantity);
		this.solver = solver;
		this.renderer = renderer;
		maxQuantity = 19;
	}

	@Override
	protected void createMsgs(Agent currentBall, int ballIdx) {
		if (solver.getLeftSenders().contains(ballIdx)) {
			if (solver.getStep() == 1) {
				renderer.drawLeftMsgs(ballIdx, "" + currentBall.getId());
			} else {
				renderer.drawLeftMsgs(ballIdx, "" + currentBall.getLeftMsg());
			}
		}
		if (solver.getRightSenders().contains(ballIdx)) {
			if (solver.getStep() == 1) {
				renderer.drawRightMsgs(ballIdx, "" + currentBall.getId());
			} else {
				renderer.drawRightMsgs(ballIdx, "" + currentBall.getRightMsg());
			}
		}
	}

	@Override
	protected void createLeader(Graphics graphics, Agent currentBall,
			int ballIdx) {
		if (solver.hasSolution()) {
			printLeader(graphics, ballIdx, currentBall);
		}

		if (solver.getCurrentLeaders().contains(ballIdx)
				&& solver.getStep() == 0) {
			renderer.drawCurrentLeaders(ballIdx);
		}
	}

	@Override
	protected void createBalls(Agent currentBall, int ballIdx) {
		renderer.drawBalls(ballIdx, "" + currentBall.getId());
	}

}
