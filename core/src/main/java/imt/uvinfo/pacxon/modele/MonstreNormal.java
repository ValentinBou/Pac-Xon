package imt.uvinfo.pacxon.modele;

import java.util.Random;

public class MonstreNormal extends Personnage {
	
	private double vitesse;
	private double coeffDirectionDepart;

	public MonstreNormal(Jeu jeu) {
		super(jeu);
		
		/* Configuration monstre normal */
		/* TODO : possibilité d'utiliser un fichier de config */
		apparu = true;
		largeur = 1;
		hauteur = 1;

		vitesse = 0.5;
		coeffDirectionDepart = 0.25 * Math.PI; // Angle de départ [0*pi, 2*pi[
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
		int coordonneeSpawnX = rand.nextInt(largeurNiveau - 2) + 1; // "-2" et "+1" pour exclure les bordures
		int coordonneeSpawnY = rand.nextInt(hauteurNiveau - 2) + 1; // "-2" et "+1" pour exclure les bordures
		
		posX = (double) ((coordonneeSpawnX + largeurNiveau) % largeurNiveau) / largeurNiveau;
		posY = (double) ((coordonneeSpawnY + hauteurNiveau) % hauteurNiveau) / hauteurNiveau;
		
		System.out.println(posX);
		System.out.println(posY);
	}
	
	public void update(float elapsedTime) {
		int largeurNiveau = this.jeu.getNiveauActuel().getTerrain().getLargeur();
		int hauteurNiveau = this.jeu.getNiveauActuel().getTerrain().getHauteur();
		double largeurUniteBloc = 1.0 / largeurNiveau;
		double hauteurUniteBloc = 1.0 / hauteurNiveau;
		
		double premierX, dernierX, premierY, dernierY;
		
		if(directionX < 0.0) {
			premierX = ((posX + directionX * elapsedTime) + (largeur * largeurUniteBloc));
			dernierX = (posX + directionX * elapsedTime);
		} else {
			premierX = (posX + directionX * elapsedTime);
			dernierX = ((posX + directionX * elapsedTime) + (largeur * largeurUniteBloc));
		}
		
		if(directionY < 0.0) {
			premierY = ((posY + directionY * elapsedTime) + (hauteur * hauteurUniteBloc));
			dernierY = (posY + directionY * elapsedTime);
		} else {
			premierY = (posY + directionY * elapsedTime);
			dernierY = ((posY + directionY * elapsedTime) + (hauteur * hauteurUniteBloc));
		}
		
		verifierBlocs(premierX, dernierX, premierY, dernierY);
		
		/* Vérification du bloc suivant */
		/*double blocSuivantX;
		if(directionX > 0.0) {
			blocSuivantX = ((posX + directionX * elapsedTime) + (largeur * largeurUniteBloc));
		} else {
			blocSuivantX = (posX + directionX * elapsedTime);
		}
		double blocSuivantY;
		if(directionY > 0.0) {
			blocSuivantY = ((posY + directionY * elapsedTime) + (hauteur * hauteurUniteBloc));
		} else {
			blocSuivantY = (posY + directionY * elapsedTime);
		}*/
		
		/* Vérification de la présence de bordures*/
		/*TypeBloc tmpBloc = null;
		double curseur;
		
		curseur = posY + directionY * elapsedTime;
		
		while((curseur <= blocSuivantY) && (curseur <= (maxPosY - hauteurUniteBloc))) {
			tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(blocSuivantX, curseur);
			if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
				directionX = -directionX;
				break;
			}
			curseur += hauteurUniteBloc;
		}*/
		
		/*curseur = posX + directionX * elapsedTime;
		
		while((curseur <= blocSuivantX) && (curseur <= (maxPosX - largeurUniteBloc))) {
			tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(curseur, blocSuivantY);
			if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
				directionY = -directionY;
				break;
			}
			curseur += largeurUniteBloc;
		}*/
		
		
		
		/* Le monstre avance (position incrémentée) */
		
		posX += directionX * elapsedTime;
		posY += directionY * elapsedTime;
		
	}
	
	private void verifierBlocs(double premierX, double dernierX, double premierY, double dernierY) {
		int largeurNiveau = this.jeu.getNiveauActuel().getTerrain().getLargeur();
		int hauteurNiveau = this.jeu.getNiveauActuel().getTerrain().getHauteur();
		double largeurUniteBloc = 1.0 / largeurNiveau;
		double hauteurUniteBloc = 1.0 / hauteurNiveau;
		double maxPosX = 1.0 - ((double)largeur / largeurNiveau);
		double maxPosY = 1.0 - ((double)hauteur / hauteurNiveau);		
		
		/* Vérification de la présence de blocs */
		TypeBloc tmpBloc = null;
		double curseur;
		double incrementeValeur;
		boolean isDevieX = false;
		boolean isDevieY = false;
		
		curseur = premierX;
		
		if(directionX < 0.0) {
			incrementeValeur = -largeurUniteBloc;
			while((curseur >= (dernierX + largeurUniteBloc)) && (curseur >= (0.0))) {
				tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(curseur, dernierY);
				if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
					directionY = -directionY;
					isDevieY = true;
					break;
				}
				curseur += incrementeValeur;
			}
		} else {
			incrementeValeur = largeurUniteBloc;
			while((curseur <= (dernierX - largeurUniteBloc)) && (curseur <= (maxPosX))) {
				tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(curseur, dernierY);
				if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
					directionY = -directionY;
					isDevieY = true;
					break;
				}
				curseur += incrementeValeur;
			}
		}
		
		curseur = premierY;
		
		if(directionY < 0.0) {
			incrementeValeur = -hauteurUniteBloc;
			while((curseur >= (dernierY + hauteurUniteBloc)) && (curseur >= (0.0))) {
				tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(dernierX, curseur);
				if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
					directionX = -directionX;
					isDevieX = true;
					break;
				}
				curseur += incrementeValeur;
			}
		} else {
			incrementeValeur = hauteurUniteBloc;
			while((curseur <= (dernierY - hauteurUniteBloc)) && (curseur <= (maxPosY))) {
				tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(dernierX, curseur);
				if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
					directionX = -directionX;
					isDevieX = true;
					break;
				}
				curseur += incrementeValeur;
			}
		}
		
		/* Si le monstre touche directement un coin */
		if(!isDevieX && !isDevieY) {
			tmpBloc = this.jeu.getNiveauActuel().getTerrain().getBloc(dernierX, dernierY);
			if((tmpBloc == TypeBloc.Bordure) || (tmpBloc == TypeBloc.BlocNormal)) {
				directionX = -directionX;
				directionY = -directionY;
			}
		}	
		
	}
	
}
