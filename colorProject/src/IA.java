import java.awt.Color;
import java.util.Random;

public class IA extends Player {
	
	private int level;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static int[] nextEventScore = new int[Main.nbPlayers];
	public static int scoreMax1;
	public static int scoreMax2;
	public static int scoreMax3;
	public static int scoreMax4;
	
	public static int getScoreMax1() {
		return scoreMax1;
	}

	public static void setScoreMax1(int scoreMax1) {
		IA.scoreMax1 = scoreMax1;
	}

	public static int getScoreMax2() {
		return scoreMax2;
	}

	public static void setScoreMax2(int scoreMax2) {
		IA.scoreMax2 = scoreMax2;
	}

	public static int getScoreMax3() {
		return scoreMax3;
	}

	public static void setScoreMax3(int scoreMax3) {
		IA.scoreMax3 = scoreMax3;
	}

	public static int getScoreMax4() {
		return scoreMax4;
	}

	public static void setScoreMax4(int scoreMax4) {
		IA.scoreMax4 = scoreMax4;
	}

	public IA(String name, Color colorInit, int xInit, int yInit, int nbCases, boolean isBegin, int level) {
		super(name, colorInit, xInit, yInit, nbCases, isBegin);
		this.level = level;
	}

	public static char getPlayIA() {
		char[] possibleColor = new char[6-Main.nbPlayers];
		possibleColorPlayIA(possibleColor);
		if (Game.getPlayerPlaying().getLevel() == 4) {
			char c1 = bestPossibleColorPlayIA(possibleColor);
			int n1 = Game.getPlayerPlaying().getNbCases();
			int nNextMax = nextEventScore[Game.getTurn() % Main.nbPlayers];
			Game.setTurn(Game.getTurn()+1); 
			char c2 = bestPossibleColorPlayIA(possibleColor);
			int n2 = Game.getPlayerPlaying().getNbCases();
			int nNextMax2 = nextEventScore[(Game.getTurn()) % Main.nbPlayers];
			Game.setTurn(Game.getTurn()-1);
			//if (n1 > n2 && nNextMax > 4) {
			if ((n1+nNextMax) > (n2+nNextMax2+6)) {
				return c2;
			} else {
				return c1;
			}
		} else if (Game.getPlayerPlaying().getLevel() == 3) {
			Game.setTurn(Game.getTurn()+1); 
			char c = bestPossibleColorPlayIA(possibleColor);
			Game.setTurn(Game.getTurn()-1);
			return c;
		} else if (Game.getPlayerPlaying().getLevel() == 2) {
			return bestPossibleColorPlayIA(possibleColor);
		} else {
			Random rand = new Random();
			int randomColor = rand.nextInt(6-Main.nbPlayers);
			char c = possibleColor[randomColor];
			return c;
		}
	}
	
	public static void possibleColorPlayIA(char[] possibleColor) {
		char[] listColor = {'R','B','J','V','I','O'};
		int j = 0;
		for (int i = 0; i < 6; i++) {
			if (Main.nbPlayers == 2) {
				if (Game.getCharFromColor(Game.getPlayers()[0].getColorInit()) != listColor[i] && Game.getCharFromColor(Game.getPlayers()[1].getColorInit()) != listColor[i]) {
					possibleColor[j] = Game.compareControled(listColor[i]);
					j++;
				}
			} else if (Main.nbPlayers == 3) {
				if ((Game.getCharFromColor(Game.getPlayers()[0].getColorInit()) != listColor[i] && Game.getCharFromColor(Game.getPlayers()[1].getColorInit()) != listColor[i]) && Game.getCharFromColor(Game.getPlayers()[2].getColorInit()) != listColor[i]) {
					possibleColor[j] = Game.compareControled(listColor[i]);
					j++;
				}
			} else if (Main.nbPlayers == 4) {
				if (Game.getCharFromColor(Game.getPlayers()[0].getColorInit()) != listColor[i] && Game.getCharFromColor(Game.getPlayers()[1].getColorInit()) != listColor[i] && Game.getCharFromColor(Game.getPlayers()[2].getColorInit()) != listColor[i] && Game.getCharFromColor(Game.getPlayers()[3].getColorInit()) != listColor[i]) {
					possibleColor[j] = Game.compareControled(listColor[i]);
					j++;
				}
			}
		}
	}
	
