package imt.uvinfo.pacxon.modele;

public abstract class Personnage {
	protected Jeu jeu;
	
	protected boolean apparu;
	
	protected int largeur;
	protected int hauteur;
	protected double posX;
	protected double posY;
	protected double directionX;
	protected double directionY;
	
	public String iconeName;

	public Personnage(Jeu jeu, boolean apparu, int largeur, int hauteur, double posX, double posY, double directionX,
			double directionY, String iconeName) {
		this.jeu = jeu;
		this.apparu = apparu;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.posX = posX;
		this.posY = posY;
		this.directionX = directionX;
		this.directionY = directionY;
		this.iconeName = iconeName;
	}
	
	public Personnage(Jeu jeu) {
		this.jeu = jeu;
	}
	
	/* Getters Setters */
	public Jeu getJeu() {
		return jeu;
	}

	public boolean isApparu() {
		return apparu;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getDirectionX() {
		return directionX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public String getIconeName() {
		return iconeName;
	}
	/* Fin Getters Setters */
	
	private boolean essayerDApparaitre() {
		apparu = true;
		return apparu;
	}
	
	abstract void update(float elapsedTime);
	
	abstract void initier();
	
	
}
