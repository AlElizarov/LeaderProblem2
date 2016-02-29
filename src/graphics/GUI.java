package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import algorithm.Agent;
import algorithm.LeaderElection;
import algorithm.MyRingList;

public class GUI {

	private int quantity = 39;
	private MyPanel PanelWithPicture;
	private MyRingList list;
	private int taskStep;
	private JSplitPane rightSplit;
	private JFrame frame;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI();
			}
		});
	}

	public GUI() {
		setData();
		createPanelWithPicture();

		frame = new JFrame();
		frame.setSize(1000, 700);
		frame.getContentPane().add(createMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
		rightSplit.setTopComponent(PanelWithPicture);
		rightSplit.setDividerLocation(0.9);
		rightSplit.setResizeWeight(0.95);
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		rightSplit.setBottomComponent(scroll);
		rightSplit.setDividerLocation(0.9);
		rightSplit.setVisible(false);
		return rightSplit;
	}

	private void setData() {
		ArrayList<Integer> items = new ArrayList<>();
		list = new MyRingList();
		for (int i = 0; i < quantity; i++) {
			items.add(i + 1);
		}

		while (!items.isEmpty()) {
			Random r = new Random();
			int index = r.nextInt(items.size());
			list.add(new Agent(items.get(index)));
			items.remove(index);
		}
		taskStep = 0;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton setup = new JButton("setup");
		JButton stepButtop = new JButton("step");
		setup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stepButtop.setEnabled(true);
				rightSplit.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}

		});
		stepButtop.setEnabled(false);
		stepButtop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LeaderElection.solve(list, taskStep++);
				PanelWithPicture.setList(list);
				PanelWithPicture.setStep(taskStep);
				frame.repaint();
				if (taskStep >= quantity) {
					stepButtop.setEnabled(false);
					taskStep = 0;
				}
			}
		});
		buttonPanel.add(setup);
		buttonPanel.add(stepButtop);
		return buttonPanel;
	}

	private void createPanelWithPicture() {
		PanelWithPicture = new MyPanel();
		PanelWithPicture.setList(list);
		PanelWithPicture.setStep(taskStep);
	}

}
