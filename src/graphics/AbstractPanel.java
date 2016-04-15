package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import algorithm.Agent;

public abstract class AbstractPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRenderer renderer;
	private ISolver solver;
	protected int maxQuantity;
	protected int quantity;

	public AbstractPanel(IRenderer renderer, ISolver solver, int quantity) {
		this.renderer = renderer;
		this.solver = solver;
		this.quantity = quantity;
	}

	public void paintComponent(Graphics graphics) {
		renderer.setGraphics(graphics);
		renderer.createFirstBar();
		if (quantity > maxQuantity) {
			return;
		}
		printCircle(graphics);
	}

	private void printCircle(Graphics graphics) {
		int ballIdx = 0;
		while (solver.hasNext()) {
			Agent currentBall = solver.next();
			graphics.setColor(Color.YELLOW);
			createLeader(graphics, currentBall, ballIdx);
			createBalls(currentBall, ballIdx);
			if (quantity == 1) {
				continue;
			}
			createMsgs(currentBall, ballIdx);
			ballIdx++;
		}
	}

	protected abstract void createMsgs(Agent currentBall, int ballIdx);

	protected abstract void createLeader(Graphics graphics, Agent currentBall,
			int ballIdx);

	protected abstract void createBalls(Agent currentBall, int ballIdx);

	protected void printLeader(Graphics graphics, int ballIdx, Agent currentBall) {
		if (currentBall.getLeftMsg() != currentBall.getId()) {
			graphics.setColor(Color.YELLOW);
		} else {
			renderer.paintLeader(ballIdx);
		}
	}

}
