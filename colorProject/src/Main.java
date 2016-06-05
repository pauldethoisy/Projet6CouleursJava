import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class Main {
	
	public static final int windowWidth = 1000;
	
	public static final int windowHeight = 1000;
	
	public static int nbPlayers;
	public static int realGamer;
	public static int IALevel;
	public static int sizeGrid;
	public static int formDisplay;

	public static void main(String[] args) {
		
		StdDraw.setXscale(-20, windowWidth + 10);
		StdDraw.setYscale(-20, windowHeight + 160);
		
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Sélectionnez la forme de la grille (1 pour carrée, 2 pour rectangle)");
			formDisplay = scan.nextInt();
		} while (formDisplay > 2);
		
		do {
			System.out.println("Sélectionnez la taille de la grille (à partir de 3)");
			sizeGrid = scan.nextInt();
		} while (sizeGrid < 3);
		
		do {
			System.out.println("Sélectionnez le nombre total de joueurs (entre 2 et 4 compris) : ");
			nbPlayers = scan.nextInt();
		} while (nbPlayers < 2 || nbPlayers > 4);
		
		Game game13 = new Game(nbPlayers);
		
		do {
			System.out.println("Sélectionnez le nombre de joueurs réels, ou humains (entre 0 et " + nbPlayers + " ) : ");
			realGamer = scan.nextInt();
		} while (realGamer > nbPlayers);
		
		game13.initGame(sizeGrid, 6, nbPlayers, realGamer);
		game13.launchedGame();
		scan.close();
	}

}
