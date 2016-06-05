import java.awt.Color;

public class Player {

	private String name;
	private Color colorInit;
	private int nbCases;
	private int xInit;
	private int yInit;
	private boolean isBegin;
	private int level;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColorInit() {
		return colorInit;
	}

	public void setColor(Color colorInit) {
		this.colorInit = colorInit;
	}

	public int getXInit() {
		return xInit;
	}

	public void setXInit(int xInit) {
		this.xInit = xInit;
	}
	
	public int getYInit() {
		return yInit;
	}

	public void setYInit(int yInit) {
		this.yInit = yInit;
	}
	
	public int getNbCases() {
		return nbCases;
	}

	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
	
	public boolean isBegin() {
		return isBegin;
	}

	public void setBegin(boolean isBegin) {
		this.isBegin = isBegin;
	}
	
	public Player(String name, Color colorInit, int xInit, int yInit, int nbCases, boolean isBegin) {
		super();
		this.name = name;
		this.colorInit = colorInit;
		this.xInit = xInit;
		this.yInit = yInit;
		this.nbCases = nbCases;
		this.isBegin = isBegin;
	}
	
	public Player(String name, Color colorInit, int xInit, int yInit, int nbCases, boolean isBegin, int level) {
		super();
		this.name = name;
		this.colorInit = colorInit;
		this.xInit = xInit;
		this.yInit = yInit;
		this.nbCases = nbCases;
		this.isBegin = isBegin;
		this.level = level;
	}
	
}
