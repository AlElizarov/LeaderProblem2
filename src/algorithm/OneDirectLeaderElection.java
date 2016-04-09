package algorithm;

public class OneDirectLeaderElection {

	public static void solve(MyAbstractList list, int i) {
		if (i == 0) {
			list.initiateStartState();
		} else {
			list.setMessages();
		}
	}

}
