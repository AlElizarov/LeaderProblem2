package graphics;

import java.awt.Color;
import java.awt.Graphics;

import algorithm.Agent;

public class OneDirectPanel extends MyAbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OneDirectSolvable solver;
	private int quantity;
	private AbstractPanelRenderer renderer;

	public OneDirectPanel(OneDirectSolvable solver, int quantity) {
		this.solver = solver;
		this.quantity = quantity;
	}

	public void paintComponent(Graphics graphics) {
		renderer = new OnePanelRenderer(graphics, quantity);
		renderer.createFirstBar();
		if (quantity > 39) {
			return;
		}
		printCircle(graphics);
	}

	private void printCircle(Graphics graphics) {
		int ballIdx = 0;
		while (solver.hasNext()) {
			Agent currentBall = solver.next();
			graphics.setColor(Color.YELLOW);
			if (solver.hasSolution()) {
				printLeader(graphics, ballIdx + 1, currentBall);
			}

			renderer.drawBalls(ballIdx + 1, "" + currentBall.getId());
			if (quantity == 1) {
				continue;
			}

			if (currentBall.getLeftMsg() > 0) {
				renderer.drawMsgs(ballIdx, "" + currentBall.getLeftMsg());
			}
			ballIdx++;
		}
	}

	private void printLeader(Graphics graphics, int ballIdx, Agent currentBall) {
		if (currentBall.getLeftMsg() != currentBall.getId()) {
			graphics.setColor(Color.YELLOW);
		} else {
			renderer.paintLeader(ballIdx);
		}
	}

}
