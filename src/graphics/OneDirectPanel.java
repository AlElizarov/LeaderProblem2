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
	private AbstractMode mode;

	public OneDirectPanel(OneDirectSolvable solver, int quantity) {
		this.solver = solver;
		this.quantity = quantity;
	}

	public void paintComponent(Graphics graphics) {
		setMode(graphics);
		mode.createFirstBar();
		if (quantity >= 40) {
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
				printLeader(graphics, ballIdx, currentBall);
			}
			
			mode.drawBalls(currentBall, ballIdx);
			if (quantity == 1) {
				continue;
			}

			if (currentBall.getLeftMsg() > 0) {
				mode.drawMsgs(currentBall, ballIdx);
			}
			ballIdx++;
		}
	}

	private void printLeader(Graphics graphics, int ballIdx, Agent currentBall) {
		if (currentBall.getLeftMsg() != currentBall.getId()) {
			graphics.setColor(Color.YELLOW);
		} else {
			mode.paintLeader(ballIdx);
		}
	}

	public void setMode(Graphics graphics) {
		if (quantity == 1) {
			mode = new OneVariableMode(graphics, quantity);
		} else if (quantity == 2) {
			mode = new TwoVariableMode(graphics, quantity);
		} else if (quantity < 40) {
			mode = new FourtyVariableMode(graphics, quantity);
		} else {
			mode = new ManyVariableMode(graphics, quantity);
		}
	}

}
