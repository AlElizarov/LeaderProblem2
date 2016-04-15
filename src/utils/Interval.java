package utils;

public class Interval {

	private int start;
	private int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public void moveTop(int shift) {
		start += shift;
		end += shift;
	}
	
	public void moveDown(int shift) {
		start -= shift;
		end -= shift;
	}

	public int getCenter() {
		return (start+end)/2;
	}

	public Interval turnBack() {
		return new Interval(end, start);
	}

}