	public static char bestPossibleColorPlayIA(char[] possibleColor) {
		scoreMax1 = Game.getPlayerPlaying().getNbCases()-1;
		scoreMax2 = Game.getPlayerPlaying().getNbCases()-1;
		scoreMax3 = Game.getPlayerPlaying().getNbCases()-1;
		scoreMax4 = Game.getPlayerPlaying().getNbCases()-1;
		char bestPlay = ' ';
		if (Main.nbPlayers == 2) {
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[0], scoreMax1, 0);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[1], scoreMax2, 1);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[2], scoreMax3, 2);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[3], scoreMax4, 3);
			if (scoreMax1 >= scoreMax2 && scoreMax1 >= scoreMax3 && scoreMax1 >= scoreMax4) {
				bestPlay = possibleColor[0];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax1;
			} else if (scoreMax2 >= scoreMax1 && scoreMax2 >= scoreMax3 && scoreMax2 >= scoreMax4) {
				bestPlay = possibleColor[1];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax2;
			} else if (scoreMax3 >= scoreMax1 && scoreMax3 >= scoreMax2 && scoreMax3 >= scoreMax4) {
				bestPlay = possibleColor[2];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax3;
			} else if (scoreMax4 >= scoreMax1 && scoreMax4 >= scoreMax2 && scoreMax4 >= scoreMax3) {
				bestPlay = possibleColor[3];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax4;
			} else {
				Random rand = new Random();
				int randomColor = rand.nextInt(6-Main.nbPlayers);
				bestPlay = possibleColor[randomColor];
			}
		} else if (Main.nbPlayers == 3) {
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[0], scoreMax1, 0);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[1], scoreMax2, 1);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[2], scoreMax3, 2);
			if (scoreMax1 >= scoreMax2 && scoreMax1 >= scoreMax3) {
				bestPlay = possibleColor[0];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax1;
			} else if (scoreMax2 >= scoreMax1 && scoreMax2 >= scoreMax3) {
				bestPlay = possibleColor[1];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax2;
			} else if (scoreMax3 >= scoreMax1 && scoreMax3 >= scoreMax2) {
				bestPlay = possibleColor[2];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax3;
			} else {
				Random rand = new Random();
				int randomColor = rand.nextInt(6-Main.nbPlayers);
				bestPlay = possibleColor[randomColor];
			}
		} else if (Main.nbPlayers == 4) {
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[0], scoreMax1, 0);
			fillGridIA (storeGrille(), Game.getPlayerPlaying().getXInit(), Game.getPlayerPlaying().getYInit(), possibleColor[1], scoreMax2, 1);
			if (scoreMax1 >= scoreMax2) {
				bestPlay = possibleColor[0];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax1;
			} else if (scoreMax2 >= scoreMax1) {
				bestPlay = possibleColor[1];
				nextEventScore[Game.getTurn() % Main.nbPlayers] = scoreMax2;
			} else {
				Random rand = new Random();
				int randomColor = rand.nextInt(6-Main.nbPlayers);
				bestPlay = possibleColor[randomColor];
			}
		}
		return bestPlay;				
	}
	
	public static char[][] storeGrille() {
		char[][] gridIA = new char[Grille.getGrid().length][Grille.getGrid().length];
		for (int i = 0; i < Grille.getGrid().length; i++) {
			for (int j = 0; j < Grille.getGrid()[i].length; j++) {
				gridIA[i][j] = Grille.getGrid()[i][j];
			}
		}
		return gridIA;
	}
	
	public static void fillGridIA (char[][] gridIA, int r, int c, char couleur, int scoreMax, int idColor) {
		if (r < 0 || c < 0) return;
        if (r >= gridIA.length || c >= gridIA.length) return;
        if (gridIA[r][c] == Game.getCharFromColor(Game.getPlayerPlaying().getColorInit()) || gridIA[r][c] == couleur) {
        	if ((!Game.getPlayerPlaying().isBegin() && Grille.getGrid()[r][c] == Game.compareControled(Game.getCharFromColor(Game.getPlayerPlaying().getColorInit()))) || (gridIA[r][c] == couleur && gridIA[r][c] != Game.getCharFromColor(Game.getPlayerPlaying().getColorInit()))) {
        		if (idColor == 0) {
    				scoreMax1++;
    			} else if (idColor == 1) {
    				scoreMax2++;
    			} else if (idColor == 2) {
    				scoreMax3++;
    			} else if (idColor == 3) {
    				scoreMax4++;
    			}
           	}
        	gridIA[r][c] = Game.getControled(couleur);
			fillGridIA(gridIA, r + 1, c, couleur, scoreMax, idColor);
	        fillGridIA(gridIA, r - 1, c, couleur, scoreMax, idColor);
	        fillGridIA(gridIA, r, c + 1, couleur, scoreMax, idColor);
	        fillGridIA(gridIA, r, c - 1, couleur, scoreMax, idColor);
	    }
    }
}
