package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import algorithm.Agent;
import utils.Trigonometry;

public class PanelRenderer {

	private int quantity;
	private MyCoord coord;
	private int arrSize;
	private int msgSize;
	private int diametr;
	private int ballTitleSize;
	protected int xLeaderPos;
	protected int yLeaderPos;
	private Graphics graphics;

	public PanelRenderer(Graphics graphics, MyCoord coord, int quantity) {
		this.graphics = graphics;
		this.coord = coord;
		setQuantity(quantity);
	}

	public PanelRenderer(Graphics graphics, int quantity) {
		this.graphics = graphics;
		coord = new MyCoord(350, 300, 270);
		setQuantity(quantity);
	}

	public MyCoord getCoord() {
		return coord;
	}

	protected void drawBalls(int xPos, int yPos, Agent currentBall) {
		graphics.fillOval(xPos - (diametr / 2), yPos - (diametr / 2), diametr,
				diametr);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballTitleSize));
		graphics.drawString("" + currentBall.getId(), xPos - 5, yPos + 5);
	}

	protected void drawMsgs(int x1, int x2, int y1, int y2, Agent currentBall) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		graphics.drawString("" + currentBall.getNewLeftMsg(), lineCenterX,
				lineCenterY);
	}

	protected void drawLeftMsgs(int x1, int x2, int y1, int y2,
			Agent currentBall) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		lineCenterX = (lineCenterX + x2) / 2;
		lineCenterY = (lineCenterY + y2) / 2;
		graphics.drawString("" + currentBall.getLeftMsg(), lineCenterX,
				lineCenterY);
	}

	protected void drawRightMsgs(int x1, int x2, int y1, int y2,
			Agent currentBall) {
		graphics.setColor(new Color(0, 100, 0));
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		lineCenterX = (x1 + lineCenterX) / 2;
		lineCenterY = (y1 + lineCenterY) / 2;
		graphics.drawString("" + currentBall.getRightMsg(), lineCenterX,
				lineCenterY);
	}

	protected void createLines(boolean mode) {
		for (int i = 0; i < quantity; i++) {
			int xStart = newCoordX(i + 1);
			int xEnd = newCoordX(i + 2);
			int yStart = newCoordY(i + 1);
			int yEnd = newCoordY(i + 2);
			if (!mode) {
				drawArrow(graphics, xStart, yStart, xEnd, yEnd, true);
			} else {
				drawArrow(graphics, xStart, yStart, xEnd, yEnd, true);
				drawArrow(graphics, xEnd, yEnd, xStart, yStart, false);
			}
		}
	}

	protected void drawArrow(Graphics g1, int x1, int y1, int x2, int y2,
			boolean b) {
		Graphics2D graphics = (Graphics2D) g1.create();

		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx * dx + dy * dy) - diametr / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		graphics.transform(at);

		// Draw horizontal arrow starting in (0, 0)
		graphics.fillPolygon(
				new int[] { len, len - arrSize, len - arrSize, len },
				new int[] { 0, -arrSize, arrSize, 0 }, 4);
		if (b)
			graphics.drawLine(0, 0, len, 0);
		else {
			graphics.drawLine(0, 0, 0, 0);
		}

	}

	public int newCoordX(double idx) {
		int roundCenterX = coord.getRoundCenterX();
		int radius = coord.getRadius();
		return (int) (roundCenterX + radius
				* Trigonometry.cos(idx * (360.0 / quantity)));
	}

	public int newCoordY(double idx) {
		int roundCenterY = coord.getRoundCenterY();
		int radius = coord.getRadius();
		return (int) (roundCenterY + radius
				* Trigonometry.sin(idx * (360.0 / quantity)));
	}

	public void paintLeader(int ballIdx) {
		graphics.setColor(Color.red);
		int oldArrSize = arrSize;
		arrSize = 12;
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		graphics.drawString("LEADER", xLeaderPos - 30, yLeaderPos);
		if (quantity == 1) {
			drawArrow(graphics, xLeaderPos, yLeaderPos, getCoord()
					.getRoundCenterX(), getCoord().getRoundCenterY(), true);
		} else
			drawArrow(graphics, xLeaderPos, yLeaderPos, newCoordX(ballIdx + 1),
					newCoordY(ballIdx + 1), true);
		arrSize = oldArrSize;
	}

	public void drawBalls2(Agent currentBall, int ballIdx) {
		if (quantity == 1) {
			drawBalls(coord.getRoundCenterX(), coord.getRoundCenterY(),
					currentBall);
		} else {
			drawBalls(newCoordX(ballIdx + 1), newCoordY(ballIdx + 1),
					currentBall);
		}
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
		xLeaderPos = coord.getRoundCenterX();
		yLeaderPos = coord.getRoundCenterY();
		if (quantity == 1) {
			yLeaderPos -= 100;
		}
		if (quantity < 10) {
			setParametres(35, 10, 24, 20);
		} else if (quantity < 20) {
			setParametres(30, 8, 20, 18);
		} else if (quantity < 30) {
			setParametres(25, 6, 16, 16);
		} else if (quantity < 40) {
			setParametres(20, 5, 14, 14);
		}
	}

	private void setParametres(int diametr, int Arr_size, int msg_size,
			int ballTitleSize) {
		this.diametr = diametr;
		arrSize = Arr_size;
		msgSize = msg_size;
		this.ballTitleSize = ballTitleSize;
	}

	public void drawMsgs2(Agent currentBall, int ballIdx) {
		if (quantity == 2 && ballIdx == 0) {
			drawMsgs(newCoordX(ballIdx) + 30, newCoordX(ballIdx + 1) + 30,
					newCoordY(ballIdx) + 10, newCoordY(ballIdx + 1) + 10,
					currentBall);
		} else
			drawMsgs(newCoordX(ballIdx), newCoordX(ballIdx + 1),
					newCoordY(ballIdx), newCoordY(ballIdx + 1), currentBall);
	}

}
