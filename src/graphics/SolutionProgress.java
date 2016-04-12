package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingWorker;

public class SolutionProgress<T, V> extends SwingWorker<T, V> implements
		ActionListener {

	private GUI gui;
	private ISolver solver;
	private StringBuffer textModeString = new StringBuffer();
	private int taskStep;

	public SolutionProgress(SolutionProgress<T, V> progress) {
		gui = progress.gui;
		solver = progress.solver;
		textModeString = progress.textModeString;
		taskStep = progress.taskStep;
	}

	public SolutionProgress(ISolver solver, GUI gui) {
		this.solver = solver;
		this.gui = gui;
	}

	@Override
	protected T doInBackground() throws Exception {
		if (solver.hasSolution()) {
			solver.initiateStartState();
		}
		while (!solver.hasSolution()) {
			Thread.sleep(2000);
			taskStep++;
			solver.solve();
			publish();
		}
		return null;
	}

	@Override
	protected void process(List<V> chunks) {
		if (firstRound()) {
			setFirstMsgsRound();
		}
		appendMsgs();
		gui.updateFrame();
	}

	@Override
	protected void done() {
		if (solver.hasSolution()) {
			setLeaderId();
		}
		gui.updateFrame();
		gui.setButtonsVisibility(true, true, true, false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		taskStep++;
		if (solver.hasSolution()) {
			solver.initiateStartState();
		}
		if (firstRound()) {
			setFirstMsgsRound();
		}
		solver.solve();
		appendMsgs();
		if (solver.hasSolution()) {
			setLeaderId();
		}
		gui.updateFrame();
	}

	protected boolean firstRound() {
		return taskStep == 1;
	}

	protected void setLeaderId() {
		int leaderId = solver.getLeaderId();
		textModeString.append("<b><font size = 6>ЛИДЕР НАЙДЕН!!! Id лидера: "
				+ leaderId + "</font></b><br>");
		gui.setTextOnTextModeArea(textModeString.toString());
		taskStep = 0;
	}

	protected void appendMsgs() {
		textModeString.append("<b>Шаг " + (taskStep) + ":</b> "
				+ solver.printMsgs() + "<br>");
		gui.setTextOnTextModeArea(textModeString.toString());
	}

	protected void setFirstMsgsRound() {
		textModeString.append("<b>Получение сообщений:</b><br>");
		gui.setTextOnTextModeArea(textModeString.toString());
	}

}
