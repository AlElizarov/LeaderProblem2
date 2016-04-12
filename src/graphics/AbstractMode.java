package graphics;

import java.awt.Graphics;

import algorithm.Agent;

public abstract class AbstractMode {

	protected PanelRenderer renderer;
	protected Graphics graphics;
	protected int quantity;

	public AbstractMode(Graphics graphics, int quantity) {
		this.graphics = graphics;
		this.quantity = quantity;
		renderer = new PanelRenderer(graphics, quantity);
	}

	public abstract void createFirstBar();

	public void paintLeader(int ballIdx) {
		renderer.paintLeader(ballIdx);
	}

	public void drawBalls(Agent currentBall, int ballIdx) {
		renderer.drawBalls2(currentBall, ballIdx);
	}

	public void drawMsgs(Agent currentBall, int ballIdx) {
		renderer.drawMsgs2(currentBall, ballIdx);
	}

}
