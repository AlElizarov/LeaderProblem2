package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utils.Trigonometry;

public abstract class AbstractPanelRenderer {

	protected int quantity;
	protected MyCoord coord;
	protected int arrSize;
	protected int msgSize;
	protected int diametr;
	protected int ballTitleSize;
	protected int xLeaderPos;
	protected int yLeaderPos;
	protected Graphics graphics;
	protected int maxQuantity;

	public AbstractPanelRenderer(Graphics graphics, MyCoord coord, int quantity) {
		this.graphics = graphics;
		this.coord = coord;
		this.quantity = quantity;
		setLeaderPos();
		setRenderParameters();
	}

	private void setLeaderPos() {
		xLeaderPos = coord.getRoundCenterX();
		yLeaderPos = coord.getRoundCenterY();
		if (quantity == 1 || quantity == 2) {
			yLeaderPos -= 100;
		}
	}

	public AbstractPanelRenderer(Graphics graphics, int quantity) {
		this(graphics, new MyCoord(350, 300, 270), quantity);
	}

	public MyCoord getCoord() {
		return coord;
	}

	protected void setParametres(int diametr, int Arr_size, int msg_size,
			int ballTitleSize) {
		this.diametr = diametr;
		arrSize = Arr_size;
		msgSize = msg_size;
		this.ballTitleSize = ballTitleSize;
	}

	protected int newCoordX(double idx) {
		int roundCenterX = coord.getRoundCenterX();
		int radius = coord.getRadius();
		return (int) (roundCenterX + radius
				* Trigonometry.cos(idx * (360.0 / quantity)));
	}

	protected int newCoordY(double idx) {
		int roundCenterY = coord.getRoundCenterY();
		int radius = coord.getRadius();
		return (int) (roundCenterY + radius
				* Trigonometry.sin(idx * (360.0 / quantity)));
	}

	public void createFirstBar() {
		if (quantity == 2) {
			drawArrow(newCoordX(1), newCoordY(1) - 8, newCoordX(2),
					newCoordY(2) - 8);
			drawArrow(newCoordX(2), newCoordY(2) + 8, newCoordX(1),
					newCoordY(1) + 8);
			return;
		}
		if (quantity > maxQuantity) {
			graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
			graphics.drawString("    Визуальный режим доступен ", 100, 150);
			graphics.drawString("только при объеме входных", 100, 190);
			graphics.drawString("данных больше 40. Вы можете", 100, 230);
			graphics.drawString("продолжить работу в текстовом режиме", 100,
					270);
			return;
		}
		if (quantity == 1) {
			return;
		}
		createLines();
	}

	public void paintLeader(int ballIdx) {
		paintLeader(ballIdx, Color.red, "LEADER!!!");
	}
	
	public void paintLeader(int ballIdx, Color color, String msg) {
		graphics.setColor(color);
		int oldArrSize = arrSize;
		arrSize = 12;
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		graphics.drawString(msg, xLeaderPos - 30, yLeaderPos);
		int xPos;
		int yPos;
		if (quantity == 1) {
			xPos = getCoord().getRoundCenterX();
			yPos = getCoord().getRoundCenterY();
		} else {
			xPos = newCoordX(ballIdx);
			yPos = newCoordY(ballIdx);
		}
		drawArrow(xLeaderPos, yLeaderPos, xPos, yPos);
		arrSize = oldArrSize;
	}

	public void drawBalls(int ballIdx, String ballTitle) {
		int xPos;
		int yPos;
		if (quantity == 1) {
			xPos = coord.getRoundCenterX();
			yPos = coord.getRoundCenterY();
		} else {
			xPos = newCoordX(ballIdx);
			yPos = newCoordY(ballIdx);
		}
		graphics.fillOval(xPos - (diametr / 2), yPos - (diametr / 2), diametr,
				diametr);
		graphics.setColor(Color.black);
		graphics.setFont(new Font("veranda", Font.PLAIN, ballTitleSize));
		graphics.drawString(ballTitle, xPos - 5, yPos + 5);
	}

	public void drawMsgs(int ballIdx, String msg) {
		int x1 = newCoordX(ballIdx);
		int x2 = newCoordX(ballIdx + 1);
		int y1 = newCoordY(ballIdx);
		int y2 = newCoordY(ballIdx + 1);
		if (quantity == 2 && ballIdx == 0) {
			x1 += 30;
			x2 += 30;
			y1 += 10;
			y2 += 10;
		}
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		graphics.drawString(msg, lineCenterX, lineCenterY);
	}

	protected abstract void setRenderParameters();
	
	protected void drawArrow(int x1, int y1, int x2, int y2){
		Graphics2D gr = (Graphics2D) graphics.create();
		
		double dx = x2 - x1;
		double dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int arrowLen = (int) Math.sqrt(dx * dx + dy * dy) - diametr / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		gr.transform(at);

		// Draw horizontal arrow starting in (0, 0)
		gr.fillPolygon(new int[] { arrowLen, arrowLen - arrSize, arrowLen - arrSize, arrowLen },
				new int[] { 0, -arrSize, arrSize, 0 }, 4);
		gr.drawLine(0, 0, arrowLen, 0);
	}

	protected void createLines(){
		for (int i = 0; i < quantity; i++) {
			int xStart = newCoordX(i + 1);
			int xEnd = newCoordX(i + 2);
			int yStart = newCoordY(i + 1);
			int yEnd = newCoordY(i + 2);
			drawLines(xStart, yStart, xEnd, yEnd);
		}
	}
	
	protected abstract void drawLines(int xStart, int yStart, int xEnd, int yEnd);
	
	protected abstract void drawLeftMsgs(int x1, int x2, int y1, int y2, String leftMsg);
	
	protected abstract void drawRightMsgs(int x1, int x2, int y1, int y2, String leftMsg);
	
	public abstract void drawCurrentLeaders(int ballIdx);

}
