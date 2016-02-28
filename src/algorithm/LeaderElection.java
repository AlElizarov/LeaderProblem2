package algorithm;

public class LeaderElection {

	public static int solve(MyRingList list) {
		Agent next;
		for (int i = 0; i <= list.size(); i++) {
			while (list.hasNext()) {
				next = list.next();
				if (i == 0) {
					list.getNeiborough().setMsg(next.getId());
					list.getNeiborough().setNewMsg(next.getId());
					continue;
				}
				if (next.getId() == next.getMsg()) {
					return next.getId();
				}
			}
			list.setMessages();
		}
		return 100;
	}

}
