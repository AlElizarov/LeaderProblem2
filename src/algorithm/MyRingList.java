package algorithm;

public class MyRingList {

	private int size;
	private Node head;
	private Node current;
	private Node tail;

	private class Node {
		Agent info;
		Node next;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Agent agent) {
		Node tmp = new Node();
		tmp.info = agent;
		if (size == 0) {
			head = tmp;
			current = head;
			tail = tmp;
		}
		tail.next = tmp;
		tail = tmp;
		tmp.next = head;
		size++;
	}

	public Object size() {
		return size;
	}

}
