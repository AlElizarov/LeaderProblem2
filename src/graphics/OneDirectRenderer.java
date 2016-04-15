package graphics;

import java.awt.Color;
import java.awt.Font;

import utils.LineSegment;

public class OneDirectRenderer extends AbstractRenderer implements OneDirectRendable {

	public OneDirectRenderer(int quantity) {
		super(quantity);
	}

	public OneDirectRenderer(Center coord, int quantity) {
		super(coord, quantity);
	}

	@Override
	protected void drawLines(LineSegment segment) {
		drawArrow(segment);
	}

	@Override
	protected void setRenderParameters() {
		if (quantity < 10) {
			setParametres(35, 10, 24, 20);
		} else if (quantity < 20) {
			setParametres(30, 8, 20, 18);
		} else if (quantity < 30) {
			setParametres(25, 6, 16, 16);
		} else if (quantity < 40) {
			setParametres(20, 5, 14, 14);
		}
		maxQuantity = 39;
	}
	
	@Override
	public void drawMsgs(int ballIdx, String msg) {
		int x1 = xCoordBall(ballIdx);
		int x2 = xCoordBall(ballIdx + 1);
		int y1 = yCoordBall(ballIdx);
		int y2 = yCoordBall(ballIdx + 1);
		LineSegment segment = createSegment(x1, x2, y1, y2);
		if (quantity == 2 && ballIdx == 0) {
			segment.moveXRight(30);
			segment.moveYTop(10);
		}
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = segment.getCenterX();
		int lineCenterY = segment.getCenterY();
		graphics.drawString(msg, lineCenterX, lineCenterY);
	}

}
