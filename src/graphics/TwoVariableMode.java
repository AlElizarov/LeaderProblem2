package graphics;

import java.awt.Graphics;

public class TwoVariableMode extends AbstractMode {

	public TwoVariableMode(Graphics graphics, int quantity) {
		super(graphics, quantity);
	}

	@Override
	public void createFirstBar() {
		renderer.drawArrow(graphics, renderer.newCoordX(1),
				renderer.newCoordY(1) - 8, renderer.newCoordX(2),
				renderer.newCoordY(2) - 8, true);
		renderer.drawArrow(graphics, renderer.newCoordX(2),
				renderer.newCoordY(2) + 8, renderer.newCoordX(1),
				renderer.newCoordY(1) + 8, true);
		renderer.createLines(false);
	}

}
