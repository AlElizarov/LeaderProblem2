package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import algorithm.Agent;

public class BiDirectPanel extends MyAbstractPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xLeaderPos;
	private int yLeaderPos;
	private boolean nextStepGetLeader;
	private int stepBeforeLeader;
	private int leaderPos = -1;

	public void paintComponent(Graphics graphics) {
		xLeaderPos = roundCenterX;
		yLeaderPos = roundCenterY;
		if (quantity == 2 || quantity == 1) {
			yLeaderPos -= 100;
		}
		if (quantity >= 20) {
			graphics.setFont(new Font("Veranda", Font.ITALIC, 26));
			graphics.drawString("    Визуальный режим доступен ", 100, 150);
			graphics.drawString("только при объеме входных", 100, 190);
			graphics.drawString("данных больше 20 в двунаправленном", 100, 230);
			graphics.drawString("режиме. Вы можете продолжить", 100, 270);
			graphics.drawString("работу в текстовом режиме", 100, 310);
			return;
		}
		if (quantity == 2) {
			ARR_SIZE = 8;
			drawArrow(graphics, newCoordX(1), newCoordY(1), newCoordX(2),
					newCoordY(2), true);
			drawArrow(graphics, newCoordX(2), newCoordY(2), newCoordX(1),
					newCoordY(1), false);
		} else {
			if (quantity != 1) {
				createLines(graphics, true);
			}
		}
		int ballIdx = 0;
		// System.out.println("LS in gui" + list.getLeftSenders());
		if (stage == 0 && biDirectStep == 1) {
			stepBeforeLeader = 0;
			nextStepGetLeader = false;
			leaderPos = -1;
		}
		while (list.hasNext()) {
			Agent currentBall = list.next();
			if(quantity == 1){
				if((stage == 0 && biDirectStep == 0) || (stage == 1 && biDirectStep == 0)){
					graphics.setColor(Color.YELLOW);
					drawBalls(graphics, roundCenterX, roundCenterY, currentBall);
					continue;
				}
				if(stage == 0 && biDirectStep == 1){
					graphics.setColor(Color.RED);
					drawBalls(graphics, roundCenterX, roundCenterY, currentBall);
					graphics.setColor(Color.RED);
					graphics.drawString("LEADER", xLeaderPos - 30, yLeaderPos);
					graphics.setColor(Color.RED);
					drawArrow(graphics, xLeaderPos, yLeaderPos, roundCenterX,
							roundCenterY, true);
					continue;
				}
			}
			if (nextStepGetLeader && biDirectStep != stepBeforeLeader) {
				graphics.setColor(Color.red);
				int oldArrSize = ARR_SIZE;
				ARR_SIZE = 12;
				graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
				graphics.drawString("LEADER", xLeaderPos - 30, yLeaderPos);
				
					drawArrow(graphics, xLeaderPos, yLeaderPos,
							newCoordX(leaderPos + 1), newCoordY(leaderPos + 1),
							true);
				
				nextStepGetLeader = false;
				// leaderPos = -1;
				// stepBeforeLeader = 0;
				ARR_SIZE = oldArrSize;
				drawBalls(graphics, newCoordX(ballIdx + 1),
						newCoordY(ballIdx + 1), currentBall);
			}
			if (ballIdx == leaderPos) {
				graphics.setColor(Color.RED);
				// leaderPos = -1;
				drawBalls(graphics, newCoordX(ballIdx + 1),
						newCoordY(ballIdx + 1), currentBall);

			}
			if (list.getCurrentLeaders().contains(ballIdx)) {

				graphics.setColor(Color.BLUE);

				int loopEnd = (int) (Math.log(list.size()) / Math.log(2));
				if ((list.size() & (list.size() - 1)) != 0) {
					loopEnd += 1;
				}
				if (list.get(ballIdx).getId() == list.get(ballIdx - 1)
						.getLeftMsg()
						&& stage == loopEnd
						&& biDirectStep == list.size() - 1) {
					// graphics.setColor(Color.red);
					// int oldArrSize = ARR_SIZE;
					// ARR_SIZE = 12;
					// graphics.setFont(new Font("Veranda", Font.BOLD,
					// msgSize));
					// graphics.drawString("LEADER", xLeaderPos - 30,
					// yLeaderPos);
					nextStepGetLeader = true;
					stepBeforeLeader = biDirectStep;
					leaderPos = ballIdx;
				}

			} else {
				if (ballIdx != leaderPos) {
					graphics.setColor(Color.YELLOW);
				}
			}
			if (stage == 0 && (biDirectStep == 1 || biDirectStep == 0)) {
				if (ballIdx != leaderPos)
					graphics.setColor(Color.YELLOW);
				else {
					graphics.setColor(Color.RED);
				}
			}

			
			drawBalls(graphics, newCoordX(ballIdx + 1), newCoordY(ballIdx + 1),
					currentBall);
			// if(biDirectStep == Math.pow(2, stage)){
			// System.out.println("yes");
			// }
			// System.out.println("stage, step"+stage+" "+biDirectStep);
			if (biDirectStep == 0 && stage != 0) {
				graphics.setColor(Color.BLUE);
				int oldArrSize = ARR_SIZE;
				ARR_SIZE = 12;
				graphics.setFont(new Font("Veranda", Font.BOLD, msgSize));
				graphics.drawString("CURRENT LEADERS", xLeaderPos - 30,
						yLeaderPos);
				if (list.getCurrentLeaders().contains(ballIdx)) {
					drawArrow(graphics, xLeaderPos, yLeaderPos,
							newCoordX(ballIdx + 1), newCoordY(ballIdx + 1),
							true);
				}
				ARR_SIZE = oldArrSize;
				ballIdx++;
				continue;
			}

			if (list.getLeftSenders().contains(ballIdx)) {
				if (biDirectStep != 0 || stage != 0) {
					if (stage == 0) {
						if (quantity != 2) {
							drawLeftMsgs(graphics, newCoordX(ballIdx),
									newCoordX(ballIdx + 1), newCoordY(ballIdx),
									newCoordY(ballIdx + 1), currentBall);
						} else {
							drawLeftMsgs(graphics, newCoordX(ballIdx) - 20,
									newCoordX(ballIdx + 1) - 20,
									newCoordY(ballIdx), newCoordY(ballIdx + 1),
									currentBall);
						}
					} else {
						if (quantity != 2) {
							drawLeftMsgs(graphics, newCoordX(ballIdx + 1),
									newCoordX(ballIdx + 2),
									newCoordY(ballIdx + 1),
									newCoordY(ballIdx + 2), currentBall);
						} else {
							drawLeftMsgs(graphics, newCoordX(ballIdx) - 20,
									newCoordX(ballIdx + 1) - 20,
									newCoordY(ballIdx), newCoordY(ballIdx + 1),
									currentBall);
						}
					}
				}
			}

			if (list.getRightSenders().contains(ballIdx)) {
				if (biDirectStep != 0 || stage != 0) {
					if (stage == 0) {
						if (quantity != 2) {
							drawRightMsgs(graphics, newCoordX(ballIdx + 1),
									newCoordX(ballIdx + 2),
									newCoordY(ballIdx + 1),
									newCoordY(ballIdx + 2), currentBall);
						} else {
							drawRightMsgs(graphics,
									newCoordX(ballIdx + 1) + 20,
									newCoordX(ballIdx + 2) + 20,
									newCoordY(ballIdx + 1),
									newCoordY(ballIdx + 2), currentBall);
						}
					} else {
						if (quantity != 2) {
							drawRightMsgs(graphics, newCoordX(ballIdx),
									newCoordX(ballIdx + 1), newCoordY(ballIdx),
									newCoordY(ballIdx + 1), currentBall);
						} else {
							drawRightMsgs(graphics, newCoordX(ballIdx) + 20,
									newCoordX(ballIdx + 1) + 20,
									newCoordY(ballIdx), newCoordY(ballIdx + 1),
									currentBall);
						}
					}
				}
			}

			ballIdx++;
		}
	}

}
