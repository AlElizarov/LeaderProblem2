package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import algorithm.BiDirectSolver;
import algorithm.OneDirectSolver;
import data.DuplicateValueException;
import data.FormatException;
import data.NonPositiveValueException;

public class GUI {

	private JFrame frame;

	private JSplitPane rightSplit;
	private AbstractPanel panelWithPicture;
	private SolutionProgress<Void, Void> progress;

	private JButton setup;
	private JButton stepButtop;
	private JButton go;
	private JButton stop;

	private JTextField quantityField;
	private JComboBox<Object> comboBoxForMode;
	private JTextArea textAreaForList;
	private JRadioButton hand;
	private JTextPane textAreaForTextMode;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				UIManager
						.put("OptionPane.messageDialogTitle", "Предупреждение");
				new GUI();
			}
		});
	}

	public GUI() {
		frame = new JFrame("LEADER ELECTION");
		setFrameIcon();
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Object[] options = { "Да", "Нет!" };
				int n = JOptionPane.showOptionDialog(e.getWindow(),
						"Закрыть окно?", "Подтверждение",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (n == 0) {
					e.getWindow().setVisible(false);
					System.exit(0);
				}
			}
		});
		frame.getContentPane().add(createMainPanel());
		setFrameParameters();
		quantityField.requestFocus();
	}

	public void updateFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.revalidate();
				frame.repaint();
				panelWithPicture.repaint();
				panelWithPicture.revalidate();
			}
		});
	}

	public void setButtonsVisibility(boolean setupVisibility,
			boolean stepVisibility, boolean goVisibility, boolean stopVisibility) {
		setup.setEnabled(setupVisibility);
		stepButtop.setEnabled(stepVisibility);
		go.setEnabled(goVisibility);
		stop.setEnabled(stopVisibility);
	}

	public void setTextOnTextModeArea(String text) {
		textAreaForTextMode.setText(textAreaForList.getText() + text);
	}

	private void setFrameParameters() {
		frame.setMinimumSize(new Dimension(1000, 700));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

	private void setFrameIcon() {
		ImageIcon image = new ImageIcon("images/leaderIcon.png");
		frame.setIconImage(image.getImage());
	}

	private Component createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(createInfoPanel(), BorderLayout.WEST);
		mainPanel.add(createRightSplit(), BorderLayout.CENTER);
		return mainPanel;
	}

	private Component createRightSplit() {
		rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		setSplitParameters();
		rightSplit.setBottomComponent(createTextModeArea());
		return rightSplit;
	}

	private Component createTextModeArea() {
		textAreaForTextMode = new JTextPane();
		JPanel panelForTextMode = new JPanel(new BorderLayout());
		panelForTextMode.add(textAreaForTextMode, BorderLayout.CENTER);
		textAreaForTextMode.setFont(textAreaForTextMode.getFont().deriveFont(
				16f));
		JScrollPane scroll = new JScrollPane(panelForTextMode);
		return scroll;
	}

	private void setSplitParameters() {
		rightSplit.setVisible(false);
	}

	private Component createInfoPanel() {
		JPanel infoPanel = new JPanel(new MigLayout());
		createButtons();
		infoPanel.add(createButtonsPane(), "wrap");
		infoPanel.add(panelForQuantityAndMode(), "wrap");
		infoPanel.add(createPanelForListGenerate(), "wrap");
		return infoPanel;
	}

	private Component createPanelForListGenerate() {
		JPanel panelForListGenereate = new JPanel(new MigLayout());
		TitledBorder tb = createBorderForList();
		panelForListGenereate.setBorder(new TitledBorder(tb));
		panelForListGenereate.add(createRadioPanel(), "wrap");
		panelForListGenereate.add(createTextAreaForList());
		return panelForListGenereate;
	}

	private Component createTextAreaForList() {
		textAreaForList = new JTextArea(1, 30);
		textAreaForList.setFont(textAreaForList.getFont().deriveFont(12f));
		textAreaForList.setEnabled(false);
		JScrollPane scrollForList = new JScrollPane(textAreaForList);
		scrollForList
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollForList
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scrollForList;
	}

	private Component createRadioPanel() {
		JPanel panelForRandomAndHandle = new JPanel(new MigLayout());
		JRadioButton random = createRandomRadio();
		panelForRandomAndHandle.add(random);
		createHandRadio();
		panelForRandomAndHandle.add(hand, "wrap");
		ButtonGroup gr = new ButtonGroup();
		gr.add(random);
		gr.add(hand);
		random.setSelected(true);
		return panelForRandomAndHandle;
	}

	private void createHandRadio() {
		hand = new JRadioButton("<html>Задать<br>вручную");
		hand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaForList.setEnabled(true);
				textAreaForList.requestFocus();
			}
		});
	}

	private JRadioButton createRandomRadio() {
		JRadioButton random = new JRadioButton(
				"<html>Случайно<br>сгенерировать");
		random.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaForList.setEnabled(false);
			}
		});
		return random;
	}

	private TitledBorder createBorderForList() {
		Color color = Color.black;
		int width = 1;
		LineBorder lb = new LineBorder(color, width);
		String title = "Расположение узлов";
		int pos = TitledBorder.CENTER;
		return createTitledBorder(lb, title, pos);
	}

	private TitledBorder createTitledBorder(LineBorder lineBorder,
			String string, int center) {
		TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK, 1));
		tb.setTitle("Расположение узлов");
		tb.setTitleJustification(TitledBorder.CENTER);
		return tb;
	}

	private Component panelForQuantityAndMode() {
		JPanel panelForQuantityAndMode = new JPanel(new MigLayout());
		panelForQuantityAndMode.add(new JLabel("<html>размер<br>задачи: "),
				"gap, sg 2");
		createQuantityField();
		panelForQuantityAndMode.add(quantityField, "wrap");
		panelForQuantityAndMode.add(new JLabel("Режим: "), "gap, sg 2");
		createComboBoxForMode();
		panelForQuantityAndMode.add(comboBoxForMode, "wrap");
		panelForQuantityAndMode.setBorder(new EmptyBorder(0, 50, 0, 50));
		return panelForQuantityAndMode;
	}

	private void createComboBoxForMode() {
		comboBoxForMode = new JComboBox<>();
		comboBoxForMode.addItem("Однонаправленный");
		comboBoxForMode.addItem("Двунаправленный");
	}

	private void createQuantityField() {
		quantityField = new JTextField(4);
	}

	private void createButtons() {
		createSetupButton();
		stepButtop = new JButton("step");
		go = new JButton("go");
		stop = new JButton("stop");
		setButtonsVisibility(true, false, false, false);
	}

	private Component createButtonsPane() {
		JPanel panelForButtons = new JPanel(new MigLayout());
		panelForButtons.add(setup, "sg 1");
		panelForButtons.add(stepButtop, "sg 1, wrap");
		panelForButtons.setBorder(new EmptyBorder(0, 30, 0, 30));
		panelForButtons.add(go, "sg 1");
		panelForButtons.add(stop, "sg 1");
		return panelForButtons;
	}

	private void addStopListener() {
		removeListeners(stop);
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				progress.cancel(true);
				setButtonsVisibility(true, true, true, false);
			}
		});
	}

	private void addGoListener() {
		removeListeners(go);
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				progress = new SolutionProgress<>(progress);
				setButtonsVisibility(false, false, false, true);
				progress.execute();
			}
		});
	}

	private void addStepListener() {
		removeListeners(stepButtop);
		stepButtop.addActionListener(progress);
	}

	private void removeListeners(JButton button) {
		for (ActionListener listener : button.getActionListeners()) {
			button.removeActionListener(listener);
		}
	}

	private void createSetupButton() {
		setup = new JButton("setup");
		setup.addActionListener(new SetupButtonListener());
	}

	private void splitReset() {
		rightSplit.setVisible(false);
		setButtonsVisibility(true, false, false, false);
	}

	private class SetupButtonListener implements ActionListener {

		private int quantity;

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				quantity = Integer.valueOf(quantityField.getText());
				if (quantity <= 0) {
					showErrorMsg("Размер задачи должен быть положительным числом");
					quantityField.setText("");
					quantityField.requestFocus();
					return;
				}
			} catch (Exception exc) {
				handleErrors(exc);
				return;
			}
			ISolver solver = setMode();

			if (hand.isSelected()) {
				if (!fillSolverHandlessOk(solver)) {
					return;
				}
			} else {
				solver.setRandomData(quantity);
			}

			addListeners();
			rightSplit.setTopComponent(panelWithPicture);
			establishTextmodeArea(solver);
			updateFrame();
			setButtonsVisibility(true, true, true, false);
			rightSplit.setVisible(true);
		}

		private void establishTextmodeArea(ISolver solver) {
			textAreaForTextMode.setContentType("text/html");
			textAreaForTextMode.setText("<b>Располажение узлов:</b> " + solver
					+ "<br>");
			System.out.println(solver);
		}

		private void addListeners() {
			addStepListener();
			addStopListener();
			addGoListener();
		}

		private boolean fillSolverHandlessOk(ISolver solver) {
			String[] items = textAreaForList.getText().split(",");
			try {
				solver.addAll(items);
			} catch (NonPositiveValueException exc) {
				showErrorMsg("Некорректное значение: "
						+ exc.getMessage()+". Элементы должны быть больше нуля");
				textAreaForList.requestFocus();
				return false;
			} catch (DuplicateValueException exc) {
				showErrorMsg("Некорректный значение: "
						+ exc.getMessage()+". Не должно быть повторяющихся элементов");
				textAreaForList.requestFocus();
				return false;
			} catch (FormatException exc) {
				showErrorMsg("Некорректный формат ввода: "
						+ exc.getMessage()+ ". Пожалуйста, вводите только целые числа ");
				textAreaForList.requestFocus();
				return false;
			}
			if (items.length != quantity) {
				showErrorMsg("Некорректный формат ввода: "
						+ "Неверное количесвто элементов");
				textAreaForList.requestFocus();
				return false;
			}
			return true;
		}

		private void handleErrors(Exception exc) {
			if (exc.getMessage().equals("For input string: \"\"")) {
				showErrorMsg("Пожалуйста введите размер задачи");
				quantityField.setText("");
				quantityField.requestFocus();
			} else {
				showErrorMsg("Некорректный ввод: Пожалуйста введите целое число");
				quantityField.setText("");
				quantityField.requestFocus();
			}
		}

		private ISolver setMode() {
			ISolver solver;
			if (comboBoxForMode.getSelectedItem().equals("Однонаправленный")) {
				solver = createOneDirectMode();
			} else {
				solver = creteBiDirectMode();
			}
			progress = new SolutionProgress<>(solver, GUI.this);
			return solver;
		}

		private ISolver creteBiDirectMode() {
			if(quantity > 19){
				rightSplit.setDividerLocation(0.5);
				rightSplit.setResizeWeight(0.5);
			}
			else{
				rightSplit.setDividerLocation(0.9);
				rightSplit.setResizeWeight(0.95);
			}
			BiDirectSolvable biDirectSolver = new BiDirectSolver();
			BiDirectRendable renderer = new BiDirectRenderer(quantity);
			panelWithPicture = new BiDirectPanel(biDirectSolver, quantity,
					renderer);
			return biDirectSolver;
		}

		private ISolver createOneDirectMode() {
			if(quantity > 39){
				rightSplit.setDividerLocation(0.5);
				rightSplit.setResizeWeight(0.5);
			}
			else{
				rightSplit.setDividerLocation(0.9);
				rightSplit.setResizeWeight(0.95);
			}
			OneDirectSolver oneDirectSolver = new OneDirectSolver();
			OneDirectRendable renderer = new OneDirectRenderer(quantity);
			panelWithPicture = new OneDirectPanel(oneDirectSolver, quantity,
					renderer);
			return oneDirectSolver;
		}

		private void showErrorMsg(String msg) {
			JOptionPane.showMessageDialog(null, msg);
			splitReset();
		}
	}

}
