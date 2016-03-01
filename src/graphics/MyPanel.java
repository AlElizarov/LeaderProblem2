package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import algorithm.Agent;
import algorithm.MyRingList;

public class MyPanel extends JPanel {

	private int quantity;
	private int roundCenterX = 350;
	private int roundCenterY = 300;
	private int radius = 270;
	private int width;
	private int height;
	private int ARR_SIZE;
	private int msgSize;
	private int ballSize;
	private boolean isBidirect;
	private MyRingList list;
	private int taskStep;
	private int xLeaderPos;
	private int yLeaderPos;

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

	public void setMode(boolean isBidirect) {
		this.isBidirect = isBidirect;
	}

	private void setParametres(int wh, int Arr_size, int msg_size, int ball_size) {
		width = height = wh;
		ARR_SIZE = Arr_size;
		msgSize = msg_size;
		ballSize = ball_size;
		xLeaderPos = roundCenterX;
		yLeaderPos = roundCenterY;
		if (quantity == 2 || quantity == 1) {
			yLeaderPos -= 100;
		}
	}

	public void setList(MyRingList list) {
		this.list = list;
	}

	public void setStep(int step) {
		this.taskStep = step;
	}

	public void paintComponent(Graphics graphics) {
		if (quantity >= 40) {
			graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
			graphics.drawString(
					"    Визуальный режим доступен ",
					100, 150);
			//graphics.drawString("\n", 100, 120);
			graphics.drawString("только при объеме входных", 100, 190);
			graphics.drawString("данных больше 40. Вы можете", 100, 230);
			graphics.drawString("продолжить работу в текстовом режиме", 100, 270);
			return;
		}
		if (quantity == 2) {
			ARR_SIZE = 8;
			createLine(graphics, newCoordX(1), newCoordY(1) - 8, newCoordX(2),
					newCoordY(2) - 8);
			createLine(graphics, newCoordX(2), newCoordY(2) + 8, newCoordX(1),
					newCoordY(1) + 8);
		} else if (quantity != 1)
			createLines(graphics);
		int ballIdx = 0;
		while (list.hasNext()) {
			Agent currentBall = list.next();
			if (currentBall.getMsg() != currentBall.getId()) {
				graphics.setColor(Color.YELLOW);
			} else {
				graphics.setColor(Color.red);
				int oldArrSize = ARR_SIZE;
				ARR_SIZE = 12;
				graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
				graphics.drawString("LEADER", xLeaderPos - 30, yLeaderPos);
				if (quantity == 1) {
					createLine(graphics, xLeaderPos, yLeaderPos, roundCenterX,
							roundCenterY);
				} else
					createLine(graphics, xLeaderPos, yLeaderPos,
							newCoordX(ballIdx + 1), newCoordY(ballIdx + 1));
				ARR_SIZE = oldArrSize;
			}

			if (quantity == 1) {
				drawBalls(graphics, roundCenterX, roundCenterY, currentBall);
				continue;
			} else {
				drawBalls(graphics, newCoordX(ballIdx + 1),
						newCoordY(ballIdx + 1), currentBall);
			}

			if (taskStep == 1 || currentBall.getMsg() > 0) {
				if (quantity == 2 && ballIdx == 0) {
					drawMsgs(graphics, newCoordX(ballIdx) + 30,
							newCoordX(ballIdx + 1) + 30,
							newCoordY(ballIdx) + 10,
							newCoordY(ballIdx + 1) + 10, currentBall);
				} else
					drawMsgs(graphics, newCoordX(ballIdx),
							newCoordX(ballIdx + 1), newCoordY(ballIdx),
							newCoordY(ballIdx + 1), currentBall);
			}
			ballIdx++;
		}
	}

	private void drawBalls(Graphics graphics, int xPos, int yPos,
			Agent currentBall) {
		graphics.fillOval(xPos - (width / 2), yPos - (height / 2), width,
				height);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballSize));
		graphics.drawString("" + currentBall.getId(), xPos - 5, yPos + 5);
	}

	private void drawMsgs(Graphics graphics, int x1, int x2, int y1, int y2,
			Agent currentBall) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		graphics.drawString("" + currentBall.getNewMsg(), lineCenterX,
				lineCenterY);
	}

	private void createLines(Graphics graphics) {
		for (int i = 0; i < quantity; i++) {
			int xStart = newCoordX(i + 1);
			int xEnd = newCoordX(i + 2);
			int yStart = newCoordY(i + 1);
			int yEnd = newCoordY(i + 2);
			createLine(graphics, xStart, yStart, xEnd, yEnd);
		}
	}

	private void createLine(Graphics g, int x1, int y1, int x2, int y2) {
		drawArrow(g, x1, y1, x2, y2, true);
		if (isBidirect) {
			drawArrow(g, x2, y2, x1, y1, false);
		}
	}

	private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2,
			boolean mode) {
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
		if (mode) {
			graphics.drawLine(0, 0, len, 0);
		} else {
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

}
