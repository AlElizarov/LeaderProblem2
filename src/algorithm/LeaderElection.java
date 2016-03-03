package algorithm;

public class LeaderElection {

	public static void solve(MyAbstractList list, int i) {
		if (i == 0) {
			Agent next;
			while (list.hasNext()) {
				next = list.next();
				list.getNextNeiborough().setMsg(next.getId());
				list.getNextNeiborough().setNewMsg(next.getId());
			}
		} else
			list.setMessages();
	}

}
