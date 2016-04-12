package graphics;

import java.awt.Graphics;

public class FourtyVariableMode extends AbstractMode {

	public FourtyVariableMode(Graphics graphics, int quantity) {
		super(graphics, quantity);
	}

	@Override
	public void createFirstBar() {
		renderer.createLines(false);
	}

}
