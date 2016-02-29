package algorithm;

public class LeaderElection {

	public static void solve(MyRingList list, int i) {
		Agent next;
		while (list.hasNext()) {
			next = list.next();
			if (i == 0) {
				list.getNeiborough().setMsg(next.getId());
				list.getNeiborough().setNewMsg(next.getId());
				continue;
			}
			if (next.getId() == next.getMsg()) {
				return;
			}
		}
		list.setMessages();
	}

}
