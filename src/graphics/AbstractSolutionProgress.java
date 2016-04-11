package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingWorker;

public class AbstractSolutionProgress<T, V> extends SwingWorker<T, V> implements
		ActionListener {

	protected GUI gui;
	protected ISolver solver;
	protected StringBuffer textModeString = new StringBuffer();
	protected int taskStep;

	public AbstractSolutionProgress(ISolver solver, GUI gui) {
		this.solver = solver;
		this.gui = gui;
	}

	@Override
	protected T doInBackground() throws Exception {
		while (!solver.hasSolution()) {
			Thread.sleep(2000);
			solver.solve();
			publish();
		}
		return null;
	}

	@Override
	protected void process(List<V> chunks) {
		taskStep++;
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
			solver.initiateStartState();
		}
		gui.updateFrame();
		gui.setButtonsVisibility(true, true, true, false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		taskStep++;
		if (solver.hasSolution()) {
			setLeaderId();
			solver.initiateStartState();
		}

		if (firstRound()) {
			setFirstMsgsRound();
		}
		solver.solve();
		appendMsgs();
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
		taskStep = 1;
	}

	protected void appendMsgs() {
		textModeString.append("<b>Шаг " + taskStep + ":</b> "
				+ solver.printMsgs() + "<br>");
		gui.setTextOnTextModeArea(textModeString.toString());
	}

	protected void setFirstMsgsRound() {
		textModeString.append("<b>Получение сообщений:</b><br>");
		gui.setTextOnTextModeArea(textModeString.toString());
	}

}
