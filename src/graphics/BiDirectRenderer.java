package graphics;

import java.awt.Color;
import java.awt.Font;

import utils.LineSegment;

public class BiDirectRenderer extends AbstractRenderer implements
		BiDirectRendable {

	public BiDirectRenderer(int quantity) {
		super(quantity);
	}

	public BiDirectRenderer(Center coord, int quantity) {
		super(coord, quantity);
	}

	public void drawLeftMsgs(int ballIdx, String leftMsg) {
		int x1 = xCoordBall(ballIdx);
		int x2 = xCoordBall(ballIdx + 1);
		int y1 = yCoordBall(ballIdx);
		int y2 = yCoordBall(ballIdx + 1);
		LineSegment segment = createSegment(x1, x2, y1, y2);
		graphics.setColor(Color.GREEN);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = segment.getCenterX();
		int lineCenterY = segment.getCenterY();
		segment = createSegment(lineCenterX, x2, lineCenterY, y2);
		lineCenterX = segment.getCenterX();
		lineCenterY = segment.getCenterY();
		graphics.drawString(leftMsg, lineCenterX, lineCenterY);
	}

	public void drawRightMsgs(int ballIdx, String rightMsg) {
		int x1 = xCoordBall(ballIdx - 1);
		int x2 = xCoordBall(ballIdx);
		int y1 = yCoordBall(ballIdx - 1);
		int y2 = yCoordBall(ballIdx);
		if (quantity == 2) {
			x1 -= 20;
			x2 -= 20;
		}
		LineSegment segment = createSegment(x1, x2, y1, y2);
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = segment.getCenterX();
		int lineCenterY = segment.getCenterY();
		segment = createSegment(x1, lineCenterX, y1, lineCenterY);
		lineCenterX = segment.getCenterX();
		lineCenterY = segment.getCenterY();
		graphics.drawString(rightMsg, lineCenterX, lineCenterY);
	}

	@Override
	protected void setRenderParameters() {
		if (quantity < 10) {
			setParametres(35, 10, 24, 20);
		} else {
			setParametres(30, 8, 20, 18);
		}
		maxQuantity = 19;
	}

	@Override
	protected void drawLines(LineSegment segment) {
		drawArrow(segment);
		drawArrow(segment.turnBack());
	}

	public void drawCurrentLeaders(int ballIdx) {
		paintLeader(ballIdx, Color.BLUE, "CURRENT LEADER");
	}

	@Override
	protected void createFirstBarForTwo() {
		int margin = 8;
		int xStart = xCoordBall(1);
		int xEnd = xCoordBall(2);
		int yStart = yCoordBall(1) - margin;
		int yEnd = yCoordBall(2) - margin;
		drawArrow(createSegment(xStart, xEnd, yStart, yEnd));
		drawArrow(createSegment(xEnd, xStart, yEnd, yStart));
		xStart = xCoordBall(2);
		xEnd = xCoordBall(1);
		yStart = yCoordBall(2) + margin;
		yEnd = yCoordBall(1) + margin;
		drawArrow(createSegment(xStart, xEnd, yStart, yEnd));
		drawArrow(createSegment(xEnd, xStart, yEnd, yStart));
	}

}
