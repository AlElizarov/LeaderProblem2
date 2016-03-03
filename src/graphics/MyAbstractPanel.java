package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import algorithm.Agent;
import algorithm.MyAbstractList;

public class MyAbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected int quantity;
	protected int roundCenterX = 350;
	protected int roundCenterY = 300;
	private int radius = 270;
	private int width;
	private int height;
	protected int ARR_SIZE;
	protected int msgSize;
	private int ballSize;
	protected MyAbstractList list;
	protected int taskStep;

	protected void drawBalls(Graphics graphics, int xPos, int yPos,
			Agent currentBall) {
		graphics.fillOval(xPos - (width / 2), yPos - (height / 2), width,
				height);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballSize));
		graphics.drawString("" + currentBall.getId(), xPos - 5, yPos + 5);
	}

	protected void drawMsgs(Graphics graphics, int x1, int x2, int y1, int y2,
			Agent currentBall) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		graphics.drawString("" + currentBall.getNewMsg(), lineCenterX,
				lineCenterY);
	}

	protected void createLines(Graphics graphics, boolean mode) {
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
		int len = (int) Math.sqrt(dx * dx + dy * dy) - width / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		graphics.transform(at);

		// Draw horizontal arrow starting in (0, 0)
		graphics.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE,
				len }, new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
		if (b)
			graphics.drawLine(0, 0, len, 0);
		else {
			graphics.drawLine(0, 0, 0, 0);
		}

	}

	// сделать private
	public double mySin(double degrees) {
		return Math.sin(Math.PI * degrees / 180);
	}

	public double myCos(double degrees) {
		return Math.cos(Math.PI * degrees / 180);
	}

	public int newCoordX(double idx) {
		return (int) (roundCenterX + radius * myCos(idx * (360.0 / quantity)));
	}

	public int newCoordY(double idx) {
		return (int) (roundCenterY + radius * mySin(idx * (360.0 / quantity)));
	}

	public void setQuantity(int q) {
		quantity = q;
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

	private void setParametres(int wh, int Arr_size, int msg_size, int ball_size) {
		width = height = wh;
		ARR_SIZE = Arr_size;
		msgSize = msg_size;
		ballSize = ball_size;
	}

	public void setList(MyAbstractList list2) {
		this.list = list2;
	}

	public void setStep(int step) {
		this.taskStep = step;
	}

}
