import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class GraphicInterface {
	
	private static char[] optionGrid;
	private static int xSize = Grille.getGrid().length;
	private static int ySize = Grille.getGrid()[0].length;
	
	public static char[] getOptionGrid() {
		return optionGrid;
	}

	public void setOptionGrid(char[] optionGrid) {
		GraphicInterface.optionGrid = optionGrid;
	}
	
	public static int getCellWidth() {
		return Main.windowWidth / Grille.getGrid().length; 
	}

	public static void display(int typeDisplay) {
		displayOptions(6);
		int CELL_WIDTH = getCellWidth();
		for(int i=0 ; i < xSize; i++) {
			for(int j=0 ; j < ySize; j++) {
				int semiWidth = CELL_WIDTH /2;
				StdDraw.setPenColor(Game.getColorFromChar(Grille.getGrid()[i][j]));
				int x = i * CELL_WIDTH + semiWidth;
				int y = j * CELL_WIDTH + semiWidth;
				if (typeDisplay == 1) {
					StdDraw.filledSquare(x, y, semiWidth);
					StdDraw.setPenColor(Color.black);
					StdDraw.square(x, y, semiWidth);
				} else if (typeDisplay == 2) {
					y = Main.windowWidth/3 + y/2;
					StdDraw.filledRectangle(x, y, semiWidth, semiWidth/2);
					StdDraw.setPenColor(Color.black);
					StdDraw.rectangle(x, y, semiWidth, semiWidth/2);
				}
				displayPlayers(x, y, i, j);
			}
		}
    }
	
	public static void displayPlayers(int x, int y, int i, int j) {
		if (i == 0 && j == 0) {
			StdDraw.text(x, y, "P1");
		} else if (i == xSize-1 && j == ySize-1) {
			StdDraw.text(x, y, "P2");
		} else if (Main.nbPlayers >= 3 && (i == xSize-1 && j == 0)) {
			StdDraw.text(x, y, "P3");
		} else if (Main.nbPlayers == 4 && (i == 0 && j == ySize-1)) {
			StdDraw.text(x, y, "P4");
		}
	}
	
	public static void optionGrid (int nbColor) {
		optionGrid = new char[nbColor];
		for (int i = 0; i < optionGrid.length; i++) {
			if (i == 0) {
				optionGrid[i] = 'r';
			} else if (i == 1) {
				optionGrid[i] = 'b';
			} else if (i == 2) {
				optionGrid[i] = 'j';
			} else if (i == 3) {
				optionGrid[i] = 'v';
			} else if (i == 4) {
				optionGrid[i] = 'i';
			} else {
				optionGrid[i] = 'o';
			} 
		}
	}
	
	public static void displayOptions (int nbColor) {
		optionGrid(nbColor);
		int y = Main.windowWidth + 80;
		int r = 50;
		int i=0;
		for(int x = 260 ; x <= 760; x = x + 2*r) {
			StdDraw.setPenColor(Game.getColorFromChar(optionGrid[i]));
			i++;
			StdDraw.filledSquare(x, y, r);
			StdDraw.setPenColor(Color.black);
			StdDraw.square(x, y, r);
		}
	}
}
