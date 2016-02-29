package algorithm;

public class LeaderElection {

	public static boolean solve(MyRingList list, int i) {
		Agent next;
		while (list.hasNext()) {
			next = list.next();
			if (i == 0) {
				list.getNeiborough().setMsg(next.getId());
				list.getNeiborough().setNewMsg(next.getId());
				continue;
			}
//			if (list.getNeiborough().getId() == list.getNeiborough()
//					.getNewMsg()) {
//				while (list.hasNext()) {
//					next = list.next();
//					if (next.getId() < next.getMsg()) {
//						list.getNeiborough().setNewMsg(next.getMsg());
//					} else {
//						list.getNeiborough().setNewMsg(0);
//					}
//				}
//				list.setMessages();
//				return true;
//			}
		}
		if(i != 0)
		list.setMessages();
		return true;
	}

}
