package utils;

public class LineSegment {

	private Interval xInterval;
	private Interval yInterval;

	public LineSegment(Interval xInterval, Interval yInterval) {
		this.xInterval = xInterval;
		this.yInterval = yInterval;
	}

	public Interval getXInterval() {
		return xInterval;
	}

	public Interval getYInterval() {
		return yInterval;
	}

	public void moveXRight(int shift) {
		xInterval.moveTop(shift);
	}

	public void moveYTop(int shift) {
		yInterval.moveTop(shift);
	}

	public int getCenterX() {
		return xInterval.getCenter();
	}

	public int getCenterY() {
		return yInterval.getCenter();
	}

	public LineSegment turnBack() {
		return new LineSegment(xInterval.turnBack(), yInterval.turnBack());
	}

	public void moveXLeft(int shift) {
		xInterval.moveDown(shift);
	}

	public void moveYDown(int shift) {
		yInterval.moveDown(shift);
	}

}
