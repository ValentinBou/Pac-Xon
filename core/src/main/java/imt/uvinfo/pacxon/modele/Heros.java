package imt.uvinfo.pacxon.modele;

public class Heros extends Personnage {
	private boolean isTracing = false;
	private int coordonneeSpawnX = 0;
	private int coordonneeSpawnY = -1;
	private double vitesse;
	
	private boolean FLAG_KEY_PRESSED_ARROW_RIGHT 	= false;
	private boolean FLAG_KEY_PRESSED_ARROW_UP 		= false;
	private boolean FLAG_KEY_PRESSED_ARROW_LEFT 	= false;
	private boolean FLAG_KEY_PRESSED_ARROW_DOWN 	= false;
	
	public Heros(Jeu jeu) {
		super(jeu);
		
		/* Configuration héros */
		/* TODO : possibilité d'utiliser un fichier de config */
		apparu = true;
		largeur = 1;
		hauteur = 1;
		coordonneeSpawnX = 0;
		coordonneeSpawnY = -1; // Coordonnée en partant du haut de l'écran
		vitesse = 0.5;
		iconeName = "iconeHeros";
		/* End Configuration héros */
		
		directionX = 0.0;
		directionY = 0.0;
		
		posX = 0.0;
		posY = 0.0;
	}

	protected void initier() {
		int largeurNiveau = this.jeu.getNiveauActuel().getTerrain().getLargeur();
		int hauteurNiveau = this.jeu.getNiveauActuel().getTerrain().getHauteur();
		
		posX = (double) ((coordonneeSpawnX + largeurNiveau) % largeurNiveau) / largeurNiveau;
		posY = (double) ((coordonneeSpawnY + hauteurNiveau) % hauteurNiveau) / hauteurNiveau;
	}
	
	public void update(float elapsedTime) {
		int largeurNiveau = this.jeu.getNiveauActuel().getTerrain().getLargeur();
		int hauteurNiveau = this.jeu.getNiveauActuel().getTerrain().getHauteur();
		double maxPosX = 1.0 - 1.0 / largeurNiveau;
		double maxPosY = 1.0 - 1.0 / hauteurNiveau;
		
		if(!isTracing) {
			if(FLAG_KEY_PRESSED_ARROW_RIGHT) {
				/* Changement de direction */
				if(directionX <= 0.0) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
				}
				directionX = vitesse;
				directionY = 0.0;
			} else if(FLAG_KEY_PRESSED_ARROW_UP) {
				/* Changement de direction */
				if(directionY <= 0.0) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
				}
				directionX = 0.0;
				directionY = vitesse;
			} else if(FLAG_KEY_PRESSED_ARROW_LEFT) {
				/* Changement de direction */
				if(directionX >= 0.0) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
				}
				directionX = -vitesse;
				directionY = 0.0;
			} else if(FLAG_KEY_PRESSED_ARROW_DOWN) {
				/* Changement de direction */
				if(directionY >= 0.0) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
				}
				directionX = 0.0;
				directionY = -vitesse;
			} else {
				if(directionX != 0.0 || directionY != 0.0) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
				}
				// Pas de touche appuyée, le héros de fait rien
				directionX = 0.0;
				directionY = 0.0;
			}
		} else {
			if(directionX != 0.0) {
				if(FLAG_KEY_PRESSED_ARROW_UP) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
					directionX = 0.0;
					directionY = vitesse;
				} else if(FLAG_KEY_PRESSED_ARROW_DOWN) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
					directionX = 0.0;
					directionY = -vitesse;
				}
			} else if(directionY != 0.0) {
				if(FLAG_KEY_PRESSED_ARROW_RIGHT) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
					directionX = vitesse;
					directionY = 0.0;
				} else if(FLAG_KEY_PRESSED_ARROW_LEFT) {
					posX = (double) ((int) (posX * largeurNiveau + 0.5)) / largeurNiveau;
					posY = (double) ((int) (posY * hauteurNiveau + 0.5)) / hauteurNiveau;
					directionX = -vitesse;
					directionY = 0.0;
				}
			}
		}
		
		posX += directionX * elapsedTime;
		if(posX > maxPosX) {
			posX = maxPosX;
		} else if(posX < 0.0) {
			posX = 0.0;
		}
		posY += directionY * elapsedTime;
		if(posY > maxPosY) {
			posY = maxPosY;
		} else if(posY < 0.0) {
			posY = 0.0;
		}
		
		/* On gère l'entrée dans un nouveau bloc */
	}
	
	public void remplirTracage() {
		// TODO Algo
	}
	
	public void setFlagArrowRight() {
		FLAG_KEY_PRESSED_ARROW_RIGHT = true;
	}
	
	public void unSetFlagArrowRight() {
		FLAG_KEY_PRESSED_ARROW_RIGHT = false;
	}
	
	public boolean isSetFlagArrowRight() {
		return FLAG_KEY_PRESSED_ARROW_RIGHT;
	}
	
	public void setFlagArrowUp() {
		FLAG_KEY_PRESSED_ARROW_UP = true;
	}
	
	public void unSetFlagArrowUp() {
		FLAG_KEY_PRESSED_ARROW_UP = false;
	}
	
	public boolean isSetFlagArrowUp() {
		return FLAG_KEY_PRESSED_ARROW_UP;
	}
	
	public void setFlagArrowLeft() {
		FLAG_KEY_PRESSED_ARROW_LEFT = true;
	}
	
	public void unSetFlagArrowLeft() {
		FLAG_KEY_PRESSED_ARROW_LEFT = false;
	}

	public boolean isSetFlagArrowLeft() {
		return FLAG_KEY_PRESSED_ARROW_LEFT;
	}
	
	public void setFlagArrowDown() {
		FLAG_KEY_PRESSED_ARROW_DOWN = true;
	}
	
	public void unSetFlagArrowDown() {
		FLAG_KEY_PRESSED_ARROW_DOWN = false;
	}
	
	public boolean isSetFlagArrowDown() {
		return FLAG_KEY_PRESSED_ARROW_DOWN;
	}
	
}
