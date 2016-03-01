package algorithm;

public class LeaderElection {

	public static void solve(MyRingList list, int i) {
		if (i == 0) {
			Agent next;
			while (list.hasNext()) {
				next = list.next();
				list.getNeiborough().setMsg(next.getId());
				list.getNeiborough().setNewMsg(next.getId());
			}
		} else
			list.setMessages();
	}

}
