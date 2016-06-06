import java.util.Random;

public class Grille {
	
	private static char[][] grid;
	
	public static char[][] getGrid() {
		return grid;
	}

	public void setGrid(char[][] grid) {
		Grille.grid = grid;
	}
	
	public Grille(int size, int nbColors) {
		grid = new char[size][size];
		for(int row = 0; row < size; row++){
			for(int col = 0; col < grid[row].length; col++){
				if (row == 0 && col == 0) {
            		grid[row][col] = Game.getControled(getRandomChar(nbColors));
            	} else if (Main.nbPlayers == 4 && (row == 0 && col == size-1)) {
            		do {
            			grid[row][col] = Game.getControled(getRandomChar(nbColors));
            		} while (grid[row][col] == getGrid()[0][0]);
            	} else if (Main.nbPlayers >= 3 && (row == size-1 && col == 0)) {
            		do {
            			grid[row][col] = Game.getControled(getRandomChar(nbColors));
            		} while (grid[row][col] == getGrid()[0][0] || grid[row][col] == getGrid()[0][size-1]);
            	} else if (row == size-1 && col == size-1) {
            		do {
            			grid[row][col] = Game.getControled(getRandomChar(nbColors));
            		} while (grid[row][col] == getGrid()[0][0] || grid[row][col] == getGrid()[0][size-1] || grid[row][col] == getGrid()[size-1][0]);
            	} else {
            		grid[row][col] = getRandomChar(nbColors);
            	}							
	        }
	    }
	}
	
	public char getRandomChar(int max) {
		char[] listOfChar = {'r', 'b', 'j', 'v', 'i', 'o'};
		char c = ' ';
		Random rand = new Random();
		int randomColor = rand.nextInt(max);
		for (int i = 0; i < max; i++) {
			if (randomColor == i) {
				c = listOfChar[i];
				break;
			}
		}
		return c;
	}
	
	public char getCharFromGrid(int x, int y) {
		return grid[x][y];
	}
	
	public void setCell(int x, int y, char c) {
			grid[x][y] = c;
	}
	
}
	
