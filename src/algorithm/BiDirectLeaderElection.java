package algorithm;


public class BiDirectLeaderElection {

	public static int solve(MyAbstractList list) {
		return 0;
	}

	public static void choice(MyAbstractList list, int stage, int step) {
		if (stage == 0) {
			list.initiateCurrentLeaders();
		}
		if (step == 0) {
			list.initiateLeftSenders();
			list.initiateRightSenders();
		}
		if (step < Math.pow(2, stage)) {
			send(list, stage, step);
		} else {
			request(list, stage, step);
		}
	}

	private static void request(MyAbstractList list, int stage, int step) {

	}

	private static void sendLeft(MyAbstractList list, int stage, int step) {
		list.setCorrectLeftSendersIndexes();
		for (int i = 0; i < list.getLeftSenders().size(); i++) {
			list.get(list.getLeftSenders().get(i) + 1).setNewLeftMsg(
					list.get(list.getLeftSenders().get(i)).getLeftMsg());
		}
		list.setLeftMsgs();
		if ((int) Math.pow(2, stage) - step == 1) {
			list.setRightRequesters();
		} else {
			list.setLeftSenders();
		}
	}

	private static void sendRight(MyAbstractList list, int stage, int step) {
		list.setCorrectRightSendersIndexes();
		for (int i = 0; i < list.getRightSenders().size(); i++) {
			list.get(list.getRightSenders().get(i) - 1).setNewRightMsg(
					list.get(list.getRightSenders().get(i)).getRightMsg());
		}
		list.setRighMsgs();
		if ((int) Math.pow(2, stage) - step == 1) {
			list.setLeftRequesters();
		} else {
			list.setRighSenders();
		}
	}

	private static void send(MyAbstractList list, int stage, int step) {
		sendLeft(list, stage, step);
		sendRight(list, stage, step);
	}

}
