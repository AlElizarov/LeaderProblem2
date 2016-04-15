package graphics;

public class MyCoord {

	private int roundCenterX;
	private int roundCenterY;
	private int radius;

	public MyCoord(int roundCenterX, int roundCenterY, int radius) {
		this.roundCenterX = roundCenterX;
		this.roundCenterY = roundCenterY;
		this.radius = radius;
	}

	public int getRoundCenterX() {
		return roundCenterX;
	}

	public void setRoundCenterX(int roundCenterX) {
		this.roundCenterX = roundCenterX;
	}

	public int getRoundCenterY() {
		return roundCenterY;
	}

	public void setRoundCenterY(int roundCenterY) {
		this.roundCenterY = roundCenterY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
