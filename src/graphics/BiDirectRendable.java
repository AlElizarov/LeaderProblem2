package graphics;

public interface BiDirectRendable extends IRenderer{
	
	public void drawCurrentLeaders(int ballIdx);
	
	public void drawLeftMsgs(int ballIdx, String leftMsg);
	
	public void drawRightMsgs(int ballIdx, String rightMsg);

}
