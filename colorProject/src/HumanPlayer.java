import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class HumanPlayer extends Player {
	
	private static int goodPlay = 0;
	
	public int getGoodPlay() {
		return goodPlay;
	}

	public static void setGoodPlay(int goodPlay) {
		HumanPlayer.goodPlay = goodPlay;
	}

	public HumanPlayer(String name, Color colorInit, int xInit, int yInit, int nbCases, boolean isBegin) {
		super(name, colorInit, xInit, yInit, nbCases, isBegin);
	}
	
	public static char getPlayHuman() {
		int x, y;
		char c = ' ';
		int played = goodPlay;
		while (goodPlay == played) {
			boolean isClicking = false;
			if (StdDraw.mouseX() > 0 && StdDraw.mouseX() < Main.windowWidth - 10 && StdDraw.mouseY() > 0 && StdDraw.mouseY() < Main.windowWidth - 10) {
				isClicking = StdDraw.mousePressed();
				if (StdDraw.mousePressed() && isClicking) {
					x = (int) StdDraw.mouseX() / GraphicInterface.getCellWidth();
					y = (int) StdDraw.mouseY() / GraphicInterface.getCellWidth();
					c = Grille.getGrid()[x][y];
					setGoodPlay(played++);
				}
			} else if (StdDraw.mouseY() > Main.windowWidth + 40 && StdDraw.mouseY() < Main.windowWidth + 120 && ((StdDraw.mouseX() > 210 && StdDraw.mouseX() < 360) || (StdDraw.mouseX() > 360 && StdDraw.mouseX() < 460) || (StdDraw.mouseX() > 460 && StdDraw.mouseX() < 560) || (StdDraw.mouseX() > 560 && StdDraw.mouseX() < 660) || (StdDraw.mouseX() > 660 && StdDraw.mouseX() < 760) || (StdDraw.mouseX() > 760 && StdDraw.mouseX() < 860))) {
				isClicking = StdDraw.mousePressed();
				if (StdDraw.mousePressed() && isClicking) {
					x = (int) StdDraw.mouseX() / ((Main.windowWidth+10)/10) - 2 ;
					if (x < 6) {
						c = GraphicInterface.getOptionGrid()[x];
					}
					setGoodPlay(played++);
				}
			}
		}
		return c;
	}

}
