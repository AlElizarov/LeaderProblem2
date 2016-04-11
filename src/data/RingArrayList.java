package data;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

import algorithm.Agent;
import algorithm.DataForLeaderElecionKeepable;

public class RingArrayList implements DataForLeaderElecionKeepable {

	private int size;
	private Agent[] arr = new Agent[1];
	private int index = 0;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(Agent agent) {
		if (agent.getId() <= 0) {
			throw new NonPositiveValueException("Отрицательное значение: "
					+ agent.getId());
		}
		if (size >= arr.length) {
			Agent[] temp = arr;
			arr = new Agent[temp.length * 2];
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
		arr[size++] = agent;
	}

	public void addAll(Agent[] data) {
		int len = data.length;
		int[] idata = new int[len];
		for (int i = 0; i < len; i++) {
			idata[i] = data[i].getId();
		}
		addAll(idata);
	}

	public Agent next() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (index == size) {
			index = 0;
		}
		return arr[index++];
	}

	public boolean hasNext() {
		if (index >= size) {
			index = 0;
			return false;
		}
		return true;
	}

	@Override
	public Agent getNextNeiborough() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (index == size) {
			return arr[0];
		}
		return arr[index];
	}

	public Agent get(int i) {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		while (i >= size) {
			i -= size;
		}
		while (i < 0) {
			i += size;
		}
		return arr[i];
	}

	@Override
	public String toString() {
		String s = "";
		while (hasNext()) {
			s += next().getId();
			if (index < size)
				s += ", ";
		}
		return s;
	}

	@Override
	public void add(int item) {
		add(new Agent(item));
	}

	@Override
	public void add(String item) {
		try {
			add(Integer.parseInt(item));
		} catch (NumberFormatException exc) {
			throw new FormatException("Неверный формат: " + item);
		}
	}

	@Override
	public void addAll(int[] data) {
		if (data.length <= 0) {
			throw new EmptyDataException();
		}
		handleDuplicateItems(data);
		for (int item : data) {
			add(item);
		}
	}

	@Override
	public void addAll(String[] data) {
		int len = data.length;
		int[] idata = new int[len];
		for (int i = 0; i < len; i++) {
			try {
				idata[i] = Integer.parseInt(data[i]);
			} catch (NumberFormatException exc) {
				throw new FormatException("Неверный формат: " + data[i]);
			}
		}
		addAll(idata);
	}

	private void handleDuplicateItems(int[] data) {
		LinkedHashSet<Integer> intItems = new LinkedHashSet<>();
		for (int item : data) {
			if (!intItems.add(item)) {
				throw new DuplicateValueException("Повторяющееся значение: "
						+ item);
			}
		}
	}

}
