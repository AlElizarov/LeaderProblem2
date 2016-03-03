package graphics;

import java.awt.Font;
import java.awt.Graphics;

import algorithm.Agent;

public class MyBiDirectPanel extends MyAbstractPanel {

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
		if (quantity >= 20) {
			graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
			graphics.drawString("    Визуальный режим доступен ", 100, 150);
			graphics.drawString("только при объеме входных", 100, 190);
			graphics.drawString("данных больше 20 в двунаправленном", 100, 230);
			graphics.drawString("режиме. Вы можете продолжить", 100, 270);
			graphics.drawString("работу в текстовом режиме", 100, 310);
			return;
		}
		if (quantity == 2) {
			ARR_SIZE = 8;
			drawArrow(graphics, newCoordX(1), newCoordY(1), newCoordX(2),
					newCoordY(2), true);
			drawArrow(graphics, newCoordX(2), newCoordY(2), newCoordX(1),
					newCoordY(1), false);
		} else {
			if (quantity != 1) {
				createLines(graphics, false);
			}
		}
		int ballIdx = 0;
		while (list.hasNext()) {
			Agent currentBall = list.next();
			drawBalls(graphics, newCoordX(ballIdx + 1), newCoordY(ballIdx + 1),
					currentBall);
			ballIdx++;
		}
	}

}
