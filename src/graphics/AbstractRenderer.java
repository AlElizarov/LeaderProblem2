package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utils.Interval;
import utils.LineSegment;

public abstract class AbstractRenderer implements IRenderer {

	protected int quantity;
	protected Center coordCenter;
	protected int arrSize;
	protected int msgSize;
	protected int diametr;
	protected int ballTitleSize;
	protected int xLeaderPos;
	protected int yLeaderPos;
	protected Graphics graphics;
	protected int maxQuantity;

	public AbstractRenderer(Center coord, int quantity) {
		this.coordCenter = coord;
		this.quantity = quantity;
		setLeaderPos();
		setRenderParameters();
	}

	public AbstractRenderer(int quantity) {
		this(new Center(350, 300, 270), quantity);
	}

	@Override
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	protected abstract void drawLines(LineSegment segment);

	protected abstract void setRenderParameters();

	protected void setParametres(int diametr, int arrSize, int msgSize,
			int ballTitleSize) {
		this.diametr = diametr;
		this.arrSize = arrSize;
		this.msgSize = msgSize;
		this.ballTitleSize = ballTitleSize;
	}

	protected int xCoordBall(int idx) {
		return coordCenter.calculateXPos(quantity, idx);
	}

	protected int yCoordBall(double idx) {
		return coordCenter.calculateYPos(quantity, idx);
	}

	@Override
	public void createFirstBar() {
		if (quantity == 2) {
			createFirstBarForTwo();
			return;
		}
		if (quantity > maxQuantity) {
			createFirstBarForMany();
			return;
		}
		if (quantity == 1) {
			return;
		}
		createLines();
	}

	private void createFirstBarForMany() {
		int fontSize = 26;
		int textXPos = 100;
		int textYPos = 150;
		int textYMargin = 40;
		graphics.setFont(new Font("Veranda", Font.ITALIC, fontSize));
		graphics.drawString("    Визуальный режим доступен ", textXPos,
				textYPos);
		textYPos += textYMargin;
		graphics.drawString("только при объеме входных", textXPos, textYPos);
		textYPos += textYMargin;
		graphics.drawString("данных больше " + maxQuantity + ". Вы можете",
				textXPos, textYPos);
		textYPos += textYMargin;
		graphics.drawString("продолжить работу в текстовом режиме", textXPos,
				textYPos);
	}

	protected void createFirstBarForTwo() {
		int margin = 8;
		int xStart = xCoordBall(1);
		int xEnd = xCoordBall(2);
		int yStart = yCoordBall(1) - margin;
		int yEnd = yCoordBall(2) - margin;
		drawArrow(createSegment(xStart, xEnd, yStart, yEnd));
		xStart = xCoordBall(2);
		xEnd = xCoordBall(1);
		yStart = yCoordBall(2) + margin;
		yEnd = yCoordBall(1) + margin;
		drawArrow(createSegment(xStart, xEnd, yStart, yEnd));
	}

	@Override
	public void paintLeader(int ballIdx) {
		paintLeader(ballIdx, Color.red, "LEADER!!!");
	}

	@Override
	public void paintLeader(int ballIdx, Color color, String msg) {
		graphics.setColor(color);
		int oldArrSize = arrSize;
		arrSize = 12;
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		graphics.drawString(msg, xLeaderPos - 30, yLeaderPos);
		createLeaderArrow(ballIdx);
		arrSize = oldArrSize;
	}

	private void createLeaderArrow(int ballIdx) {
		int xPos = calculateXPos(ballIdx);
		int yPos = calculateYPos(ballIdx);
		drawArrow(createSegment(xLeaderPos, xPos, yLeaderPos, yPos));
	}

	private int calculateYPos(int ballIdx) {
		if (quantity == 1) {
			return coordCenter.getRoundCenterY();
		}
		return yCoordBall(ballIdx);
	}

	private int calculateXPos(int ballIdx) {
		if (quantity == 1) {
			return coordCenter.getRoundCenterX();
		}
		return xCoordBall(ballIdx);
	}

	@Override
	public void drawBalls(int ballIdx, String ballTitle) {
		int xPos = calculateXPos(ballIdx);
		int yPos = calculateYPos(ballIdx);
		graphics.fillOval(xPos - (diametr / 2), yPos - (diametr / 2), diametr,
				diametr);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballTitleSize));
		graphics.drawString(ballTitle, xPos - 5, yPos + 5);
	}

	protected void drawArrow(LineSegment segment) {
		Graphics2D gr = (Graphics2D) graphics.create();

		int x1 = segment.getXInterval().getStart();
		int x2 = segment.getXInterval().getEnd();
		int y1 = segment.getYInterval().getStart();
		int y2 = segment.getYInterval().getEnd();
		double dx = x2 - x1;
		double dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int arrowLen = (int) Math.sqrt(dx * dx + dy * dy) - diametr / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		gr.transform(at);

		// Draw horizontal arrow starting in (0, 0)
		gr.fillPolygon(new int[] { arrowLen, arrowLen - arrSize,
				arrowLen - arrSize, arrowLen }, new int[] { 0, -arrSize,
				arrSize, 0 }, 4);
		gr.drawLine(0, 0, arrowLen, 0);
	}

	private void createLines() {
		for (int i = 0; i < quantity; i++) {
			int xStart = xCoordBall(i + 1);
			int xEnd = xCoordBall(i + 2);
			int yStart = yCoordBall(i + 1);
			int yEnd = yCoordBall(i + 2);
			drawLines(createSegment(xStart, xEnd, yStart, yEnd));
		}
	}

	protected LineSegment createSegment(int xStart, int xEnd, int yStart,
			int yEnd) {
		Interval xInterval = new Interval(xStart, xEnd);
		Interval yInterval = new Interval(yStart, yEnd);
		return new LineSegment(xInterval, yInterval);
	}

	private void setLeaderPos() {
		xLeaderPos = coordCenter.getRoundCenterX();
		yLeaderPos = coordCenter.getRoundCenterY();
		if (quantity == 1 || quantity == 2) {
			yLeaderPos -= 100;
		}
	}

}
