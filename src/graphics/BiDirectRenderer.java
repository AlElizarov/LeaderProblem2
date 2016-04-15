package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BiPanelRenderer extends AbstractPanelRenderer {

	public BiPanelRenderer(Graphics graphics, int quantity) {
		super(graphics, quantity);
	}

	public BiPanelRenderer(Graphics graphics, MyCoord coord, int quantity) {
		super(graphics, coord, quantity);
	}

	protected void drawLeftMsgs(int x1, int x2, int y1, int y2, String leftMsg) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		lineCenterX = (lineCenterX + x2) / 2;
		lineCenterY = (lineCenterY + y2) / 2;
		graphics.drawString(leftMsg, lineCenterX, lineCenterY);
	}

	protected void drawRightMsgs(int x1, int x2, int y1, int y2, String rightMsg) {
		graphics.setColor(new Color(0, 100, 0));
		graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
		int lineCenterX = (x1 + x2) / 2;
		int lineCenterY = (y1 + y2) / 2;
		lineCenterX = (x1 + lineCenterX) / 2;
		lineCenterY = (y1 + lineCenterY) / 2;
		graphics.drawString(rightMsg, lineCenterX, lineCenterY);
	}
	
	public void createRightMsgs(){
		
	}

	@Override
	protected void setRenderParameters() {
		if (quantity < 10) {
			setParametres(35, 10, 24, 20);
		} else {
			setParametres(30, 8, 20, 18);
		}
		maxQuantity = 19;
	}

	@Override
	protected void drawLines(int xStart, int yStart, int xEnd, int yEnd) {
		drawArrow(xStart, yStart, xEnd, yEnd);
		drawArrow(xEnd, yEnd, xStart, yStart);
	}
	
	public void drawCurrentLeaders(int ballIdx){
		paintLeader(ballIdx, Color.BLUE, "CURRENT LEADER");
	}

}
