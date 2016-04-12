package graphics;

import java.awt.Graphics;

public class OnePanelRenderer extends AbstractPanelRenderer {

	public OnePanelRenderer(Graphics graphics, int quantity) {
		super(graphics, quantity);
	}

	public OnePanelRenderer(Graphics graphics, MyCoord coord, int quantity) {
		super(graphics, coord, quantity);
	}

	protected void drawLines(int xStart, int yStart, int xEnd, int yEnd) {
		drawArrow(xStart, yStart, xEnd, yEnd);
	}

	protected int createArrowLen() {
		return arrowLen;
	}

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
	protected void drawLeftMsgs(int x1, int x2, int y1, int y2, String leftMsg) {
	}

	@Override
	protected void drawRightMsgs(int x1, int x2, int y1, int y2, String leftMsg) {
	}

}
