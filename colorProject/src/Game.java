import java.awt.Color;
import java.util.*;
import java.util.Scanner;

public class Game {

	private static int turn = 0;
	private static Grille grille;
	private static Player[] players;
	private boolean finished = false;
	public int n;
	
	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		Game.turn = turn;
	}

	public static Grille getGrille() {
		return grille;
	}

	public static void setGrille(Grille grille) {
		Game.grille = grille;
	}

	public static Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		Game.players = players;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public Game(int nbPlayers) {
		super();
		players = new Player[nbPlayers]; 
	}

	public void initGame(int size, int colorMax, int nbPlayers, int realGamer) {
		grille = new Grille(size, colorMax);
		n = nbPlayers;
		Scanner sc = new Scanner(System.in);
		int[] xStart = {0,size-1,size-1,0};
		int[] yStart = {0,size-1,0,size-1};
		int nbIA = 0;
		for (int i = 0; i < nbPlayers; i++) {
			if (realGamer != 0) {
				players[i] = new Player("Player " + Integer.toString(i+1), getColorFromChar(Grille.getGrid()[xStart[i]][yStart[i]]), xStart[i], yStart[i], 1, true);
				System.out.println("Player "+ (i+1) + " s'appelle : ");
				do {
					getPlayers()[i].setName(sc.nextLine());
				} while (getPlayers()[i].getName() == "Player 1" || getPlayers()[i].getName() == null);	
				realGamer--;
			} else {
				System.out.println("Player " + (i+1) + " est une machine, choisissez le type d'intelligence : " + "\n" + " 1 --> Aléatoire " + "\n" + " 2 --> Offensive (capture le plus de cases) " + "\n" + " 3 --> Défensive (gêne le plus l'adversaire sur le choix des cases) ");
				nbIA++;
				players[i] = new IA("Machine " + Integer.toString(nbIA), getColorFromChar(Grille.getGrid()[xStart[i]][yStart[i]]), xStart[i], yStart[i], 1, true, sc.nextInt());	
			}
			grille.setCell(xStart[i], yStart[i], Game.getControled(Grille.getGrid()[xStart[i]][yStart[i]]));
		}
		sc.close();
	}

	public void launchedGame() {
		while (getPlayerPlaying().isBegin() == true) {
			fillGrid(grille, getPlayerPlaying().getXInit(), getPlayerPlaying().getYInit());
			turn++;
		}
		turn = 0;
		GraphicInterface.display(Main.formDisplay);
		System.out.println(getPlayerPlaying().getName() + " commence à jouer et contrôle la couleur '" + getCharFromColor(getPlayerPlaying().getColorInit()) + "' .");
		infoHumanPlayer();
		while (finished == false) {
			if(getPlayerPlaying() instanceof IA) {
				playMove(IA.getPlayIA());
			} else {
				playMove(HumanPlayer.getPlayHuman());
			}
			if (n == Grille.getGrid().length*Grille.getGrid().length) {
				setFinished(true);
				System.out.println("Le jeu est terminé : ");
				gameIsOver();
			}
		}
	}
	
	public void playMove(char c) {
		if ((Main.nbPlayers == 2 && c != compareControled(getCharFromColor(players[0].getColorInit())) && c != compareControled(getCharFromColor(players[1].getColorInit()))) || (Main.nbPlayers == 3 && c != compareControled(getCharFromColor(players[0].getColorInit())) && c != compareControled(getCharFromColor(players[1].getColorInit())) && c != compareControled(getCharFromColor(players[2].getColorInit()))) || (Main.nbPlayers == 4 && c != compareControled(getCharFromColor(players[0].getColorInit())) && c != compareControled(getCharFromColor(players[1].getColorInit())) && c != compareControled(getCharFromColor(players[2].getColorInit())) && c != compareControled(getCharFromColor(players[3].getColorInit())))) {
			if (c == 'r' || c == 'o' || c == 'j' || c == 'v' || c == 'b' || c == 'i') {
				fillGrid(grille, getPlayerPlaying().getXInit(), getPlayerPlaying().getYInit(), c);
				getPlayerPlaying().setColor(getColorFromChar(getControled(c)));
				GraphicInterface.display(Main.formDisplay);
				if (getPlayerPlaying().getNbCases() > Grille.getGrid().length*Grille.getGrid().length/2) {
					setFinished(true);
					System.out.print("Le jeu est terminé : " + "\n" + getPlayerPlaying().getName() + " a GAGNÉ avec " +getPlayerPlaying().getNbCases() + " cases contrôlées ! ");
				} else {
					turn++;
					System.out.println(getPlayerPlaying().getName() + " joue et contrôle " + getPlayerPlaying().getNbCases() + " cases de la couleur '" + getCharFromColor(getPlayerPlaying().getColorInit()) + "' . ");
					infoHumanPlayer();
				}
			} else {
				System.out.println("La couleur choisie n'est pas correcte, rejouez : ");
			}
		} else {
			System.out.println("La couleur choisie n'est pas disponible pour ce tour, rejouez : ");
		}
	}
	
	public void fillGrid (Grille grille, int r, int c) {
		if (r < 0 || c < 0) return;
        if (r >= Grille.getGrid().length || c >= Grille.getGrid()[r].length) return;
	    if ((getPlayerPlaying().isBegin() == true && Grille.getGrid()[r][c] == getCharFromColor(getPlayerPlaying().getColorInit())) || (!getPlayerPlaying().isBegin() && Grille.getGrid()[r][c] == compareControled(getCharFromColor(getPlayerPlaying().getColorInit())))) {
	        if(getPlayerPlaying().isBegin() == true && Grille.getGrid()[r][c] == getCharFromColor(getPlayerPlaying().getColorInit())) {
	        	getPlayerPlaying().setBegin(false);
	        }
	        if (!getPlayerPlaying().isBegin() && Grille.getGrid()[r][c] == compareControled(getCharFromColor(getPlayerPlaying().getColorInit()))) {
				scoreCompteur();
	       	}
	        grille.setCell(r, c, getCharFromColor(getPlayerPlaying().getColorInit()));
	        fillGrid(grille, r + 1, c);
	        fillGrid(grille, r - 1, c);
	        fillGrid(grille, r, c + 1);
	        fillGrid(grille, r, c - 1);   
		}
    }
	
	public void fillGrid (Grille grille, int r, int c, char couleur) {
		if (r < 0 || c < 0) return;
        if (r >= Grille.getGrid().length || c >= Grille.getGrid()[r].length) return;
        if (Grille.getGrid()[r][c] == getCharFromColor(getPlayerPlaying().getColorInit()) || Grille.getGrid()[r][c] == couleur) {
        	if (Grille.getGrid()[r][c] == couleur && Grille.getGrid()[r][c] != getCharFromColor(getPlayerPlaying().getColorInit())) {
    			scoreCompteur();
           	}
        	grille.setCell(r, c, getControled(couleur));
			fillGrid(grille, r + 1, c, couleur);
	        fillGrid(grille, r - 1, c, couleur);
	        fillGrid(grille, r, c + 1, couleur);
	        fillGrid(grille, r, c - 1, couleur);
	    }
    }
	
	public void possibleColorPlay() {
		char[] listColor = {'R','B','J','V','I','O'};
		for (int i = 0; i < 6; i++) {
			if (Main.nbPlayers == 2) {
				if (getCharFromColor(players[0].getColorInit()) != listColor[i] && getCharFromColor(players[1].getColorInit()) != listColor[i]) {
					System.out.print(compareControled(listColor[i]) + " | ");
				}
			} else if (Main.nbPlayers == 3) {
				if ((getCharFromColor(players[0].getColorInit()) != listColor[i] && getCharFromColor(players[1].getColorInit()) != listColor[i]) && getCharFromColor(players[2].getColorInit()) != listColor[i]) {
					System.out.print(compareControled(listColor[i]) + " | ");
				}
			} else if (Main.nbPlayers == 4) {
				if (getCharFromColor(players[0].getColorInit()) != listColor[i] && getCharFromColor(players[1].getColorInit()) != listColor[i] && getCharFromColor(players[2].getColorInit()) != listColor[i] && getCharFromColor(players[3].getColorInit()) != listColor[i]) {
					System.out.print(compareControled(listColor[i]) + " | ");
				}
			}
		}
		System.out.print("\n");
	}
	
	public static char getControled(char selectedColor) {
		return ("" + selectedColor).toUpperCase().charAt(0);
	}
	
	public static char compareControled(char selectedColor) {
		return ("" + selectedColor).toLowerCase().charAt(0);
	}
	
	public static Color getColorFromChar(char c) {
		if (c == 'r' || c == 'R') {
			return Color.RED;
		} else if (c == 'b' || c == 'B') {
			return Color.BLUE;
		} else if (c == 'j' || c == 'J') {
			return Color.YELLOW;
		} else if (c == 'v' || c == 'V') {
			return Color.GREEN;
		} else if (c == 'i' || c == 'I') {
			return Color.MAGENTA;
		} else {
			return Color.ORANGE;
		}
	}
	
	public static char getCharFromColor(Color c) {
		if (c == Color.RED) {
			return 'R';
		} else if (c == Color.BLUE) {
			return 'B';
		} else if (c == Color.YELLOW) {
			return 'J';
		} else if (c == Color.GREEN) {
			return 'V';
		} else if (c == Color.MAGENTA) {
			return 'I';
		} else {
			return 'O';
		}
	}
	
	public static void gameIsOver() {
		int[] scorePlayers = new int[Main.nbPlayers];
		int temp = 0, indice_temp = 0;
		for(int i = 0; i<Main.nbPlayers; i++) {
			scorePlayers[i] = players[i].getNbCases();
			if (scorePlayers[i] >= temp) {
				temp = scorePlayers[i];
				indice_temp = i;
			}
		}
		System.out.println(players[indice_temp].getName() + " a GAGNÉ avec " + players[indice_temp].getNbCases() + " cases contrôlées ! ");
	}
	
	public static Player getPlayerPlaying() {
		return players[turn % Main.nbPlayers];
	}
	
	public void scoreCompteur() {
		getPlayerPlaying().setNbCases(getPlayerPlaying().getNbCases()+1);
   		n++;
	}
	
	public void infoHumanPlayer() {
		if(!(getPlayerPlaying() instanceof IA)) {
			System.out.print("Saisissez une couleur parmis --> | ");
			possibleColorPlay();
		}
	}
	
}