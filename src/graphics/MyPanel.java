package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import algorithm.Agent;

public class MyPanel extends MyAbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xLeaderPos;
	private int yLeaderPos;

	public void paintComponent(Graphics graphics) {
		xLeaderPos = roundCenterX;
		yLeaderPos = roundCenterY;
		if (quantity == 2 || quantity == 1) {
			yLeaderPos -= 100;
		}
		if (quantity >= 40) {
			graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
			graphics.drawString("    Визуальный режим доступен ", 100, 150);
			graphics.drawString("только при объеме входных", 100, 190);
			graphics.drawString("данных больше 40. Вы можете", 100, 230);
			graphics.drawString("продолжить работу в текстовом режиме", 100,
					270);
			return;
		}
		if (quantity == 2) {
			ARR_SIZE = 8;
			drawArrow(graphics, newCoordX(1), newCoordY(1) - 8, newCoordX(2),
					newCoordY(2) - 8, true);
			drawArrow(graphics, newCoordX(2), newCoordY(2) + 8, newCoordX(1),
					newCoordY(1) + 8, true);
		} else {
			if (quantity != 1) {
				createLines(graphics, false);
			}
		}
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
					drawArrow(graphics, xLeaderPos, yLeaderPos, roundCenterX,
							roundCenterY, true);
				} else
					drawArrow(graphics, xLeaderPos, yLeaderPos,
							newCoordX(ballIdx + 1), newCoordY(ballIdx + 1),
							true);
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

}
