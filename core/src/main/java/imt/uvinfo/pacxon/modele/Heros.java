package imt.uvinfo.pacxon.modele;

public class Heros extends Personnage {
	private boolean isTracing = false;
	private int coordonneeSpawnX = 0;
	private int coordonneeSpawnY = -1;
	
	public Heros(Jeu jeu) {
		super(jeu);
		
		/* Configuration héros */
		/* TODO : possibilité d'utiliser un fichier de config */
		apparu = true;
		largeur = 1;
		hauteur = 1;
		coordonneeSpawnX = 0;
		coordonneeSpawnY = -1; // Coordonnée en partant du haut de l'écran
		double vitesse = 0.01;
		iconeName = "iconeHeros";
		/* End Configuration héros */
		
		directionX = vitesse;
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
	
	public void remplirTracage() {
		// TODO Algo
	}
	
	
}
