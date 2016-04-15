package graphics;

import java.awt.Color;
import java.awt.Graphics;

public interface IRenderer {

	public void createFirstBar();

	public void paintLeader(int ballIdx);

	public void paintLeader(int ballIdx, Color color, String msg);

	public void drawBalls(int ballIdx, String ballTitle);
	
	public void setGraphics(Graphics graphics);

}
