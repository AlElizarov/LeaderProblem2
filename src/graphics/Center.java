package graphics;

import utils.Trigonometry;

public class Center {

	private int roundCenterX;
	private int roundCenterY;
	private int radius;

	public Center(int roundCenterX, int roundCenterY, int radius) {
		this.roundCenterX = roundCenterX;
		this.roundCenterY = roundCenterY;
		this.radius = radius;
	}

	public int getRoundCenterX() {
		return roundCenterX;
	}

	public int getRoundCenterY() {
		return roundCenterY;
	}

	public int calculateXPos(int quantityOfNodes, int currentIdx) {
		double angle = currentIdx * (360.0 / quantityOfNodes);
		double shift = radius * Trigonometry.cos(angle);
		int newXPos = (int) (roundCenterX + shift);
		return newXPos;
	}

	public int calculateYPos(int quantityOfNodes, double currentIdx) {
		double angle = currentIdx * (360.0 / quantityOfNodes);
		double shift = radius * Trigonometry.sin(angle);
		int newYPos = (int) (roundCenterY + shift);
		return newYPos;
	}

}
