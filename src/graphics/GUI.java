package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

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
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import algorithm.Agent;
import algorithm.BiDirectLeaderElection;
import algorithm.OneDirectLeaderElection;
import algorithm.MyAbstractList;
import algorithm.BiRingList;
import algorithm.RingList;

public class GUI {

	private int quantity;
	private boolean isBidirect;
	private MyAbstractPanel panelWithPicture;
	private MyAbstractList list;
	private int taskStep;
	private JSplitPane rightSplit;
	private JFrame frame;
	private SwingWorker<Void, Void> sw;
	private JButton setup;
	private JButton stepButtop;
	private JButton go;
	private JButton stop;
	private JTextField quantityField;
	private JComboBox<Object> comboBoxForMode;
	private JTextArea textAreaForList;
	private JRadioButton hand;
	private JTextPane textAreaForTextMode;
	private StringBuilder textModeString;
	private int stage;
	private int step;

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

		// refact
		setData();
		ImageIcon image = new ImageIcon("images/leaderIcon.png");
		frame.setIconImage(image.getImage());

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

		frame.setMinimumSize(new Dimension(1000, 700));
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(createMainPanel());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		quantityField.requestFocus();
	}

	private Component createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(createButtonPanel(), BorderLayout.WEST);
		mainPanel.add(createRightSplit(), BorderLayout.CENTER);
		return mainPanel;
	}

	private Component createRightSplit() {
		rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		rightSplit.setDividerLocation(0.9);
		rightSplit.setResizeWeight(0.95);
		textAreaForTextMode = new JTextPane();
		JPanel temp = new JPanel(new BorderLayout());
		temp.add(textAreaForTextMode, BorderLayout.CENTER);
		textAreaForTextMode.setFont(textAreaForTextMode.getFont().deriveFont(
				16f));
		JScrollPane scroll = new JScrollPane(temp);
		rightSplit.setBottomComponent(scroll);
		rightSplit.setVisible(false);
		return rightSplit;
	}

	private void setData() {
		ArrayList<Integer> items = new ArrayList<>();
		if (!isBidirect) {
			list = new RingList();
		} else {
			list = new BiRingList();
		}
		for (int i = 0; i < quantity; i++) {
			items.add(i + 1);
		}

		while (!items.isEmpty()) {
			Random r = new Random();
			int index = r.nextInt(items.size());
			list.add(new Agent(items.get(index)));
			items.remove(index);
		}
	}

	private JPanel createButtonPanel() {
		JPanel infoPanel = new JPanel(new MigLayout());
		createSetupButton();
		createStepButton();
		createGoButton();
		createStopButton();
		setButtonsVisibility(true, false, false, false);

		JPanel panelForButtons = new JPanel(new MigLayout());
		panelForButtons.add(setup, "sg 1");
		panelForButtons.add(stepButtop, "sg 1, wrap");
		panelForButtons.setBorder(new EmptyBorder(0, 30, 0, 30));
		panelForButtons.add(go, "sg 1");
		panelForButtons.add(stop, "sg 1");

		infoPanel.add(panelForButtons, "wrap");

		JPanel panelForQuantityAndMode = new JPanel(new MigLayout());
		panelForQuantityAndMode.add(new JLabel("<html>размер<br>задачи: "),
				"gap, sg 2");
		quantityField = new JTextField(4);
		panelForQuantityAndMode.add(quantityField, "wrap");
		panelForQuantityAndMode.add(new JLabel("Режим: "), "gap, sg 2");
		comboBoxForMode = new JComboBox<>();
		comboBoxForMode.addItem("Однонаправленный");
		comboBoxForMode.addItem("Двунаправленный");
		panelForQuantityAndMode.add(comboBoxForMode, "wrap");
		panelForQuantityAndMode.setBorder(new EmptyBorder(0, 50, 0, 50));

		infoPanel.add(panelForQuantityAndMode, "wrap");

		JPanel panelForListGenereate = new JPanel(new MigLayout());

		TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK, 1));
		tb.setTitle("Расположение узлов");
		tb.setTitleJustification(TitledBorder.CENTER);
		panelForListGenereate.setBorder(new TitledBorder(tb));

		JRadioButton random = new JRadioButton(
				"<html>Случайно<br>сгенерировать");
		random.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaForList.setEnabled(false);
			}
		});
		hand = new JRadioButton("<html>Задать<br>вручную");
		hand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaForList.setEnabled(true);
				textAreaForList.requestFocus();
			}
		});
		JPanel panelForRandomAndHandle = new JPanel(new MigLayout());
		panelForRandomAndHandle.add(random);
		panelForRandomAndHandle.add(hand, "wrap");

		panelForListGenereate.add(panelForRandomAndHandle, "wrap");

		ButtonGroup gr = new ButtonGroup();
		gr.add(random);
		gr.add(hand);
		random.setSelected(true);

		textAreaForList = new JTextArea(1, 30);
		textAreaForList.setFont(textAreaForList.getFont().deriveFont(12f));
		textAreaForList.setEnabled(false);
		JScrollPane scrollForList = new JScrollPane(textAreaForList);
		scrollForList
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollForList
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelForListGenereate.add(scrollForList);

		infoPanel.add(panelForListGenereate, "wrap");

		return infoPanel;
	}

	private void createStopButton() {
		stop = new JButton(" stop ");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sw.cancel(true);
				setButtonsVisibility(true, true, true, false);
			}
		});
	}

	private void createGoButton() {
		go = new JButton("  go  ");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonsVisibility(false, false, false, true);
				if (!isBidirect) {
					sw = new SwingWorkerForButtonGo<Void, Void>();
				} else {
					sw = new SwingWorkerForButtonGoBiDirect<Void, Void>();
				}
				sw.execute();
			}
		});
	}

	private void createStepButton() {
		stepButtop = new JButton(" step ");

	}

	private void createSetupButton() {
		setup = new JButton("setup");
		setup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					quantity = Integer.valueOf(quantityField.getText());
					if (quantity <= 0) {
						JOptionPane
								.showMessageDialog(null,
										"Размер задачи должен быть положительным числом");
						quantityField.requestFocus();
						splitReset();
						return;
					}
				} catch (Exception exc) {
					if (exc.getMessage().equals("For input string: \"\"")) {
						JOptionPane.showMessageDialog(null,
								"Пожалуйста введите размер задачи");
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Некорректный ввод: Пожалуйста введите целое число");
						quantityField.setText("");
					}
					quantityField.requestFocus();
					splitReset();
					return;
				}
				if (comboBoxForMode.getSelectedItem()
						.equals("Однонаправленный")) {
					isBidirect = false;
				} else {
					isBidirect = true;
				}
				if (hand.isSelected()) {

					if (!isBidirect) {
						list = new RingList();
					} else {
						list = new BiRingList();
					}
					String[] items = textAreaForList.getText().split(",");
					LinkedHashSet<Integer> intItems = new LinkedHashSet<>();
					for (int i = 0; i < items.length; i++) {
						try {
							int element = Integer.valueOf(items[i].trim());
							if (!intItems.add(element)) {
								JOptionPane
										.showMessageDialog(
												null,
												"Некорректный значение: "
														+ "Не должно быть повторяющихся элементов");
								textAreaForList.requestFocus();
								splitReset();
								return;
							}
							if (element <= 0) {
								JOptionPane
										.showMessageDialog(
												null,
												"Некорректное значение: "
														+ "Элементы должны быть больше нуля");
								textAreaForList.requestFocus();
								splitReset();
								return;
							}
						} catch (Exception exc) {
							JOptionPane
									.showMessageDialog(
											null,
											"Некорректный формат ввода: "
													+ "Пожалуйста вводите только целые числа через запятую");
							textAreaForList.requestFocus();
							textAreaForList.setText("");
							splitReset();
							return;
						}
					}
					if (intItems.size() != quantity) {
						JOptionPane.showMessageDialog(null,
								"Некорректный формат ввода: "
										+ "Неверное количесвто элементов");
						textAreaForList.requestFocus();
						splitReset();
						return;
					}
					Iterator<Integer> iter = intItems.iterator();
					while (iter.hasNext()) {
						list.add(new Agent(iter.next()));
					}
				} else {
					// refact
					setData();
				}

				if (!isBidirect) {
					panelWithPicture = new OneDirectPanel();
				} else {
					panelWithPicture = new BiDirectPanel();
				}
				ActionListener[] listeners = stepButtop
						.getListeners(ActionListener.class);
				for (int i = 0; i < listeners.length; i++) {
					stepButtop.removeActionListener(listeners[i]);
				}
				stepButtop.removeAll();
				if (!isBidirect) {
					stepButtop.addActionListener(new StepListenerOneDirect());
				} else {
					stepButtop.addActionListener(new StepListenerBiDirect());
				}
				taskStep = 0;
				stage = 0;
				step = 0;
				rightSplit.setTopComponent(panelWithPicture);
				panelWithPicture.setQuantity(quantity);
				textAreaForTextMode.setContentType("text/html");
				textModeString = new StringBuilder(
						"<b>Располажение узлов:</b> " + list + "<br>");
				textAreaForTextMode.setText(textModeString.toString());
				updateFrame();
				setButtonsVisibility(true, true, true, false);
				rightSplit.setVisible(true);
				if ((!isBidirect && quantity >= 40)
						|| (isBidirect && quantity >= 20)) {
					rightSplit.setDividerLocation(0.5);
					rightSplit.setResizeWeight(0.5);
				}
				frame.revalidate();
			}

		});
	}

	private void updateFrame() {
		panelWithPicture.setList(list);
		panelWithPicture.setStep(taskStep);
		panelWithPicture.setStage(stage);
		panelWithPicture.setBiDirectStep(step);
		frame.repaint();
	}

	private void setButtonsVisibility(boolean setupVisibility,
			boolean stepVisibility, boolean goVisibility, boolean stopVisibility) {
		setup.setEnabled(setupVisibility);
		stepButtop.setEnabled(stepVisibility);
		go.setEnabled(goVisibility);
		stop.setEnabled(stopVisibility);
	}

	private void splitReset() {
		rightSplit.setVisible(false);
		setButtonsVisibility(true, false, false, false);
	}

	private class SwingWorkerForButtonGo<T, V> extends SwingWorker<T, V> {

		@Override
		protected T doInBackground() throws Exception {
			while (taskStep != quantity) {
				Thread.sleep(2000);
				OneDirectLeaderElection.solve(list, taskStep++);
				publish();
			}
			taskStep = 0;
			return null;
		}

		@Override
		protected void process(List<V> chunks) {
			if (taskStep == 1) {
				textModeString.append("<b>Получение сообщений:</b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}
			textModeString.append("<b>Шаг " + taskStep + ":</b> "
					+ list.printMsgs() + "<br>");
			textAreaForTextMode.setText(textModeString.toString());
			updateFrame();
		}

		@Override
		protected void done() {
			updateFrame();
			setButtonsVisibility(true, true, true, false);
			stop.setEnabled(false);
			setup.setEnabled(true);
			stepButtop.setEnabled(true);
			go.setEnabled(true);
			int leaderId = list.getLeaderId();

			if (taskStep == 0) {
				textModeString
						.append("<b><font size = 6>ЛИДЕР НАЙДЕН!!! Id лидера: "
								+ leaderId + "</font></b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}
			updateFrame();
		}

	}

	private class StepListenerOneDirect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (taskStep == 0) {
				textModeString.append("<b>Получение сообщений:</b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}
			OneDirectLeaderElection.solve(list, taskStep++);
			textModeString.append("<b>Шаг " + taskStep + ":</b> "
					+ list.printMsgs() + "<br>");
			textAreaForTextMode.setText(textModeString.toString());
			if (taskStep == quantity) {
				taskStep = 0;
				textModeString
						.append("<b><font size = 6>ЛИДЕР НАЙДЕН!!! Id лидера: "
								+ list.getLeaderId() + "</font></b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}
			updateFrame();
		}

	}

	private class StepListenerBiDirect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (stage == 0 && step == 0) {
				textModeString.append("<b>Получение сообщений:</b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}

			BiDirectLeaderElection.choice(list, stage, step);

			textModeString.append("<b>Стадия: " + stage + " Шаг " + (step + 1)
					+ ":</b> " + list.printMsgs() + "<br>");
			textAreaForTextMode.setText(textModeString.toString());

			if (Math.pow(2, stage) == step) {
				stage++;
				step = 0;
			} else {
				step++;
			}

			int loopEnd = (int) (Math.log(list.size()) / Math.log(2));
			if ((list.size() & (list.size() - 1)) != 0 || quantity == 1) {
				loopEnd += 1;

			}
			if (stage == loopEnd && step == list.size()) {

				textModeString
						.append("<b><font size = 6>ЛИДЕР НАЙДЕН!!! Id лидера: "
								+ list.getLeaderId() + "</font></b><br>");
				textAreaForTextMode.setText(textModeString.toString());

				stage = 0;
				step = 0;

			}
			if (quantity == 1 && stage == 1 && step == 0) {
				stage = 0;
				step = 1;
			}
			updateFrame();

		}

	}

	private class SwingWorkerForButtonGoBiDirect<T, V> extends
			SwingWorker<T, V> {

		@Override
		protected T doInBackground() throws Exception {
			int loopEnd = (int) (Math.log(list.size()) / Math.log(2));
			if ((list.size() & (list.size() - 1)) != 0 || quantity == 1) {
				loopEnd += 1;
			}
			while (stage <= loopEnd) {
				while (step <= Math.pow(2, stage)) {
					Thread.sleep(2000);
					BiDirectLeaderElection.choice(list, stage, step++);
					if (stage == loopEnd && step == list.size()) {
						textModeString
								.append("<b><font size = 6>ЛИДЕР НАЙДЕН!!! Id лидера: "
										+ list.getLeaderId()
										+ "</font></b><br>");
						textAreaForTextMode.setText(textModeString.toString());
						stage = 0;
						step = 0;
						return null;
					}
					
					if (quantity == 1 && stage == 1 && step == 0) {
						stage = 0;
						step = 1;
					}
					publish();
				}
				stage++;
				step = 0;
			}
			stage = 0;
			step = 0;
			return null;
		}

		@Override
		protected void process(List<V> chunks) {
			if (stage == 0 && step == 1) {
				textModeString.append("<b>Получение сообщений:</b><br>");
				textAreaForTextMode.setText(textModeString.toString());
			}
			textModeString.append("<b>Стадия: " + stage + " Шаг " + (step + 1)
					+ ":</b> " + list.printMsgs() + "<br>");
			textAreaForTextMode.setText(textModeString.toString());
			updateFrame();
		}

		@Override
		protected void done() {
			updateFrame();
			setButtonsVisibility(true, true, true, false);

		}

	}

}
