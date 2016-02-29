package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import algorithm.Agent;
import algorithm.LeaderElection;
import algorithm.MyRingList;

public class GUI {

	private int quantity = 3;
	private MyPanel PanelWithPicture;
	private MyRingList list;
	private int taskStep;
	private JSplitPane rightSplit;
	private JFrame frame;
	private SwingWorker<Void, Void> sw;

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
				new GUI();
			}
		});
	}

	public GUI() {
		frame = new JFrame();
		
		setData();
		createPanelWithPicture();

		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
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

	private Box createButtonPanel() {
		Box verticalBoxForTaskMainInfo = Box.createVerticalBox();
		JButton setup = new JButton("setup");
		JButton stepButtop = new JButton(" step ");
		JButton go = new JButton("  go  ");
		JButton stop = new JButton(" stop ");
		setup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sw != null) {
					sw.cancel(true);
				}
				setData();
				System.out.println(list);
				updateFrame();
				stepButtop.setEnabled(true);
				go.setEnabled(true);
				rightSplit.setVisible(true);
				frame.revalidate();
			}

		});
		stepButtop.setEnabled(false);
		stepButtop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LeaderElection.solve(list, taskStep++);
				if (taskStep == quantity) {
					taskStep = 0;
				}
				updateFrame();
			}
		});
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop.setEnabled(true);
				setup.setEnabled(false);
				stepButtop.setEnabled(false);
				go.setEnabled(false);
				sw = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						while (taskStep != quantity) {
							LeaderElection.solve(list, taskStep++);
							Thread.sleep(1000);
							publish();
							Thread.sleep(1000);
						}
						taskStep= 0;
						return null;
					}
					
					@Override
					protected void process(List<Void> chunks) {
						updateFrame();
					}
					
					@Override
					protected void done() {
						updateFrame();
						stop.setEnabled(false);
						setup.setEnabled(true);
						stepButtop.setEnabled(true);
						go.setEnabled(true);
					}

				};
				sw.execute();
			}
		});
		go.setEnabled(false);
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sw.cancel(true);
				stepButtop.setEnabled(true);
				go.setEnabled(true);
			}
		});
		stop.setEnabled(false);
		Box buttonBoxForSetupAndStep = Box.createHorizontalBox();
		buttonBoxForSetupAndStep.add(setup);
		buttonBoxForSetupAndStep.add(stepButtop);
		Box buttonBoxForGoAndStop = Box.createHorizontalBox();
		buttonBoxForGoAndStop.add(go);
		buttonBoxForGoAndStop.add(stop);
		verticalBoxForTaskMainInfo.add(Box.createVerticalStrut(50));
		verticalBoxForTaskMainInfo.add(buttonBoxForSetupAndStep);
		verticalBoxForTaskMainInfo.add(buttonBoxForGoAndStop);
		verticalBoxForTaskMainInfo.setBorder(new EmptyBorder(0, 20, 0, 20));
		return verticalBoxForTaskMainInfo;
	}

	private void createPanelWithPicture() {
		PanelWithPicture = new MyPanel(quantity);
		updateFrame();
	}
	
	private void updateFrame(){
		PanelWithPicture.setList(list);
		PanelWithPicture.setStep(taskStep);
		frame.repaint();
	}

}
