package algorithm;


public class BiDirectLeaderElection {

	public static void choice(MyAbstractList list, int stage, int step) {
		if (stage == 0) {
			list.initiateCurrentLeaders();
		}
		if (step == 0) {
			list.initiateSenders();
		}
		if (step < Math.pow(2, stage)) {
			list.send(stage, step);
		} else {
			list.setRequests(stage);
		}
	}

}
