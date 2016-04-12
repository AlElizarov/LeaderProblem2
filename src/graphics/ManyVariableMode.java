package graphics;

import java.awt.Font;
import java.awt.Graphics;

public class ManyVariableMode extends AbstractMode{

	public ManyVariableMode(Graphics graphics, int quantity) {
		super(graphics, quantity);
	}

	@Override
	public void createFirstBar() {
		graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
		graphics.drawString("    Визуальный режим доступен ", 100, 150);
		graphics.drawString("только при объеме входных", 100, 190);
		graphics.drawString("данных больше 40. Вы можете", 100, 230);
		graphics.drawString("продолжить работу в текстовом режиме", 100,
				270);
	}

}
