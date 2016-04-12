package graphics;

import java.awt.Color;
import java.awt.Graphics;

import algorithm.Agent;

public class BiDirectPanel extends MyAbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BiDirectSolvable solver;
	private int quantity;
	private AbstractPanelRenderer renderer;

	public BiDirectPanel(BiDirectSolvable solver, int quantity) {
		this.solver = solver;
		this.quantity = quantity;
	}

	public void paintComponent(Graphics graphics) {
		renderer = new BiPanelRenderer(graphics, quantity);
		renderer.createFirstBar();
		if (quantity > 19) {
			return;
		}
		int ballIdx = 0;
		while (solver.hasNext()) {
			Agent currentBall = solver.next();
			graphics.setColor(Color.YELLOW);

			if (quantity == 1) {
				continue;
			}

			if (solver.hasSolution()) {
				printLeader(graphics, ballIdx, currentBall);
			}

			if (solver.getCurrentLeaders().contains(ballIdx) && solver.getStep() == 0) {
				renderer.drawCurrentLeaders(ballIdx + 1);
			}

			renderer.drawBalls(ballIdx, "" + currentBall.getId());
			if(solver.getStep() == 0){
				ballIdx++;
				continue;
			}

			if (solver.getLeftSenders().contains(ballIdx)) {
				if (quantity != 2) {
					if (solver.getStep() == 1) {
						renderer.drawLeftMsgs(renderer.newCoordX(ballIdx),
								renderer.newCoordX(ballIdx + 1),
								renderer.newCoordY(ballIdx),
								renderer.newCoordY(ballIdx + 1), ""
										+ currentBall.getId());
					} else {
						renderer.drawLeftMsgs(renderer.newCoordX(ballIdx),
								renderer.newCoordX(ballIdx + 1),
								renderer.newCoordY(ballIdx),
								renderer.newCoordY(ballIdx + 1), ""
										+ currentBall.getLeftMsg());
					}
				} else {
					drawLeftMsgs(graphics, newCoordX(ballIdx) - 20,
							newCoordX(ballIdx + 1) - 20, newCoordY(ballIdx),
							newCoordY(ballIdx + 1), currentBall);
				}
			}
			if (solver.getRightSenders().contains(ballIdx)) {
				if (quantity != 2) {
					if (solver.getStep() == 1) {
						renderer.drawRightMsgs(renderer.newCoordX(ballIdx-1),
								renderer.newCoordX(ballIdx),
								renderer.newCoordY(ballIdx-1),
								renderer.newCoordY(ballIdx),
								"" + currentBall.getId());
					} else {
						renderer.drawRightMsgs(renderer.newCoordX(ballIdx-1),
								renderer.newCoordX(ballIdx),
								renderer.newCoordY(ballIdx-1),
								renderer.newCoordY(ballIdx),
								"" + currentBall.getRightMsg());
					}
				} else {
					renderer.drawRightMsgs(renderer.newCoordX(ballIdx) + 20,
							renderer.newCoordX(ballIdx + 1) + 20,
							renderer.newCoordY(ballIdx),
							renderer.newCoordY(ballIdx + 1),
							"" + currentBall.getRightMsg());
				}
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
