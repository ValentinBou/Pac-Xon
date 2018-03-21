package imt.uvinfo.pacxon.modele;

import java.util.Random;

public class MonstreNormal extends Personnage {

	public MonstreNormal(Jeu jeu) {
		super(jeu);
		
		/* Configuration monstre normal */
		/* TODO : possibilité d'utiliser un fichier de config */
		apparu = true;
		largeur = 1;
		hauteur = 1;

		double vitesse = 0.01;
		double coeffDirectionDepart = 0.25 * Math.PI; // Angle de départ [0*pi, 2*pi[
		iconeName = "iconeHeros";
		/* End Configuration monstre normal */
		
		directionX = vitesse * Math.cos(coeffDirectionDepart);
		directionY = vitesse * Math.sin(coeffDirectionDepart);
		
		posX = 0.0;
		posY = 0.0;
	}
	
	protected void initier() {
		int largeurNiveau = this.jeu.getNiveauActuel().getTerrain().getLargeur();
		int hauteurNiveau = this.jeu.getNiveauActuel().getTerrain().getHauteur();
		
		Random rand = new Random();
		int coordonneeSpawnX = rand.nextInt(largeurNiveau - 1) + 1; // "-1" et "+1" pour exclure les bordures
		int coordonneeSpawnY = rand.nextInt(hauteurNiveau - 1) + 1; // "-1" et "+1" pour exclure les bordures
		
		posX = (double) ((coordonneeSpawnX + largeurNiveau) % largeurNiveau) / largeurNiveau;
		posY = (double) ((coordonneeSpawnY + hauteurNiveau) % hauteurNiveau) / hauteurNiveau;
		
		System.out.println(posX);
		System.out.println(posY);
	}

	@Override
	public String toString() {
		return "MonstreNormal [jeu=" + jeu + ", apparu=" + apparu + ", largeur=" + largeur + ", hauteur=" + hauteur
				+ ", posX=" + posX + ", posY=" + posY + ", directionX=" + directionX + ", directionY=" + directionY
				+ ", iconeName=" + iconeName + "]";
	}

}
