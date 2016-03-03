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
		leftRequest(list, stage);
		rightRequest(list, stage);
	}

	private static void leftRequest(MyAbstractList list, int stage) {
		list.setLeftRequests(stage);
	}

	private static void rightRequest(MyAbstractList list, int stage) {
		list.setRightRequests(stage);
	}

	private static void sendLeft(MyAbstractList list, int stage, int step) {
		list.setCorrectLeftSendersIndexes();
		System.out.println("LSinSend"+list.getLeftSenders());
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
		System.out.println("RSinSend"+list.getRightSenders());
		
		for (int i = 0; i < list.getRightSenders().size(); i++) {
			list.get(list.getRightSenders().get(i) - 1).setNewRightMsg(
					list.get(list.getRightSenders().get(i)).getRightMsg());
		}
		list.setRighMsgs();
		System.out.println("messagesInSend"+list.printLeftAndRightMsgs());
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
	
	public static void main(String[] args){
		MyAbstractList list = new MyRingArrayList();
		list.add(new Agent(12));
		list.add(new Agent(11));
		list.add(new Agent(10));
		list.add(new Agent(9));
		list.add(new Agent(8));
		list.add(new Agent(7));
		list.add(new Agent(6));
		list.add(new Agent(5));
		list.add(new Agent(4));
		list.add(new Agent(3));
		list.add(new Agent(2));
		list.add(new Agent(1));
		//System.out.println((int)(Math.log(list.size())/Math.log(2)));
		int loopEnd = (int)(Math.log(list.size())/Math.log(2));
		if((list.size() & (list.size()-1)) != 0){
			loopEnd += 1;
		}
		for(int i = 0; i < loopEnd; i++){
			for(int j = 0; j <= Math.pow(2, i); j++ ){
				choice(list, i ,j);
			}
		}
		
//		choice(list, 0, 0);
//		choice(list, 0, 1);
		System.out.println("Leader!"+list.getCurrentLeaders().get(0));
//		choice(list, 1, 0);
		
//		//System.out.println("LS"+list.getLeftSenders());
//		choice(list, 1, 1);
//		choice(list, 1, 2);
		
//		choice(list, 2, 0);
//		choice(list, 2, 1);
//		choice(list, 2, 2);
//		choice(list, 2, 3);
//		choice(list, 2, 4);
		
//		System.out.println(list.getCurrentLeaders());
	}

}
