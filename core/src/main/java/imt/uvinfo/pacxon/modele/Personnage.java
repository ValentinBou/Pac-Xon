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
	
	// Vérifie si une partie d'un personnage est présente sur un bloc en particulier
	protected boolean estDansBloc(int x, int y) {
		Terrain terrain = this.jeu.getNiveauActuel().getTerrain();
		int i, j;
		
		// Pour chaque bloc sur le quel est présent le monstre
		for(i = terrain.getXint(posX); i < (terrain.getXint(posX) + largeur); i++) {
			for(j = terrain.getYint(posY); j < (terrain.getYint(posY) + hauteur); j++) {
				// Le bloc correspond au bloc recherché : le monstre est sur le bloc
				if((i == x) && (j == y)) {
					return true;
				}
			}
		}
		
		// Aucun des blocs sur lesquels est le monstre correspond au bloc recherché : le monstre n'est pas sur le bloc
		return false;
	}
	
	// Cette méthode sera utile pour des monstres qui n'apparraissent que dans certaines conditions
	abstract boolean essayerDApparaitre();
	
	abstract void update(float elapsedTime);
	
	abstract void initier();
	
}
