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
		graphics.drawString("    ���������� ����� �������� ", 100, 150);
		graphics.drawString("������ ��� ������ �������", 100, 190);
		graphics.drawString("������ ������ 40. �� ������", 100, 230);
		graphics.drawString("���������� ������ � ��������� ������", 100,
				270);
	}

}
