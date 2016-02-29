package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import algorithm.Agent;
import algorithm.MyRingList;

public class MyPanel extends JPanel{
	
	private int quantity;
	private int roundCenterX = 400;
	private int roundCenterY = 300;
	private int radius = 270;
	private int width;
	private int height;
	private int ARR_SIZE;
	private int msgSize;
	private int ballSize;
	private boolean isBidirect = true;
	private MyRingList list;
	private int taskStep;

	public MyPanel(int quantity) {
		this.quantity = quantity;
		if (quantity < 10) {
			setParametres(35, 10, 24, 20);
		} else if (quantity < 20) {
			setParametres(30, 8, 20, 18);
		} else if (quantity < 30) {
			setParametres(25, 6, 16, 16);
		} else if (quantity < 40) {
			setParametres(20, 4, 14, 14);
		}
	}

	private void setParametres(int wh, int Arr_size, int msg_size, int ball_size) {
		width = height = wh;
		ARR_SIZE = Arr_size;
		msgSize = msg_size;
		ballSize = ball_size;
	}

	public void setList(MyRingList list) {
		this.list = list;
	}

	public void setStep(int step) {
		this.taskStep = step;
	}

	public void paintComponent(Graphics graphics) {
		createLines(graphics);
		int ballIdx = 0;
		while (list.hasNext()) {
			Agent currentBall = list.next();
			if (currentBall.getMsg() != currentBall.getId()) {
				graphics.setColor(Color.YELLOW);
			} else {
				graphics.setColor(Color.red);
			}

			drawBalls(graphics, ballIdx, currentBall);
			
			if (taskStep == 1 || currentBall.getMsg() > 0) {
				drawMsgs(graphics, ballIdx, currentBall);
			}
			ballIdx++;
		}
	}

	private void drawBalls(Graphics graphics, int ballIdx, Agent currentBall) {
		graphics.fillOval(newCoordX(ballIdx + 1) - (width / 2), newCoordY(ballIdx + 1)
				- (height / 2), width, height);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballSize));
		graphics.drawString("" + currentBall.getId(), newCoordX(ballIdx + 1) - 5,
				newCoordY(ballIdx + 1) + 5);
	}

	private void drawMsgs(Graphics graphics, int ballIdx, Agent currentBall) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (newCoordX(ballIdx) + newCoordX(ballIdx + 1)) / 2;
		int lineCenterY = (newCoordY(ballIdx) + newCoordY(ballIdx + 1)) / 2;
		graphics.drawString("" + currentBall.getNewMsg(), lineCenterX, lineCenterY);
	}

	private void createLines(Graphics graphics) {
		for (int i = 0; i < quantity; i++) {
			graphics.setColor(Color.black);
			drawArrow(graphics, newCoordX(i + 1), newCoordY(i + 1), newCoordX(i + 2),
					newCoordY(i + 2), i, true);
			if (isBidirect) {
				drawArrow(graphics, newCoordX(i + 2), newCoordY(i + 2),
						newCoordX(i + 1), newCoordY(i + 1), i, false);
			}
		}
	}

	private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2,
			int step, boolean mode) {
		Graphics2D graphics = (Graphics2D) g1.create();

		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx * dx + dy * dy) - width / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		graphics.transform(at);

		// Draw horizontal arrow starting in (0, 0)
		graphics.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
				new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
		if (mode) {
			graphics.drawLine(0, 0, len, 0);
		} else {
			graphics.drawLine(0, 0, 0, 0);
		}

	}

	//сделать private
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

}
